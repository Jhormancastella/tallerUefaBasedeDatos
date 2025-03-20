package com.crudbasededato.infrastructure;

import java.util.List;

import com.crudbasededato.domain.entity.Equipo;

public class EquiposWrapper {
    private List<Equipo> equipos;

    // Getter y Setter
    
    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }
}