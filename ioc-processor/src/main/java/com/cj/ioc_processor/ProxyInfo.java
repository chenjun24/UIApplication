package com.cj.ioc_processor;

import java.util.HashMap;
import java.util.Map;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class ProxyInfo {
    private static final String SUFFIX = "ViewInjector";
    public Map<Integer, VariableElement> mInjectElements = new HashMap<>();//被注解修饰的变量和id映射表
    private TypeElement typeElement;//类信息
    private String packageName;//包名
    private String proxyClassName;//代理类名

    public ProxyInfo(final Elements elementUtils, final TypeElement typeElement){
          this.typeElement = typeElement;
        PackageElement packageOf = elementUtils.getPackageOf(typeElement);
        packageName = packageOf.getQualifiedName().toString();
        String className = getClassName(typeElement, packageName);
        proxyClassName = className+"$$"+SUFFIX;
        System.out.println("***************"+proxyClassName+"\n"+packageName);
    }

    private String getClassName(final TypeElement typeElement,final String packageName){
           int packageLength = packageName.length()+1;
           return typeElement.getQualifiedName().toString().substring(packageLength).replace('.','$');
    }

    public String getProxyClassName(){
        return packageName+"."+proxyClassName;
    }
    public TypeElement getTypeElement(){
        return typeElement;
    }

    public String generateJavaCode(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("//generate code,Do not modify it!\n")
                .append("package ").append(packageName).append(";\n\n")
                .append("import com.cj.ioc.*;\n\n")
                .append("public class ").append(proxyClassName).append(" implements ")
                .append(SUFFIX).append("<").append(typeElement.getQualifiedName())
                .append(">").append("{\n");
        generateMethod(stringBuilder);
        stringBuilder.append("\n}\n");
        return stringBuilder.toString();

    }
    private void generateMethod(final StringBuilder stringBuilder){
        if (stringBuilder==null){

        }
        stringBuilder.append("@Override\n")
                .append("public void inject(").append(typeElement.getQualifiedName())
                .append(" host,Object object)")
                .append("{\n");
        for (Integer id:mInjectElements.keySet()){
            VariableElement variableElement = mInjectElements.get(id);
            String name = variableElement.getSimpleName().toString();
            String type = variableElement.asType().toString();
            stringBuilder.append("if(object instanceof android.app.Activity)").append("{\n")
                    .append("host.").append(name).append(" = ")
                    .append("(").append(type).append(")((android.app.Activity)object).findViewById(").append(id).append(");")
                    .append("\n}\n")
                    .append("else").append("{\n")
                    .append("host.").append(name).append(" = ")
                    .append("(").append(type).append(")((android.view.View)object).findViewById(").append(id).append(");")
                    .append("\n}\n");
            }
        stringBuilder.append("\n}\n");
    }
}
