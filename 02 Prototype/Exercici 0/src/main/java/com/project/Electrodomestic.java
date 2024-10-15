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