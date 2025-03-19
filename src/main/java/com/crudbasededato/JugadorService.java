package com.crudbasededato;

import com.crudbasededato.infrastructure.database.ConnectionDb;

import com.crudbasededato.model.Equipo;
import com.crudbasededato.model.jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JugadorService {

    private final ConnectionDb conexionDb;

    public JugadorService(ConnectionDb conexionDb) {
        this.conexionDb = conexionDb;
    }

    /**
     * Crea un nuevo jugador en la base de datos.
     *
     * @param jugador El jugador a crear.
     */
    public void crearJugador(jugador jugador) {
        String sql = "INSERT INTO jugadores (nombre, posicion, edad, altura, equipo_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getPosicion());
            pstmt.setInt(3, jugador.getEdad());
            pstmt.setDouble(4, jugador.getAltura());
            pstmt.setLong(5, jugador.getEquipo().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear el jugador: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los jugadores de la base de datos.
     *
     * @return Una lista de jugadores.
     */
    public List<jugador> obtenerTodosLosJugadores() {
        List<jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                jugador jugador = new jugador();
                jugador.setId(rs.getLong("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setPosicion(rs.getString("posicion"));
                jugador.setId(rs.getInt("edad"));
                jugador.setAltura(rs.getDouble("altura"));
                // Aquí deberías obtener el equipo asociado al jugador (si es necesario).
                jugadores.add(jugador);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los jugadores: " + e.getMessage());
        }
        return jugadores;
    }

    /**
     * Obtiene un jugador por su ID.
     *
     * @param id El ID del jugador.
     * @return El jugador encontrado, o `null` si no existe.
     */
    public jugador obtenerJugadorPorId(Long id) {
        jugador jugador = null;
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    jugador = new jugador();
                    jugador.setId(rs.getLong("id"));
                    jugador.setNombre(rs.getString("nombre"));
                    jugador.setPosicion(rs.getString("posicion"));
                    jugador.setId(rs.getInt("edad"));
                    jugador.setAltura(rs.getDouble("altura"));
                    // Aquí deberías obtener el equipo asociado al jugador (si es necesario).
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el jugador: " + e.getMessage());
        }
        return jugador;
    }

    /**
     * Actualiza un jugador en la base de datos.
     *
     * @param jugador El jugador a actualizar.
     */
    public void actualizarJugador(jugador jugador) {
        String sql = "UPDATE jugadores SET nombre = ?, posicion = ?, edad = ?, altura = ?, equipo_id = ? WHERE id = ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getPosicion());
            pstmt.setInt(3, jugador.getEdad());
            pstmt.setDouble(4, jugador.getAltura());
            pstmt.setLong(5, jugador.getEquipo().getId());
            pstmt.setLong(6, jugador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el jugador: " + e.getMessage());
        }
    }

    /**
     * Elimina un jugador de la base de datos.
     *
     * @param id El ID del jugador a eliminar.
     */
    public void eliminarJugador(Long id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el jugador: " + e.getMessage());
        }
    }

    /**
     * Encuentra el jugador más alto de todos los equipos.
     *
     * @return El jugador más alto, o `null` si no hay jugadores.
     */
    public jugador encontrarJugadorMasAltoDeTodosLosEquipos() {
        jugador jugadorMasAlto = null;
        String sql = "SELECT * FROM jugadores ORDER BY altura DESC LIMIT 1";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                jugadorMasAlto = new jugador();
                jugadorMasAlto.setId(rs.getLong("id"));
                jugadorMasAlto.setNombre(rs.getString("nombre"));
                jugadorMasAlto.setPosicion(rs.getString("posicion"));
                jugadorMasAlto.setId(rs.getInt("edad"));
                jugadorMasAlto.setAltura(rs.getDouble("altura"));
                // Aquí deberías obtener el equipo asociado al jugador (si es necesario).
            }
        } catch (SQLException e) {
            System.err.println("Error al encontrar el jugador más alto: " + e.getMessage());
        }
        return jugadorMasAlto;
    }

    /**
     * Cuenta cuántos jugadores son delanteros.
     *
     * @return El número de delanteros.
     */
    public long contarJugadoresDelanteros() {
        long totalDelanteros = 0;
        String sql = "SELECT COUNT(*) AS total FROM jugadores WHERE posicion = 'Delantero'";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                totalDelanteros = rs.getLong("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al contar los delanteros: " + e.getMessage());
        }
        return totalDelanteros;
    }

    public List<jugador> listarJugadoresBrasilenosOrdenadosPorEdad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarJugadoresBrasilenosOrdenadosPorEdad'");
    }

    public Map<String, Long> agruparJugadoresPorPosicion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agruparJugadoresPorPosicion'");
    }

    public List<Equipo> obtenerEquiposConJugadores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerEquiposConJugadores'");
    }

    public double calcularPromedioEdad(Object jugadores) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularPromedioEdad'");
    }

    public jugador encontrarJugadorMasAlto(Object jugadores) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'encontrarJugadorMasAlto'");
    }
}