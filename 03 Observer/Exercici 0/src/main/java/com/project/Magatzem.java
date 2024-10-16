package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Magatzem {
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(this);
    private Entregues entregues;

    private int capacitat;
    private ArrayList<Producte> productes;
    public Magatzem(Entregues entregues) {
        this.capacitat = 10;
        this.productes = new ArrayList<>();
        this.entregues = entregues;
    }

    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(name, listener);
    }

    public void removePropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(name, listener);
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int newValue) {
        int oldValue = this.capacitat;
        if (oldValue != newValue) {
            this.capacitat = newValue;
            llistaObservers.firePropertyChange("capacitat", oldValue, newValue);
        }
    }

    public ArrayList<Producte> getProductes() {
        return productes;
    }

    public void addProducte(Producte a) {
        if (capacitat > 0) {
            productes.add(a);
            this.capacitat = this.capacitat - 1;
            llistaObservers.firePropertyChange("addproducte", a.getId(), this.capacitat);
        }
    }

    public void removeProducte(int id) {
        for (int i = 0; i < productes.size(); i++) {
            Producte obj = productes.get(i);
            if (obj.getId() == id) {
                productes.remove(i);  // Elimina el elemento en el índice actual
                this.capacitat = this.capacitat + 1;
                entregues.addProducte(obj);
                llistaObservers.firePropertyChange("removeproducte", obj.getId(), this.capacitat);
                break;  // Detener el ciclo después de eliminar el objeto
            }
        }
    }

    @Override
    public String toString() {
        String a = "";
        for (Producte producte : productes) {
            a = a + producte + "\n";
        }
        return a;
    }
    

}