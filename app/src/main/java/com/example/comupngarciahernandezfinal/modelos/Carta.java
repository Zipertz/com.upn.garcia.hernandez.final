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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private String imagen;
    private double latitud;
    private double longitud;

    public Carta( int id, String mounstro, String ataque,String defensa,String imagen, double latitud, double longitud) {

        this.id = id;
        this.mounstro = mounstro;
        this.ataque = ataque;
        this.imagen = imagen;
        this.defensa = defensa;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
