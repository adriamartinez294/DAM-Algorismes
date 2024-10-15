package com.project;

public class Forn extends Electrodomestic {
    private int temperatura;
    private boolean autoneteja;

    public Forn(int temperatura, boolean autoneteja){
        super();
        this.temperatura = temperatura;
        this.autoneteja = autoneteja;
    }

    @Override
    public Electrodomestic clone() {
        return new Forn(this);
    }
}