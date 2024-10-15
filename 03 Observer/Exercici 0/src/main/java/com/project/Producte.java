package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Producte {
    private PropertyChangeSupport llistaObservers = new PropertyChangeSupport(this);

    int id;
    String nom;

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
        String oldValue = this.nom;
        if (oldValue != newValue) {
            this.nom = newValue;
            llistaObservers.firePropertyChange("nom", oldValue, newValue);
        }
    }

    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(name, listener);
    }

    @Override
    public String toString() {
        return "Id: " + this.id + ", Nom: " + this.nom;
    }
}