package com.crudbasededato.domain.entity;

import java.util.List;

public class Equipo {
    private int id;
    private String name;
    private String yearfoundation;
    private List<estadisticas> statistics; 
    private List<jugador> players;
    private String coach;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearfoundation() {
        return yearfoundation;
    }

    public void setYearfoundation(String yearfoundation) {
        this.yearfoundation = yearfoundation;
    }

    public List<estadisticas> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<estadisticas> statistics) {
        this.statistics = statistics;
    }

    public List<jugador> getPlayers() {
        return players;
    }

    public void setPlayers(List<jugador> players) {
        this.players = players;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    // Método toString para darle un mejor formato en consola 

    @Override
    public String toString() {
        return String.format("| %-4d | %-20s | %-30s |", id, name, yearfoundation,statistics,players,coach);
    }
}
