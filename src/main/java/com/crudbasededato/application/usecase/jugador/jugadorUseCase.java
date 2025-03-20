package com.crudbasededato.application.usecase.jugador;

import java.util.List;

import com.crudbasededato.domain.entity.jugador;
import com.crudbasededato.domain.repository.jugadorRespository;

public class jugadorUseCase {
    private final jugadorRespository repository;

    public jugadorUseCase(jugadorRespository repository) {
        this.repository = repository;
    }

    // Registrar un nuevo jugador
    public void registrarJugador(int id, int equipoId, String dorsal, String name, String nationality, String age, String height, String weight, String position) {
        jugador jugador = new jugador(id, equipoId, dorsal, name, nationality, age, height, weight, position);
        repository.guardar(jugador);
    }

    // Obtener un jugador por su ID
    public jugador obtenerJugador(int id) {
        return repository.buscarPorId(id);
    }

    // Listar todos los jugadores
    public List<jugador> listarJugadores() {
        return repository.listarTodos();
    }

    // Actualizar un jugador existente
    public void actualizarJugador(int id, int equipoId, String dorsal, String name, String nationality, String age, String height, String weight, String position) {
        jugador jugador = new jugador(id, equipoId, dorsal, name, nationality, age, height, weight, position);
        repository.actualizar(jugador);
    }

    // Eliminar un jugador por su ID
    public void eliminarJugador(int id) {
        repository.eliminar(id);
    }
}