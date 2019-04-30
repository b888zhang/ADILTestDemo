// IMyAidlParParcelable.aidl
package com.example.zb.adiltestdemo;

import com.example.zb.adiltestdemo.Person;
//自定义类型
interface IMyAidlParParcelable {

    List<Person> add(in Person person);

}
