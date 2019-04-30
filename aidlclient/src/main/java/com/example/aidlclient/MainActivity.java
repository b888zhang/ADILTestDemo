package com.example.aidlclient;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.example.zb.adiltestdemo.IMyAidlParParcelable;
import com.example.zb.adiltestdemo.Person;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_num1)
    EditText mEtNum1;
    @BindView(R.id.et_num2)
    EditText mEtNum2;
    @BindView(R.id.et_num3)
    EditText mEtNum3;
    @BindView(R.id.bt_jisuan)
    Button   mBtJisuan;
 //   IMyAidlInterface iMyAidlInterface;
 IMyAidlParParcelable iMyAidlParParcelable;
    private ServiceConnection conn=new ServiceConnection() {
        //绑定服务的时候
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //拿到了远程的服务
          //   iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            iMyAidlParParcelable = IMyAidlParParcelable.Stub.asInterface(service);
        }
        //断开服务的时候
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收资源
           // iMyAidlInterface=null;
            iMyAidlParParcelable=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //软件一启动就绑定服务
        bindService();

    }

    @OnClick(R.id.bt_jisuan)
    public void onViewClicked() {

//        String num1 = mEtNum1.getText().toString().trim();
//        String num2 = mEtNum2.getText().toString().trim();
//
//        if(TextUtils.isEmpty(num1) || TextUtils.isEmpty(num2)){
//            Toast.makeText(this,"参数不能为空",Toast.LENGTH_SHORT).show();
//        }else {
//            try {
//                //调用远程的服务，获取结果
//                int res = iMyAidlInterface.add(Integer.parseInt(num1), Integer.parseInt(num2));
//                mEtNum3.setText(res+"");
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }

        try {
            ArrayList<Person> persons = (ArrayList<Person>) iMyAidlParParcelable.add(new Person("张三", 19));
            Log.i("persons--------",persons.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    private void bindService() {
        //获取到服务端
        Intent intent = new Intent();
        //新版本  必须显示intent启动  绑定服务
        intent.setComponent(new ComponentName("com.example.zb.adiltestdemo","com.example.zb.adiltestdemo.MyService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定服务
        unbindService(conn);
    }
}
