package com.crudbasededato.application.usecase.equipo;

import java.util.List;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.domain.repository.equipoRepository;

public class equipoUseCase {
    private final equipoRepository repository;

    public equipoUseCase(equipoRepository repository) {
        this.repository = repository;
    }

    // Registrar un nuevo equipo
    public void registrarEquipo(int id, String name, String yearfoundation, String coach) {
        Equipo equipo = new Equipo(id, name, yearfoundation, coach);
        repository.guardar(equipo);
    }

    // Obtener un equipo por su ID
    public Equipo obtenerEquipo(int id) {
        return repository.buscarPorId(id);
    }

    // Listar todos los equipos
    public List<Equipo> listarEquipos() {
        return repository.listarTodos();
    }

    // Actualizar un equipo existente
    public void actualizarEquipo(int id, String name, String yearfoundation, String coach) {
        Equipo equipo = new Equipo(id, name, yearfoundation, coach);
        repository.actualizar(equipo);
    }

    // Eliminar un equipo por su ID
    public void eliminarEquipo(int id) {
        repository.eliminar(id);
    }
}