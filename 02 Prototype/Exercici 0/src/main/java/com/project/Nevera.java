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

    @Override
    public boolean equals(Object object2) {
        // Comprova si són la mateixa referència
        if (this == object2) return true;

        if (!(object2 instanceof Electrodomestic) || !super.equals(object2)) return false;

        // Comprova si els dos objectes són exactament de la mateixa classe
        if (!this.getClass().equals(object2.getClass())) return false;

        Nevera cast2 = (Nevera) object2;
        return cast2.frigories == frigories && cast2.soroll == soroll;
    }

}