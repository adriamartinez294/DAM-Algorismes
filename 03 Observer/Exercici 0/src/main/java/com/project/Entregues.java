package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Entregues {
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(this);

    private ArrayList<Producte> productes;

    public Entregues() {
        this.productes = new ArrayList<>();
    }

    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(name, listener);
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(name, listener);
    }

    

    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public void addProducte(Producte a) {
        productes.add(a);
        llistaObservers.firePropertyChange("addentrega", a.getId(), a.getNom());
    }

    public void removeProducte(int id) {
        for (int i = 0; i < productes.size(); i++) {
            Producte obj = productes.get(i);
            if (obj.getId() == id) {
                productes.remove(i);  // Elimina el elemento en el índice actual
                llistaObservers.firePropertyChange("removeentrega", obj.getId(), obj.getNom());
                break;  // Detener el ciclo después de eliminar el objeto
            }
        }
    }
    
    public String toString() {
        String a = "";
        for (Producte producte : productes) {
            a = a + producte + "\n";
        }
        return a;
    }
}