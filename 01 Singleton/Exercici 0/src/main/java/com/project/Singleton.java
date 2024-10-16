package com.project;

public final class Singleton {
    private static Singleton instance;
    private String nom;
    private String cognom;
    private int edat;

    private Singleton(String nom, String cognom, int edat) {
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
    }

    public static Singleton getInstance(String nom, String cognom, int edat) {
        if (instance == null) {
            instance = new Singleton(nom, cognom, edat);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Cognom: " + cognom + ", Edat: " + edat;
    }
}