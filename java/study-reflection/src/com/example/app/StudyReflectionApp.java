package com.example.app;

import com.example.A;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StudyReflectionApp {

    public static void main(String[] args) {
        A a = new A(42);
        sun(a);
        System.out.println(a.fun());
    }

    public static void sun(Object o) {
        Class<?> clazz = o.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            switch (field.getName()) {
                case "x":
                    try {
                        field.setAccessible(true);
                        field.set(o, 108);
                        field.setAccessible(false);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            switch (method.getName()) {
                case "gun":
                    try {
                        method.setAccessible(true);
                        method.invoke(o, null);
                        method.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }

}

