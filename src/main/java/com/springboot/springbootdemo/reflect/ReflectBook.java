package com.springboot.springbootdemo.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * description: ReflectBook
 * date: 2019-09-03 13:49
 * author: Lee
 */
public class ReflectBook {
    private final static String TAG = "peter.log.ReflectClass";
    private static Logger logger = LoggerFactory.getLogger("ReflectBook");
    private static Class<?> classBook = null;

    static {
        try {
            classBook = Class.forName("com.springboot.springbootdemo.reflect.Book");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            // 创建对象
            ReflectBook.reflectNewInstance();

            // 反射私有的构造方法
            ReflectBook.reflectPrivateConstructor();

            // 反射私有属性
            ReflectBook.reflectPrivateField();

            // 反射私有方法
            ReflectBook.reflectPrivateMethod();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 创建对象
    public static void reflectNewInstance() {
        try {
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("安徒生童话");
            book.setAuthor("安徒生");
            logger.info("reflectNewInstance book = " + book.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有的构造方法
    public static void reflectPrivateConstructor() {
        try {
            Constructor<?> constructor = classBook.getDeclaredConstructor(String.class, String.class);
            constructor.setAccessible(true);
            Object objectBook = constructor.newInstance("一千零一夜", "阿拉伯民间故事集");
            Book book = (Book) objectBook;
            logger.info("reflectPrivateConstructor book = " + book.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有属性
    public static void reflectPrivateField() {
        try {
            Object objectBook = classBook.newInstance();
            Field fieldTag = classBook.getDeclaredField("TAG");
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
            logger.info("reflectPrivateField tag = " + tag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有方法
    public static void reflectPrivateMethod() {
        try {
            Method methodBook = classBook.getDeclaredMethod("declaredMethod", int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook, 0);
            logger.info("reflectPrivateMethod string = " + string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
