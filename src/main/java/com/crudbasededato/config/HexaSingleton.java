package com.crudbasededato.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum HexaSingleton {
    INSTANCIA;

    private final Properties propiedades = new Properties();

    HexaSingleton() {
        cargarConfiguraciones("config.properties");
    }

    private void cargarConfiguraciones(String rutaArchivo) {
        try (InputStream archivo = getClass().getClassLoader().getResourceAsStream(rutaArchivo)) {
            if (archivo == null) {
                System.err.println("❌ Error cargando configuración: archivo no encontrado.");
                return;
            }
            propiedades.load(archivo);
        } catch (IOException e) {
            System.err.println("❌ Error cargando configuración: " + e.getMessage());
        }
    }

    public String get(String clave) {
        return propiedades.getProperty(clave, "No encontrado");
    }
}