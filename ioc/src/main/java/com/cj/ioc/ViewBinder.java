package com.cj.ioc;

import android.app.Activity;

public class ViewBinder {
    private final static String SUFFIX = "$$ViewInjector";
    public static void bind(Activity activity){
         bind(activity,activity);
    }
    private static void bind(final Object host,final Object root){
          if (host == null || root == null){
              return;
          }
        Class<?> aClass = host.getClass();
        String proxyClassName = aClass.getName()+SUFFIX;//拼接 生成类的名称
        try {
            Class<?> proxyClass = Class.forName(proxyClassName);
            ViewInjector viewInjector = (ViewInjector) proxyClass.newInstance();
            if (viewInjector != null){
                viewInjector.inject(host,root);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
