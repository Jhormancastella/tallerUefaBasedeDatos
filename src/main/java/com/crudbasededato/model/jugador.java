package com.crudbasededato.model;

public class jugador {
    private Long id;
    private String nombre;
    private String posicion;
    private Equipo equipo;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setId(int int1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

    public void setAltura(double double1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAltura'");
    }

    public int getEdad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEdad'");
    }

    public double getAltura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAltura'");
    }
}