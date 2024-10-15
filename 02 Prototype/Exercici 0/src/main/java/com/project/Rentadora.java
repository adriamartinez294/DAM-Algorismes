package com.project;

public class Rentadora extends Electrodomestic {
    private int revolucions;
    private int soroll;

    public Rentadora(int revolucions, int soroll){
        super();
        this.revolucions = revolucions;
        this.soroll = soroll;
    }

    @Override
    public Electrodomestic clone() {
        return new Rentadora(this);
    }
}