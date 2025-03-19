package com.crudbasededato;

import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.model.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Equipo> filtrarEquiposConMasDe20Victorias() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE victorias > 20";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getLong("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setCiudad(rs.getString("ciudad"));
                equipo.setVictorias(rs.getInt("victorias"));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar equipos con más de 20 victorias: " + e.getMessage());
        }
        return equipos;
    }
    public List<Equipo> filtrarEquiposFundadosDespuesDe(int año) {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE año_fundacion > ?";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, año);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Equipo equipo = new Equipo();
                    equipo.setId(rs.getLong("id"));
                    equipo.setNombre(rs.getString("nombre"));
                    equipo.setCiudad(rs.getString("ciudad"));
                    equipo.setAñoFundacion(rs.getInt("año_fundacion"));
                    equipos.add(equipo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar equipos fundados después de " + año + ": " + e.getMessage());
        }
        return equipos;
    }
    public List<Equipo> obtenerEquiposConMasDe20GolesOrdenados() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE goles_a_favor > 20 ORDER BY goles_a_favor DESC";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getLong("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setCiudad(rs.getString("ciudad"));
                equipo.setGolesAFavor(rs.getInt("goles_a_favor"));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos con más de 20 goles ordenados: " + e.getMessage());
        }
        return equipos;
    }
    public boolean algunEquipoTieneMasDe25Puntos() {
        String sql = "SELECT COUNT(*) AS total FROM equipos WHERE puntos > 25";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si algún equipo tiene más de 25 puntos: " + e.getMessage());
        }
        return false;
    }
    public List<Equipo> filtrarEquiposConEntrenadorLargo() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE LENGTH(entrenador) > 10";
        try (Connection conn = conexionDb.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setId(rs.getLong("id"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setCiudad(rs.getString("ciudad"));
                equipo.setEntrenador(rs.getString("entrenador"));
                equipos.add(equipo);
            }
        } catch (SQLException e) {
            System.err.println("Error al filtrar equipos con entrenador largo: " + e.getMessage());
        }
        return equipos;
    }
    public Map<String, Integer> crearMapEquiposYGolesAFavor() {
    Map<String, Integer> map = new HashMap<>();
    String sql = "SELECT nombre, goles_a_favor FROM equipos";
    try (Connection conn = conexionDb.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            map.put(rs.getString("nombre"), rs.getInt("goles_a_favor"));
        }
    } catch (SQLException e) {
        System.err.println("Error al crear el mapa de equipos y goles a favor: " + e.getMessage());
    }
    return map;
}
public List<String> obtenerEntrenadoresDeEquiposConEmpates() {
    List<String> entrenadores = new ArrayList<>();
    String sql = "SELECT DISTINCT entrenador FROM equipos WHERE empates > 0";
    try (Connection conn = conexionDb.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            entrenadores.add(rs.getString("entrenador"));
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los entrenadores de equipos con empates: " + e.getMessage());
    }
    return entrenadores;
}
public Equipo encontrarEquipoConMasVictorias() {
    Equipo equipo = null;
    String sql = "SELECT * FROM equipos ORDER BY victorias DESC LIMIT 1";
    try (Connection conn = conexionDb.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
            equipo = new Equipo();
            equipo.setId(rs.getLong("id"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setCiudad(rs.getString("ciudad"));
            equipo.setVictorias(rs.getInt("victorias"));
        }
    } catch (SQLException e) {
        System.err.println("Error al encontrar el equipo con más victorias: " + e.getMessage());
    }
    return equipo;
}
public double obtenerPromedioGolesAFavor() {
    double promedio = 0;
    String sql = "SELECT AVG(goles_a_favor) AS promedio FROM equipos";
    try (Connection conn = conexionDb.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
            promedio = rs.getDouble("promedio");
        }
    } catch (SQLException e) {
        System.err.println("Error al calcular el promedio de goles a favor: " + e.getMessage());
    }
    return promedio;
}
public List<Equipo> filtrarEquiposConMasDe15Puntos() {
    List<Equipo> equipos = new ArrayList<>();
    String sql = "SELECT * FROM equipos WHERE puntos > 15";
    try (Connection conn = conexionDb.getConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
            Equipo equipo = new Equipo();
            equipo.setId(rs.getLong("id"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setCiudad(rs.getString("ciudad"));
            equipo.setPuntos(rs.getInt("puntos"));
            equipos.add(equipo);
        }
    } catch (SQLException e) {
        System.err.println("Error al filtrar equipos con más de 15 puntos: " + e.getMessage());
    }
    return equipos;
}
}