package com.crudbasededato.domain.entity;

public class estadisticas {
    private String pj; // Partidos jugados
    private String pg; // Partidos ganados
    private String pe; // Partidos empatados
    private String pp; // Partidos perdidos
    private String gf; // Goles a favor
    private String gc; // Goles en contra
    private String tp; // Total de puntos

    // Constructor
    public estadisticas(String pj, String pg, String pe, String pp, String gf, String gc, String tp) {
        this.pj = pj;
        this.pg = pg;
        this.pe = pe;
        this.pp = pp;
        this.gf = gf;
        this.gc = gc;
        this.tp = tp;
    }

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

    // Método toString para darle un mejor formato en consola
    @Override
    public String toString() {
        return String.format("| PJ: %-3s | PG: %-3s | PE: %-3s | PP: %-3s | GF: %-3s | GC: %-3s | TP: %-3s |",
                pj, pg, pe, pp, gf, gc, tp);
    }
}