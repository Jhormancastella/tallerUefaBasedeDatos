package com.crudbasededato;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.infrastructure.jsonReader;
import com.crudbasededato.menu.SubmenuJSON;
import com.crudbasededato.menu.SubmenuMySQL;
import com.crudbasededato.service.EquipoService;
import com.crudbasededato.service.JugadorService;
import com.crudbasededato.service.Validaciones;

public class Main {
    private static List<Equipo> equipos;
    private static EquipoService equipoService;
    private static JugadorService jugadorService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Inicialización de dependencias
        jsonReader jsonReader = new jsonReader();
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("equipos_uefa.json");

        // Limpiar la consola
        limpiarConsola();

        if (inputStream == null) {
            System.out.println("❌ Error: Archivo JSON no encontrado en el classpath.");
            return;
        }

        equipos = jsonReader.leerEquiposDesdeJson(inputStream);
        equipoService = new EquipoService();
        jugadorService = new JugadorService();
        scanner = new Scanner(System.in);

        if (equipos != null) {
            mostrarMenuPrincipal();
        } else {
            System.out.println("❌ Error al leer el archivo JSON.");
        }
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("          🏆 Menú Principal 🏆            ");
            System.out.println("╠═════════════════════════════════════════╣");
            System.out.println("║ 1. Retos con consumo de archivo JSON    ║");
            System.out.println("║ 2. Retos con la base de datos (MySQL)   ║");
            System.out.println("║ 3. Salir                                ║");
            System.out.println("╚═════════════════════════════════════════╝");
            System.out.print("👉 Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        SubmenuJSON.mostrarSubmenuJSON(equipos, equipoService, jugadorService, scanner);
                        break;
                    case 2:
                        SubmenuMySQL.mostrarSubmenuMySQL(equipoService, jugadorService, scanner);
                        break;
                    case 3:
                        System.out.println("🚪 Saliendo del programa...");
                        pausar(3);
                        limpiarConsola();
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente de nuevo.");
                        pausar(2);
                        limpiarConsola();
                }
            } else {
                System.out.println("❌ Entrada no válida. Por favor, ingrese un número.");
                opcion = -1;
                pausar(2);
                limpiarConsola();
            }
        } while (opcion != 3);
    }

    // Método para poner una pausa
    public static void pausar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            System.out.println("⏳ Error en la pausa: " + e.getMessage());
        }
    }

    // Método para limpiar la consola
    public static void limpiarConsola() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (Exception ex) {
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
            }
        }
    }
}