package com.crudbasededato.domain.repository;

import java.util.List;

import com.crudbasededato.domain.entity.Equipo;


public interface equipoRepository {
    void guardar(Equipo equipo);
    Equipo buscarPorId(int id);
    List<Equipo> listarTodos();
    void actualizar(Equipo equipo);
    void eliminar(int id);
}
