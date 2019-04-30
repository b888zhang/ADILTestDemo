// IMyBasictypes.aidl
package com.example.zb.adiltestdemo;

// Declare any non-default types here with import statements

//基本类型
interface IMyBasictypes {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<String> basicTypes(byte abyte,
                    int anInt,
                    long aLong,
                    boolean aBoolean,
                    float aFloat,
                    double aDouble,
                    char aChar,
                    String aString,
                    in List<String> aList
                    );
}
