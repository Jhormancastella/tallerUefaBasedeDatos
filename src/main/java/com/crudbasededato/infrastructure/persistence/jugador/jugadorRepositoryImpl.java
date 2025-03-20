package com.crudbasededato.infrastructure.persistence.jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crudbasededato.domain.repository.equipoRepository;
import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.domain.entity.jugador;

public class jugadorRepositoryImpl implements ProductRepository, com.crudbasededato.domain.repository.ProductRepository {
    private final ConnectionDb connection;
    
    public ProductRepositoryImpl(ConnectionDb connection) {
        this.connection = connection;
    }

    @Override
    public void guardar(Product producto) {
        String sql = "INSERT INTO product (id, name, stock) VALUES (?, ?, ?)";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getId());
            stmt.setString(2, producto.getName());
            stmt.setInt(3, producto.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product buscarPorId(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> listarTodos() {
        List<Product> productos = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conexion = connection.getConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("stock")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public void actualizar(Product producto) {
        String sql = "UPDATE product SET name = ?, stock = ? WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getName());
            stmt.setInt(2, producto.getStock());
            stmt.setString(3, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conexion = connection.getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}