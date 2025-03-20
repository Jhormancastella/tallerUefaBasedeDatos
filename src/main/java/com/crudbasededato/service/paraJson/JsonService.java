package com.crudbasededato.service.paraJson;

import java.io.InputStream;
import java.util.List;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.infrastructure.EquiposWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonService {
    public static List<Equipo> leerEquiposDesdeJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Leer el archivo JSON desde la carpeta resources

            InputStream inputStream = JsonService.class.getClassLoader().getResourceAsStream("equipos.json");
            if (inputStream == null) {
                throw new IllegalArgumentException("Archivo JSON no encontrado");
            }

            // Deserializar el JSON a la clase EquiposWrapper
            
            EquiposWrapper wrapper = objectMapper.readValue(inputStream, EquiposWrapper.class);
            return wrapper.getEquipos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}