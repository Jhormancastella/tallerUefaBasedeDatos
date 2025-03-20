package com.crudbasededato.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.crudbasededato.config.HexaSingleton;

public class ConnMySql implements ConnectionDb {
    @Override
    public Connection getConexion() throws SQLException {
        HexaSingleton config = HexaSingleton.INSTANCIA;
        String url = config.get("db.url");
        String usuario = config.get("db.user");
        String password = config.get("db.password");

        return DriverManager.getConnection(url, usuario, password);
    }

    // Método para probar la conexión
    public boolean testConnection() {
        try (Connection connection = getConexion()) {
            System.out.println("Conexión exitosa a la base de datos: " + connection.getCatalog());
            return true;
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return false;
        }
    }
}