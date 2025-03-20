package com.crudbasededato.domain.entity;

import java.util.List;

public class Equipo {
    private int id;
    private String name;
    private String yearfoundation;
    private List<estadisticas> statistics; 
    private List<jugador> players;
    private String coach;

    // Constructor por defecto (requerido por Jackson)
    public Equipo() {
    }

    // // Constructor completo
    public Equipo(int id, String name, String yearfoundation, List<estadisticas> statistics, List<jugador> players, String coach) {
        this.id = id;
        this.name = name;
        this.yearfoundation = yearfoundation;
        this.statistics = statistics;
        this.players = players;
        this.coach = coach;
    }

    // Constructor sin estadísticas y jugadores
    public Equipo(int id, String name, String yearfoundation, String coach) {
        this.id = id;
        this.name = name;
        this.yearfoundation = yearfoundation;
        this.coach = coach;
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("| %-4d | %-20s | %-12s | %-20s |\n", id, name, yearfoundation, coach));

        if (statistics != null && !statistics.isEmpty()) {
            sb.append("Estadísticas:\n");
            for (estadisticas estadistica : statistics) {
                sb.append(estadistica.toString()).append("\n");
            }
        }

        if (players != null && !players.isEmpty()) {
            sb.append("Jugadores:\n");
            for (jugador jugador : players) {
                sb.append(jugador.toString()).append("\n");
            }
        }

        return sb.toString();
    }
}