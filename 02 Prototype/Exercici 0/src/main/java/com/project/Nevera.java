package com.project;

public class Nevera extends Electrodomestic {
    private int frigories;
    private int soroll;

    public Nevera(String nom, String color, int preu, String marca, String eficiencia, int frigories, int soroll){
        super(nom, color, preu, marca, eficiencia);
        this.frigories = frigories;
        this.soroll = soroll;
    }

    public Nevera(Nevera another) {
        super(another.getNom(), another.getColor(), another.getPreu(), another.getMarca(), another.getEficiencia());
        this.frigories = another.frigories;
        this.soroll = another.soroll;
    }

    @Override
    public Electrodomestic clone() {
        return new Nevera(this);
    }

}