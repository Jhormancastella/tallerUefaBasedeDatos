package com.crudbasededato.domain.repository;

import java.util.List;

import com.crudbasededato.domain.entity.jugador;

public interface jugadorRespository {
    void guardar(jugador jugador);
    jugador buscarPorId(int id);
    List<jugador> listarTodos();
    void actualizar(jugador jugador);
    void eliminar(int id);
}
