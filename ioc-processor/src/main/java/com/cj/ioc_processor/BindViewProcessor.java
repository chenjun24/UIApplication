package com.cj.ioc_processor;

import com.cj.ioc_annotation.BindView;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

//@SupportedAnnotationTypes("com.cj.ioc_annotation.BindView")
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)//帮我们生成 META-INF 信息
public class BindViewProcessor extends AbstractProcessor {
    private Elements elementUtils;//基于元素进行操作的工具方法
    private Filer filer;//代码创建者
    private Messager messager; //日志，提示者，提示错误、警告
    private Map<String,ProxyInfo> proxyInfoMap = new HashMap<>();
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        //收集信息
        //生成代码

        /*注解修饰变量所在的类名，便于和后缀拼接生成代理类
        类的完整包名
        类中被注解修饰的字段，以及对应的布局 id*/

        messager.printMessage(Diagnostic.Kind.NOTE,"process..");
        //避免生成重复的代理类
        proxyInfoMap.clear();

        //拿到被@BindView注解修饰的元素，应该是VariableElement
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        //收集信息
        for (Element element:elementsAnnotatedWith){
            if (!checkAnnotationValid(element, BindView.class)) {    //去除不合格的元素
                continue;
            }
            //类中的成员变量
            VariableElement variableElement = (VariableElement) element;
            //类或接口
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            //完整的名称
            String qualifiedName = typeElement.getQualifiedName().toString();
            ProxyInfo proxyInfo = proxyInfoMap.get(qualifiedName);
            if (proxyInfo == null){
                   //将该类中被注解修饰的变量加入到proxyInfo中
                    proxyInfo = new ProxyInfo(elementUtils,typeElement);
                    proxyInfoMap.put(qualifiedName,proxyInfo);
            }
            BindView annotation = variableElement.getAnnotation(BindView.class);
            if (annotation!=null){
                int value = annotation.value();
                proxyInfo.mInjectElements.put(value,variableElement);
            }
        }

        //生成代理
        for (String key:proxyInfoMap.keySet()){
            ProxyInfo proxyInfo = proxyInfoMap.get(key);
            try {
                JavaFileObject sourceFile = filer.createSourceFile(proxyInfo.getProxyClassName(), proxyInfo.getTypeElement());
                Writer writer = sourceFile.openWriter();
                writer.write(proxyInfo.generateJavaCode());//写入文件
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.NOTE,"process.."+proxyInfo.getTypeElement()+"*****"+e.getMessage());
            }
        }
//        如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们
//        如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
        return true;
    }

    private boolean checkAnnotationValid(Element element, Class<BindView> bindViewClass) {
        return true;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    /**
     * @SupportedAnnotationTypes("com.cj.ioc_annotation.BindView")
     * 作用跟这个函数类似
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(BindView.class.getCanonicalName());
        return annotationTypes;
    }

    /**
     *@SupportedSourceVersion(SourceVersion.RELEASE_8)
     * 作用跟这个函数类似
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    Map<TypeElement,Set<Element>> elementSetMap = new HashMap<>();
    /**
     * 使用javaPoet方式获取
     * @param roundEnvironment
     */
    private void javaPoetProcess(RoundEnvironment roundEnvironment) {
        elementSetMap.clear();
        messager.printMessage(Diagnostic.Kind.WARNING,"---------------------------------");
        for (Element element:roundEnvironment.getElementsAnnotatedWith(BindView.class)){
            //取包名
            TypeElement typeElement = (TypeElement) element.getEnclosingElement();
            Set<Element> elements = elementSetMap.get(typeElement);
            if (elements == null){
                elements = new HashSet<>();
                elementSetMap.put(typeElement,elements);
                messager.printMessage(Diagnostic.Kind.NOTE,"Add enclose elem "+typeElement.getSimpleName());
                System.out.println("Add enclose elem "+typeElement.getSimpleName());
            }
            elements.add(element);
            messager.printMessage(Diagnostic.Kind.NOTE,"Add bind elem "+element.getSimpleName());
            System.out.println("Add bind elem "+element.getSimpleName());
        }

        for (TypeElement typeElement:elementSetMap.keySet()){
            //@Override
            //public void inject(com.cj.uiapplication.view.annotation.AnnotationActivity host,Object object){
            MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC)//public
                    .returns(TypeName.VOID)
                    .addAnnotation(Override.class)
                    .addParameter(ClassName.get(typeElement.asType()),"host")
                    .addParameter(Object.class,"object");

            for (Element element:elementSetMap.get(typeElement)){
//                methodBuilder.addStatement(String.format("activity.%s = (%s)activity.findViewById(%d)",
//                          bindElem.getSimpleName(),bindElem.asType(),bindElem.getAnnotation(BindView.class).value())
//                        );
            }
            //TypeSpec 生成类，接口，或者枚举   生成BindMainActivity类
//            TypeSpec typeSpec = TypeSpec.classBuilder("Bind"+enclosedElem.getSimpleName())
//                    .superclass(TypeName.get(enclosedElem.asType()))//extends MainActivity
//                    .addModifiers(Modifier.FINAL,Modifier.PUBLIC)//public final
//                    .addMethod(methodSpecBuilder.build())//添加上面得方法
//                    .build();
            //用于构造输出包含一个顶级类的Java文件
//            JavaFile file = JavaFile.builder(getPackageName(enclosedElem),typeSpec).build();
//            try {
//                file.writeTo(processingEnv.getFiler());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }
}