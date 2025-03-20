package com.crudbasededato.infrastructure.persistence.equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.domain.repository.equipoRepository;
import com.crudbasededato.infrastructure.database.ConnectionDb;

public class equipoRepositoryImpl implements equipoRepository {
    private final ConnectionDb connection;

    public equipoRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Equipo equipo) {
        String sql = "INSERT INTO equipos (name, yearfoundation, coach) VALUES (?, ?, ?)";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, equipo.getName());
            stmt.setString(2, equipo.getYearfoundation());
            stmt.setString(3, equipo.getCoach());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Equipo buscarPorId(int id) {
        String sql = "SELECT * FROM equipos WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Equipo(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("yearfoundation"),
                    rs.getString("coach")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Equipo> listarTodos() {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos";
        try (Connection conexion = connection.getConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                equipos.add(new Equipo(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("yearfoundation"),
                    rs.getString("coach")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    @Override
    public void actualizar(Equipo equipo) {
        String sql = "UPDATE equipos SET name = ?, yearfoundation = ?, coach = ? WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, equipo.getName());
            stmt.setString(2, equipo.getYearfoundation());
            stmt.setString(3, equipo.getCoach());
            stmt.setInt(4, equipo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM equipos WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}