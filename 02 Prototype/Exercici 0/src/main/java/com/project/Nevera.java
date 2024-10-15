package com.project;

public class Nevera extends Electrodomestic {
    private int frigories;
    private int soroll;

    public Nevera(int frigories, int soroll){
        super();
        this.frigories = frigories;
        this.soroll = soroll;
    }

    @Override
    public Electrodomestic clone() {
        return new Nevera(this);
    }

}