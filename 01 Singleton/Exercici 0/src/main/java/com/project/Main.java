package com.project;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("iniciant 1");
        Thread.sleep(3000);
        Singleton instance1 = Singleton.getInstance("Pep", "Corbera", 70);

        System.out.println("iniciant 2");
        Thread.sleep(3000);
        Singleton instance2 = Singleton.getInstance("Adria", "Martinez", 24);

        System.out.println("iniciant 3");
        Thread.sleep(3000);
        Singleton instance3 = Singleton.getInstance("David", "Bargados", 37);


        System.out.println(instance1);
        System.out.println(instance2);
        System.out.println(instance3);
    }

}