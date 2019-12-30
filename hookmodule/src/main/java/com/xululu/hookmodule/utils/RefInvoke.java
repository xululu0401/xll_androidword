package com.xululu.hookmodule.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Author: pipilu
 * Time: 2019-08-27 09:21
 */
public class RefInvoke {

    //无参数构造方法
    public static Object createObject(String className) {
        Class[] paramTypes = new Class[]{};
        Object[] paraValues = new Object[]{};
        try {
            Class cls = Class.forName(className);
            return createObject(cls, paramTypes, paraValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 一个参数的构造方法
     * @return
     */
    public static Object createObject(String className, Class paraType, Object paraValue){
        Class[] paraTypes = {paraType};
        Object[] paraValues = {paraValue};
        try {
            Class cls = Class.forName(className);
            return createObject(cls, paraTypes, paraValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 多个参数构造方法
     * @param className
     * @param paraTypes
     * @param paraValues
     * @return
     */
    public static Object createObject(String className, Class[] paraTypes, Object[] paraValues) {
        try {
            Class cls = Class.forName(className);
            return createObject(cls, paraTypes, paraValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 多个参数构造方法
     * @param clazz
     * @param pareTypes
     * @param paraValues
     * @return
     */
    public static Object createObject(Class clazz, Class[] pareTypes, Object[] paraValues) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor(pareTypes);
            return constructor.newInstance(paraValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取field字段
     * @param clazz
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getFieldObject(Class clazz, Object object, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldObject(Class clazz, Object obj, String fieldName, Object fieldValue) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据object获取field
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getFieldObject(Object object, String fieldName) {
        try {
            return getFieldObject(object.getClass(), object, fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置
     * @param obj
     * @param fieldName
     * @param fieldValue
     */
    public static void setFieldObject(Object obj, String fieldName, Object fieldValue) {
        setFieldObject(obj.getClass(), obj, fieldName, fieldValue);
    }

    /**
     * 获取静态属性
     * @param className
     * @param fieldName
     * @return
     */
    public static Object getStaticFieldObject(String className, String fieldName) {
        return getFieldObject(className, null, fieldName);
    }

    public static void setStaticFieldObject(String className, String fieldName, Object fieldValue) {
        setFieldObject(className, null, fieldName, fieldValue);
    }

    /**
     * 获取属性字段
     * @param className
     * @param obj
     * @param fieldname
     * @return
     */
    public static Object getFieldObject(String className, Object obj, String fieldname) {
        try {
            Class cls = Class.forName(className);
            Field field = cls.getDeclaredField(fieldname);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置属性字段
     * @param className
     * @param obj
     * @param fieldName
     * @param fieldValue
     */
    public static void setFieldObject(String className, Object obj, String fieldName, Object fieldValue) {
        try {
            Class cls = Class.forName(className);
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object invokeInstanceMehod(Object obj, String methodName, Class[] paraTypes, Object[] paraValues) {
        try {
            Class cls = obj.getClass();
            Method method = cls.getDeclaredMethod(methodName, paraTypes);
            method.setAccessible(true);
            return method.invoke(obj, paraValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeStaticMethod(String className, String methodName){
        try{
            Class cls = Class.forName(className);
            Class[] paraTypes = new Class[]{};
            Object[] paraValues = new Object[]{};
            Method method = cls.getDeclaredMethod(methodName, paraTypes);
            method.setAccessible(true);
            return method.invoke(null, paraValues);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
