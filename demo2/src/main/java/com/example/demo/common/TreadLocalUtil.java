package com.example.demo.common;

public class TreadLocalUtil {

    //全局的ThreadLocal
    private final static ThreadLocal THREAD_LOCAL=new ThreadLocal();

    //存储数据
    public static void set(Object o){
        THREAD_LOCAL.set(o);
    }
    //获取数据
    public static Object get(){
        return  THREAD_LOCAL.get();
    }
    //销毁数据【避免内存泄漏】
    public static void remove(){
        THREAD_LOCAL.remove();
    }



}
