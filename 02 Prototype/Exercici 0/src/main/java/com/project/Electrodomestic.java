package com.project;

public abstract class Electrodomestic {
    private String nom;
    private String color;
    private int preu;
    private String marca;
    private String eficiencia;

    public Electrodomestic(String nom, String color, int preu, String marca, String eficiencia) {
        this.nom = nom;
        this.color = color;
        this.preu = preu;
        this.marca = marca;
        this.eficiencia = eficiencia;
    }

    public abstract Electrodomestic clone();

    @Override
    public boolean equals(Object object2) {
        if (!(object2 instanceof Electrodomestic)) return false;
        Electrodomestic cast2 = (Electrodomestic) object2;
        return cast2.nom.equals(nom) && cast2.color.equals(color) && cast2.preu == preu && cast2.marca.equals(marca) && cast2.eficiencia.equals(eficiencia);
    }

    public String getNom() {
        return nom;
    }

    public int getPreu() {
        return preu;
    }

    public String getColor() {
        return color;
    }

    public String getEficiencia() {
        return eficiencia;
    }

    public String getMarca() {
        return marca;
    }
}