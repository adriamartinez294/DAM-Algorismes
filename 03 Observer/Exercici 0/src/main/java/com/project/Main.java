package com.project;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main {

    public static void main (String[] args) {

        Producte p0 = new Producte(0, "Llibre");
        Producte p1 = new Producte(1, "Boli");
        Producte p2 = new Producte(2, "Rotulador");
        Producte p3 = new Producte(3, "Carpeta");
        Producte p4 = new Producte(4, "Motxilla");


        Entregues entregues = new Entregues();
        Magatzem magatzem = new Magatzem(entregues);

        PropertyChangeListener producteNom = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("Producte ha canviat el nom de '%s' a '%s'\n",
                                  evt.getOldValue(), evt.getNewValue());                
            }
        };
        p0.addPropertyChangeListener("nom", producteNom);
        p1.addPropertyChangeListener("nom", producteNom);
        p2.addPropertyChangeListener("nom", producteNom);
        p3.addPropertyChangeListener("nom", producteNom);
        p4.addPropertyChangeListener("nom", producteNom);

        PropertyChangeListener producteId = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("Producte ha canviat la ID de '%s' a '%s'\n",
                                  evt.getOldValue(), evt.getNewValue());                
            }
        };

        p0.addPropertyChangeListener("id", producteId);
        p1.addPropertyChangeListener("id", producteId);
        p2.addPropertyChangeListener("id", producteId);
        p3.addPropertyChangeListener("id", producteId);
        p4.addPropertyChangeListener("id", producteId);

        PropertyChangeListener magatzemAdd = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("S’ha afegit el producte amb id %s al magatzem, capacitat: %s\n",
                                  evt.getOldValue(), evt.getNewValue());                
            }
        };

        magatzem.addPropertyChangeListener("addproducte", magatzemAdd);

        PropertyChangeListener magatzemRemove = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("S’ha esborrat el producte amb id %s al magatzem, capacitat: %s\n",
                                  evt.getOldValue(), evt.getNewValue());                
            }
        };

        magatzem.addPropertyChangeListener("removeproducte", magatzemRemove);

        PropertyChangeListener entreguesAdd = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("S'ha mogut el producte amd id %s del magatzem cap a les entregues\n",
                                  evt.getOldValue());                
            }
        };

        entregues.addPropertyChangeListener("addentrega", entreguesAdd);

        PropertyChangeListener entreguesRemove = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.printf("S'ha entregat el producte amb id %s\n",
                                  evt.getOldValue());                
            }
        };

        entregues.addPropertyChangeListener("removeentrega", entreguesRemove);

        

        p0.setId(5);
        p0.setNom("Llibreta");
        p1.setNom("Boli");

        magatzem.addProducte(p0);
        magatzem.addProducte(p1);
        magatzem.addProducte(p2);
        magatzem.addProducte(p3);
        magatzem.addProducte(p4);

        magatzem.removeProducte(2);
        magatzem.removeProducte(3);
        magatzem.removeProducte(4);

        entregues.removeProducte(2);
        entregues.removeProducte(3);

        System.out.println(magatzem);
        System.out.println(entregues);
    }
}