package com.crudbasededato.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.infrastructure.database.ConnectionFactory;

public class EquipoService {

    // Método para listar todos los equipos
    public void listarEquipos() {
        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "SELECT id, name, yearfoundation, coach FROM equipos";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de equipos:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Nombre: " + rs.getString("name") +
                        ", Año de fundación: " + rs.getString("yearfoundation") +
                        ", Entrenador: " + rs.getString("coach"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar equipos: " + e.getMessage());
        }
    }

    // Método para insertar un nuevo equipo
    public void insertarEquipo(Scanner scanner) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el año de fundación: ");
        String yearFoundation = scanner.nextLine();
        System.out.print("Ingrese el nombre del entrenador: ");
        String coach = scanner.nextLine();

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "INSERT INTO equipos (name, yearfoundation, coach) VALUES (?, ?, ?)";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, yearFoundation);
            stmt.setString(3, coach);
            stmt.executeUpdate();
            System.out.println("Equipo insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar equipo: " + e.getMessage());
        }
    }

    // Método para actualizar un equipo existente
    public void actualizarEquipo(Scanner scanner) {
        System.out.print("Ingrese el ID del equipo a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo nombre del equipo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo año de fundación: ");
        String yearFoundation = scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre del entrenador: ");
        String coach = scanner.nextLine();

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "UPDATE equipos SET name = ?, yearfoundation = ?, coach = ? WHERE id = ?";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, yearFoundation);
            stmt.setString(3, coach);
            stmt.setInt(4, id);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Equipo actualizado correctamente.");
            } else {
                System.out.println("No se encontró el equipo con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar equipo: " + e.getMessage());
        }
    }

    // Método para eliminar un equipo
    public void eliminarEquipo(Scanner scanner) {
        System.out.print("Ingrese el ID del equipo a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "DELETE FROM equipos WHERE id = ?";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Equipo eliminado correctamente.");
            } else {
                System.out.println("No se encontró el equipo con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar equipo: " + e.getMessage());
        }
    }

    // Métodos adicionales (sin modificar)
    public List<Equipo> filtrarEquiposFundadosDespuesDe(int año, List<Equipo> equipos) {
        return equipos.stream()
            .filter(e -> Integer.parseInt(e.getYearfoundation()) > año)
            .toList();
    }

    public void imprimirEntrenadores(List<Equipo> equipos) {
        equipos.forEach(e -> System.out.println(e.getCoach()));
    }

    public Map<String, Integer> crearMapEquiposYGolesAFavor(List<Equipo> equipos) {
        return equipos.stream()
            .collect(Collectors.toMap(
                Equipo::getName, 
                e -> Integer.parseInt(e.getStatistics().get(0).getGf())
            ));
    }

    public List<Equipo> filtrarEquiposConEntrenadorLargo(List<Equipo> equipos) {
        return equipos.stream()
            .filter(e -> e.getCoach().length() > 10)
            .toList();
    }

    public List<Equipo> obtenerEquiposConMasDe20GolesOrdenados(List<Equipo> equipos) {
        return equipos.stream()
            .filter(e -> Integer.parseInt(e.getStatistics().get(0).getGf()) > 20)
            .sorted(Comparator.comparingInt(e -> Integer.parseInt(((Equipo) e).getStatistics().get(0).getGf())).reversed())
            .toList();
    }

    public boolean algunEquipoTieneMasDe25Puntos(List<Equipo> equipos) {
        return equipos.stream()
            .anyMatch(e -> Integer.parseInt(e.getStatistics().get(0).getTp()) > 25);
    }

    public List<Equipo> listarEquiposConMasDe15Puntos(List<Equipo> equipos) {
        return equipos.stream()
            .filter(e -> Integer.parseInt(e.getStatistics().get(0).getTp()) > 15)
            .toList();
    }

    public double obtenerPromedioGolesAFavor(List<Equipo> equipos) {
        return equipos.stream()
            .collect(Collectors.averagingInt(e -> Integer.parseInt(e.getStatistics().get(0).getGf())));
    }

    public Equipo encontrarEquipoConMasVictorias(List<Equipo> equipos) {
        return equipos.stream()
            .max(Comparator.comparingInt(e -> Integer.parseInt(e.getStatistics().get(0).getPg())))
            .orElse(null);
    }
}