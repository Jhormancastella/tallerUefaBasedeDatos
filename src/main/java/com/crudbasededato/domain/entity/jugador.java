package com.crudbasededato.domain.entity;

// import com.fasterxml.jackson.annotation.JsonProperty;

public class jugador {
    private int id; // ID del jugador
    private int equipoId; // ID del equipo al que pertenece
    private String dorsal;
    private String name;
    private String nationality;
    private String age;
    private String height;
    private String weight;
    private String position;

    // Constructor por defecto (necesario para Jackson)
    public jugador() {}

    // Constructor completo
    public jugador(int id, int equipoId, String dorsal, String name, String nationality, String age, String height, String weight, String position) {
        this.id = id;
        this.equipoId = equipoId;
        this.dorsal = dorsal;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.position = position;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}