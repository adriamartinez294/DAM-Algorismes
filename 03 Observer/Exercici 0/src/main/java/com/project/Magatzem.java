package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Magatzem {
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(this);

    int id;
    String nom

    public Producte(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int newValue) {
        int oldValue = this.id;
        if (oldValue != newValue) {
            this.id = newValue;
            llistaObservers.firePropertyChange("id", oldValue, newValue);
        }
    }

    public void setNom(String newValue) {
        int oldValue = this.nom;
        if (oldValue != newValue) {
            this.nom = newValue;
            llistaObservers.firePropertyChange("id", oldValue, newValue);
        }
    }
}