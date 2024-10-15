package com.project;

public class Rentadora extends Electrodomestic {
    private int revolucions;
    private int soroll;

    public Rentadora(String nom, String color, int preu, String marca, String eficiencia, int revolucions, int soroll){
        super(nom, color, preu, marca, eficiencia);
        this.revolucions = revolucions;
        this.soroll = soroll;
    }

    public Rentadora(Rentadora another) {
        super(another.getNom(), another.getColor(), another.getPreu(), another.getMarca(), another.getEficiencia());
        this.revolucions = revolucions;
        this.soroll = soroll;
    }

    @Override
    public Electrodomestic clone() {
        return new Rentadora(this);
    }
}