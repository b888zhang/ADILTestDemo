package com.example.zb.adiltestdemo;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    public MyService() {
    }
    ArrayList<Person> personlist;
    /**
     * 当客户端绑定到该服务的时候，会执行该方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        personlist = new ArrayList<>();
        return mIBinder;

    }

    private IBinder mIBinder=new IMyAidlParParcelable.Stub() {
        @Override
        public List<Person> add(Person person) throws RemoteException {
            personlist.add(person);
            return personlist;
        }
    };



//    private  IBinder mIBinder=new IMyAidlInterface.Stub() {
//        @Override
//        public int add(int num1, int num2) throws RemoteException {
//            Log.i("TAG","收到了远程请求，输入的参数是"+num1+"和"+num2);
//            return num1+num2;
//        }
//    };

//    private  IBinder mIBinder1=new IMyBasictypes.Stub() {
//        @Override
//        public List<String> basicTypes(byte abyte, int anInt, long aLong,
//                                       boolean aBoolean, float aFloat, double aDouble, char aChar, String aString, List<String> aList) throws RemoteException {
//            return null;
//        }
//    };


}
