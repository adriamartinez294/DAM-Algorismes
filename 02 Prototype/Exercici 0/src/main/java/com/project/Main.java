package com.project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Electrodomestic> llista = new ArrayList<>();
        List<Electrodomestic> llistaCopy = new ArrayList<>();

        Rentadora rentadora1 = new Rentadora("Rentadora bosch", "gris", 700, "bosch", "A", 2000, 100);
        Rentadora rentadora2 = new Rentadora("Rentadora LG", "blanc", 400, "LG", "B", 1400, 113);
        Forn forn1 = new Forn("Forn balay", "blanc", 460, "balay", "A", 120, false);
        Forn forn2 = new Forn("Forn samsung", "gris", 790, "samsung", "B", 150, false);
        Nevera nevera1 = new Nevera("Nevera combi", "gris", 290, "combi", "A", 50, 60);
        Nevera nevera2 = new Nevera("Nevera samsung", "blanc", 290, "samsung", "B", 30, 60);

        llista.add(rentadora1);
        llista.add(rentadora2);
        llista.add(forn1);
        llista.add(forn2);
        llista.add(nevera1);
        llista.add(nevera2);


        for (Electrodomestic obj : llista) {
            llistaCopy.add(obj.clone());
        }

        System.out.println("Comparar la mateixa llista:");
        for (int i = 0; i < llista.size(); i++) {
            compare(i, llista.get(i), llista.get(i));
        }

        System.out.println("Comparar amb la llista clonada:");
        for (int i = 0; i < llista.size(); i++) {
            compare(i, llista.get(i), llistaCopy.get(i));
        }

        System.out.println("Comparar amb la llista clonada però invertida:");
        for (int i = 0; i < llista.size(); i++) {
            compare(i, llista.get(i), llistaCopy.get(llista.size() - i - 1));
        }
    }

    static void compare (int i, Poligon a, Poligon b) {
        if (a == b) {
            System.out.println(i + ": Els Electrodomestics són el mateix objecte");
        } else {
            System.out.print(i + ": Els Electrodomestics són objectes diferents - ");
            if (a.equals(b)) {
                System.out.println(i + ": Els Electrodomestics són idèntics");
            } else {
                System.out.println(i + ": Els Electrodomestics NO són identics");
            }
        }
    }
    }
}