package com.crudbasededato.model;

public class estadisticas {
    private String pj; // Partidos jugados
    private String pg; // Partidos ganados
    private String pe; // Partidos empatados
    private String pp; // Partidos perdidos
    private String gf; // Goles a favor
    private String gc; // Goles en contra
    private String tp; // Puntos totales

    // Getters y Setters

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getGf() {
        return gf;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
}