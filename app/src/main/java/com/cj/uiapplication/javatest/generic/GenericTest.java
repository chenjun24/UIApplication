package com.cj.uiapplication.javatest.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 泛型
 */
public class GenericTest {

    /**
     * 泛型类
     * @param <T>
     */
    static class GenericClass<T>{
        private  T key;
        public void setKey(T t){
           this.key = t;
        }

        public T getKey() {
            return key;
        }
    }

    /**
     * 泛型接口
     *   实现类如果是泛型类  那么参数类型需要一致
     *   实现类不是泛型类   那接口必须明确参数化类型 默认是object
     *   实现类可以进行泛型扩展
     * @param <T>
     */
    static interface GenericInterface<T>{

        public void test(T t);

    }

    static class GenericMethod{
         //泛型方法  可以静态 支持可变参数 多了泛型列表
        //泛型方法 跟泛型类参数 可以不一致 独立于泛型类
        public <T> T test(T t){
          return t;
        }
       //泛型类中成员方法 不能使用静态
    }

    static class Command{

    }
    static class SubCommand extends Command{

    }
    static interface ICommandExecutor<T extends Command>{

    }
    static class SubCommandExecutor implements ICommandExecutor<SubCommand> {

    }


    public static void main(String[] args){
        //为什么使用泛型 ClassCastException 运行时异常
        //编译器安全检测  复用 不支持基本数据类型
        //逻辑上不同  实际是同一个
        //参数化类型
        //子类如果是泛型类 父类是泛型类  那么参数类型需要一致
        //子类不是泛型类   父类是泛型类  那父类必须明确参数化类型
       // GenericClass<String> genericClass = new GenericClass();
        //通配符
        //Box<Number> 本质一样  Box<Integer> 方法参数 两个函数则表示是一个的
        //? 上限  下限
        //类型擦除  无限制擦除  ，有限制擦除  保留上限类型
        //桥接方法 保持接口和类的方法重写 实际关系 两个方法 一个返回值object
       //泛型数组  不能创建带泛型对象的数组  容易出错类型转化出错
//        SubCommandExecutor executor = new SubCommandExecutor();
//        Class<? extends SubCommandExecutor> aClass = executor.getClass();
//        Type[] genericInterfaces = aClass.getGenericInterfaces();
//        for (Type genericInterface : genericInterfaces) {
//            System.out.println("111..."+genericInterface);
//            Type[] actualTypeArguments = ((ParameterizedType) genericInterface).getActualTypeArguments();
//            Class target = (Class) actualTypeArguments[0];
//            System.out.println("111...target--"+target.getName());
//        }
//
//        Type genericSuperclass = aClass.getGenericSuperclass();
//        System.out.println("222..."+genericSuperclass);
          Animal animal = new Animal();
          Cat cat = new Cat();
          MinCat minCat = new MinCat();
          ArrayList<Animal> animals = new ArrayList<>();
          ArrayList<Cat> cats = new ArrayList<>();
          ArrayList<MinCat> minCats = new ArrayList<>();
          test(animals);
          test(cats);
          test(minCats);

       // test1(animals);
        test1(cats);
        //test1(minCats);

    }

    public static void test(ArrayList<? extends Animal> list){
//        list.add(new Animal());
//        list.add(new Cat());
//        list.add(new MinCat());  不能添加元素
        Animal animal = list.get(1);//获取的都是Animal
    }

    public static void test1(ArrayList<? super Cat> list){
       // list.add(new Animal());
        list.add(new Cat());
        list.add(new MinCat());
        Object object = list.get(0);//获取的都是object
    }

}
