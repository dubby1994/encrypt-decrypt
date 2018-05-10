package cn.dubby.serializable;

import java.io.*;

/**
 * Created by yangzheng03 on 2018/5/4.
 */
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SimpleBean simpleBean = new SimpleBean();
        simpleBean.setId(1);
        simpleBean.setName("name");
        simpleBean.setTime(System.currentTimeMillis());

        FileOutputStream b = new FileOutputStream("test10.txt");
        ObjectOutput a = new ObjectOutputStream(b);
        a.writeObject(simpleBean);
        a.writeObject(simpleBean);
        a.writeObject(simpleBean);
        a.writeObject(simpleBean);
        a.writeObject(simpleBean);

        SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setId(2);
        simpleBean1.setName("name2");
        simpleBean1.setTime(System.currentTimeMillis());

        a.writeObject(simpleBean1);
        a.writeObject(simpleBean1);
        a.writeObject(simpleBean1);
        a.writeObject(simpleBean);
        a.writeObject(simpleBean);

//        FileInputStream i = new FileInputStream("test10.txt");
//        ObjectInput objectReader = new ObjectInputStream(i);
//
//        Object o1 = objectReader.readObject();
//        System.out.println(o1);
//
//        Object o2 = objectReader.readObject();
//        System.out.println(o2);
//
//        Object o3 = objectReader.readObject();
//        System.out.println(o3);
//
//        System.out.println(o1 == o2);
//        System.out.println(o2 == o3);

    }

}
