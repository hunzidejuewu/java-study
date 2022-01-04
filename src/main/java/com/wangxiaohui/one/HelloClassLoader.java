package com.wangxiaohui.one;

import java.lang.reflect.Method;
import java.util.Base64;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            // 加载类
            Class<?> clazz = new HelloClassLoader().findClass("Hello");
            // 创建对象
            Object instance =  clazz.newInstance();
            // 查看方法
            for (Method m : clazz.getDeclaredMethods()) {
                System.out.println(clazz.getSimpleName() + "." + m.getName());
            }
            // 调用方法
            Method method =  clazz.getMethod("hello");
            method.invoke(instance);
            // 调用实例方法
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
        };
    }


    @Override
    protected Class<?> findClass(String name) {
        // 网上base64文件的结果
        String helloBase64 = "NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYq" +
                "SnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpu" +
                "ajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNC" +
                "vjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f/" +
                "//////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fx" +
                "J//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
        byte[] bytes = decode(helloBase64);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] decode(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        byte[] byteArray = new byte[bytes.length];
        // 处理255-的方法
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) (255 - bytes[i]);
        }
        return byteArray;
    }
}
