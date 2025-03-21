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
import com.crudbasededato.domain.entity.jugador;
import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.infrastructure.database.ConnectionFactory;
import com.crudbasededato.Main;

public class JugadorService {

    // Método para listar jugadores de un equipo
    public void listarJugadores(Scanner scanner) {
        System.out.print("Ingrese el ID del equipo: ");
        int equipoId = Integer.parseInt(scanner.nextLine());

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "SELECT dorsal, name, nationality, age, height, weight, position FROM jugadores WHERE equipo_id = ?";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, equipoId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Lista de jugadores:");
            while (rs.next()) {
                System.out.println("Dorsal: " + rs.getString("dorsal") +
                        ", Nombre: " + rs.getString("name") +
                        ", Nacionalidad: " + rs.getString("nationality") +
                        ", Edad: " + rs.getString("age") +
                        ", Altura: " + rs.getString("height") +
                        ", Peso: " + rs.getString("weight") +
                        ", Posición: " + rs.getString("position"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar jugadores: " + e.getMessage());
        }
    }

    // Método para insertar un nuevo jugador
    public void insertarJugador(Scanner scanner) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║       🆕 Crear Nuevo Jugador 🆕      ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ Ingrese 0 en cualquier momento para ║");
        System.out.println("║ regresar al submenú MySQL.         ║");
        System.out.println("╚════════════════════════════════════╝");

        System.out.print("👉 Ingrese el ID del equipo: ");
        String equipoIdInput = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (equipoIdInput.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        int equipoId = Integer.parseInt(equipoIdInput);

        System.out.print("👉 Ingrese el dorsal: ");
        String dorsal = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (dorsal.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        System.out.print("👉 Ingrese el nombre: ");
        String name = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (name.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        System.out.print("👉 Ingrese la nacionalidad: ");
        String nationality = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (nationality.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        System.out.print("👉 Ingrese la edad: ");
        String age = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (age.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        System.out.print("👉 Ingrese la altura: ");
        String height = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (height.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        System.out.print("👉 Ingrese el peso: ");
        String weight = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (weight.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        System.out.print("👉 Ingrese la posición: ");
        String position = scanner.nextLine();

        // Si el usuario ingresa 0, regresar al submenú MySQL
        if (position.equals("0")) {
            System.out.println("🔙 Regresando al submenú MySQL...");
            Main.pausar(2);
            Main.limpiarConsola();
            return;
        }

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "INSERT INTO jugadores (equipo_id, dorsal, name, nationality, age, height, weight, position) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, equipoId);
            stmt.setString(2, dorsal);
            stmt.setString(3, name);
            stmt.setString(4, nationality);
            stmt.setString(5, age);
            stmt.setString(6, height);
            stmt.setString(7, weight);
            stmt.setString(8, position);
            stmt.executeUpdate();
            System.out.println("✅ Jugador insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("❌ Error al insertar jugador: " + e.getMessage());
        } finally {
            Main.pausar(2);
            Main.limpiarConsola();
        }
    }

    // Método para actualizar un jugador existente
    public void actualizarJugador(Scanner scanner) {
        System.out.print("Ingrese el ID del jugador a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo dorsal: ");
        String dorsal = scanner.nextLine();
        System.out.print("Ingrese el nuevo nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la nueva nacionalidad: ");
        String nationality = scanner.nextLine();
        System.out.print("Ingrese la nueva edad: ");
        String age = scanner.nextLine();
        System.out.print("Ingrese la nueva altura: ");
        String height = scanner.nextLine();
        System.out.print("Ingrese el nuevo peso: ");
        String weight = scanner.nextLine();
        System.out.print("Ingrese la nueva posición: ");
        String position = scanner.nextLine();

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "UPDATE jugadores SET dorsal = ?, name = ?, nationality = ?, age = ?, height = ?, weight = ?, position = ? WHERE id = ?";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dorsal);
            stmt.setString(2, name);
            stmt.setString(3, nationality);
            stmt.setString(4, age);
            stmt.setString(5, height);
            stmt.setString(6, weight);
            stmt.setString(7, position);
            stmt.setInt(8, id);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Jugador actualizado correctamente.");
            } else {
                System.out.println("No se encontró el jugador con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar jugador: " + e.getMessage());
        }
    }

    // Método para eliminar un jugador
    public void eliminarJugador(Scanner scanner) {
        System.out.print("Ingrese el ID del jugador a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        String query = "DELETE FROM jugadores WHERE id = ?";

        try (Connection conn = connectionDb.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Jugador eliminado correctamente.");
            } else {
                System.out.println("No se encontró el jugador con ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar jugador: " + e.getMessage());
        }
    }

    // Métodos adicionales (sin modificar)
    public double calcularPromedioEdad(List<jugador> jugadores) {
        return jugadores.stream()
            .mapToInt(j -> Integer.parseInt(j.getAge()))
            .average()
            .orElse(0);
    }

    public jugador encontrarJugadorMasAlto(List<jugador> jugadores) {
        return jugadores.stream()
            .max(Comparator.comparingInt(j -> Integer.parseInt(j.getHeight())))
            .orElse(null);
    }

    public jugador encontrarJugadorMasAltoDeTodosLosEquipos(List<Equipo> equipos) {
        return equipos.stream()
                .flatMap(e -> e.getPlayers().stream())
                .max(Comparator.comparingInt(j -> Integer.parseInt(j.getHeight())))
                .orElse(null);
    }

    public long contarJugadoresDelanteros(List<Equipo> equipos) {
        return equipos.stream()
                .flatMap(e -> e.getPlayers().stream())
                .filter(j -> "Delantero".equals(j.getPosition()))
                .count();
    }

    public List<String> obtenerEntrenadoresDeEquiposConEmpates(List<Equipo> equipos) {
        return equipos.stream()
                .filter(e -> Integer.parseInt(e.getStatistics().get(0).getPe()) > 0)
                .map(Equipo::getCoach)
                .toList();
    }

    public List<jugador> listarJugadoresBrasilenosOrdenadosPorEdad(List<Equipo> equipos) {
        return equipos.stream()
                .flatMap(e -> e.getPlayers().stream())
                .filter(j -> "Brasileño".equals(j.getNationality()))
                .sorted(Comparator.comparingInt(j -> Integer.parseInt(j.getAge())))
                .toList();
    }

    public Map<String, Long> agruparJugadoresPorPosicion(List<Equipo> equipos) {
        return equipos.stream()
                .flatMap(e -> e.getPlayers().stream())
                .collect(Collectors.groupingBy(jugador::getPosition, Collectors.counting()));
    }
}