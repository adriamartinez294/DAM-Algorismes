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
        this.temperatura = another.temperatura;
        this.autoneteja = another.autoneteja;
    }

    @Override
    public Electrodomestic clone() {
        return new Forn(this);
    }

    @Override
    public boolean equals(Object object2) {
        // Comprova si són la mateixa referència
        if (this == object2) return true;

        if (!(object2 instanceof Electrodomestic) || !super.equals(object2)) return false;

        // Comprova si els dos objectes són exactament de la mateixa classe
        if (!this.getClass().equals(object2.getClass())) return false;

        Forn cast2 = (Forn) object2;
        return cast2.temperatura == temperatura && cast2.autoneteja == autoneteja;
    }
}