package com.crudbasededato.infrastructure.persistence.jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crudbasededato.domain.entity.jugador;
import com.crudbasededato.domain.repository.jugadorRespository;
import com.crudbasededato.infrastructure.database.ConnectionDb;

public class jugadorRepositoryImpl implements jugadorRespository {
    private final ConnectionDb connection;

    public jugadorRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(jugador jugador) {
        String sql = "INSERT INTO jugadores (equipo_id, dorsal, name, nationality, age, height, weight, position) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, jugador.getEquipoId());
            stmt.setString(2, jugador.getDorsal());
            stmt.setString(3, jugador.getName());
            stmt.setString(4, jugador.getNationality());
            stmt.setString(5, jugador.getAge());
            stmt.setString(6, jugador.getHeight());
            stmt.setString(7, jugador.getWeight());
            stmt.setString(8, jugador.getPosition());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public jugador buscarPorId(int id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new jugador(
                    rs.getInt("id"),
                    rs.getInt("equipo_id"),
                    rs.getString("dorsal"),
                    rs.getString("name"),
                    rs.getString("nationality"),
                    rs.getString("age"),
                    rs.getString("height"),
                    rs.getString("weight"),
                    rs.getString("position")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<jugador> listarTodos() {
        List<jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores";
        try (Connection conexion = connection.getConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                jugadores.add(new jugador(
                    rs.getInt("id"),
                    rs.getInt("equipo_id"),
                    rs.getString("dorsal"),
                    rs.getString("name"),
                    rs.getString("nationality"),
                    rs.getString("age"),
                    rs.getString("height"),
                    rs.getString("weight"),
                    rs.getString("position")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    @Override
    public void actualizar(jugador jugador) {
        String sql = "UPDATE jugadores SET equipo_id = ?, dorsal = ?, name = ?, nationality = ?, age = ?, height = ?, weight = ?, position = ? WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, jugador.getEquipoId());
            stmt.setString(2, jugador.getDorsal());
            stmt.setString(3, jugador.getName());
            stmt.setString(4, jugador.getNationality());
            stmt.setString(5, jugador.getAge());
            stmt.setString(6, jugador.getHeight());
            stmt.setString(7, jugador.getWeight());
            stmt.setString(8, jugador.getPosition());
            stmt.setInt(9, jugador.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}