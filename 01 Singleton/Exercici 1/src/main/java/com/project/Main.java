package com.project;

import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("iniciant 1");
        Thread.sleep(3000);
        Singleton instance1 = Singleton.getInstance("Pep", "Corbera", 70);

        System.out.println("iniciant 2");
        Thread.sleep(3000);
        Singleton instance2 = getNewDestroyedInstance("Adria", "Martinez", 24);
        System.out.println("iniciant 3");
        Thread.sleep(3000);
        Singleton instance3 = getNewDestroyedInstance("David", "Bargados", 37);

        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);


    }

    static Singleton getNewDestroyedInstance (String nom, String cognom, int edat) {
        
        Singleton result = null;
        try {
            Constructor<?>[] constructors = Singleton.class.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                result = (Singleton) constructor.newInstance(nom, cognom, edat);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}