package com.crudbasededato;

import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.model.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoService {
    private final ConnectionDb conexionDb;

    public EquipoService(ConnectionDb conexionDb) {
        this.conexionDb = conexionDb;
    }

    public void crearEquipo(Equipo equipo) {
        String sql = "INSERT INTO equipos (nombre, ciudad) VALUES (?, ?)";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getCiudad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al crear el equipo: " + e.getMessage());
        }
    }

    public List<Equipo> obtenerTodosLosEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getLong("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setCiudad(rs.getString("ciudad"));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los equipos: " + e.getMessage());
        }
        return equipos;
    }

    public Equipo obtenerEquipoPorId(Long id) {
        Equipo equipo = null;
        String sql = "SELECT * FROM equipos WHERE id = ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    equipo = new Equipo();
                    equipo.setId(rs.getLong("id"));
                    equipo.setNombre(rs.getString("nombre"));
                    equipo.setCiudad(rs.getString("ciudad"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el equipo: " + e.getMessage());
        }
        return equipo;
    }

    public void actualizarEquipo(Equipo equipo) {
        String sql = "UPDATE equipos SET nombre = ?, ciudad = ? WHERE id = ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getCiudad());
            pstmt.setLong(3, equipo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el equipo: " + e.getMessage());
        }
    }

    public void eliminarEquipo(Long id) {
        String sql = "DELETE FROM equipos WHERE id = ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar el equipo: " + e.getMessage());
        }
    }
}