package com.project;

public class Forn extends Electrodomestic {
    private int temperatura;
    private boolean autoneteja;

    public Forn(String nom, String color, int preu, String marca, String eficiencia, int temperatura, boolean autoneteja){
        super(nom, color, preu, marca, eficiencia);
        this.temperatura = temperatura;
        this.autoneteja = autoneteja;
    }

    public Forn(Forn another) {
        super(another.getNom(), another.getColor(), another.getPreu(), another.getMarca(), another.getEficiencia());
        this.temperatura = temperatura;
        this.autoneteja = autoneteja;
    }

    @Override
    public Electrodomestic clone() {
        return new Forn(this);
    }
}