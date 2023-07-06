package com.example.comupngarciahernandezfinal.modelos;

public class Carta {
    private  int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMounstro() {
        return mounstro;
    }

    public void setMounstro(String mounstro) {
        this.mounstro = mounstro;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getDefensa() {
        return defensa;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    private String mounstro;
    private String ataque;
    private String defensa;
    private double latitud;
    private double longitud;

    public Carta( String mounstro, String ataque,String defensa, double latitud, double longitud) {
        this.mounstro = mounstro;
        this.ataque = ataque;
        this.defensa = defensa;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
