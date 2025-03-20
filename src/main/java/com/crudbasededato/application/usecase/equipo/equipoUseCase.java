package com.crudbasededato.application.usecase.equipo;

import java.util.List;

import com.crudbasededato.domain.repository.equipoRepository;
import com.mysql.cj.xdevapi.Client;

public class equipoUseCase {
    private final equipoRepository repository;

    public equipoUseCase(equipoRepository repository) {
        this.repository = repository;
    }

    public void registrarequipo(int id, String nombre, String email) {
        equipos equipo = new equipo(id, nombre, email);
        repository.guardar(equipo);
    }

    public equipo obtenerequipo(int id) {
        return repository.buscarPorId(id);
    }

    public List<equipo> listarequipos() {
        return repository.listarTodos();
    }

    public void actualizarequipo(int id, String nombre, String email) {
        equipo equipo = new equipo(id, nombre, email);
        repository.actualizar(equipo);
    }

    public void eliminarequipo(int id) {
        repository.eliminar(id);
    }
}
