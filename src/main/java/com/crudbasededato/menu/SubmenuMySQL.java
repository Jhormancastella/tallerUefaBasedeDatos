package com.crudbasededato.menu;

import java.util.Scanner;

import com.crudbasededato.infrastructure.database.ConnMySql;
import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.infrastructure.database.ConnectionFactory;
import com.crudbasededato.service.EquipoService;
import com.crudbasededato.service.JugadorService;
import com.crudbasededato.service.Validaciones;
import com.crudbasededato.Main;

public class SubmenuMySQL {

    public static void mostrarSubmenuMySQL(EquipoService equipoService, JugadorService jugadorService, Scanner scanner) {
        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        ConnMySql connMySql = (ConnMySql) connectionDb;

        System.out.println("🔍 Verificando conexión a la base de datos...");
        boolean isConnected = connMySql.testConnection();

        if (!isConnected) {
            System.out.println("❌ No se pudo conectar a la base de datos. Regresando al menú principal...");
            Main.pausar(3);
            return;
        }
        limpiarConsola();
        int opcion;
        do {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("                🗃 Submenú MySQL 🗃               ");
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║ 1. Listar equipos (Leer)                       ║");
            System.out.println("║ 2. Insertar un nuevo equipo (Crear)            ║");
            System.out.println("║ 3. Actualizar un equipo existente (Actualizar) ║");
            System.out.println("║ 4. Eliminar un equipo (Eliminar)               ║");
            System.out.println("║ 5. Listar jugadores de un equipo (Leer)        ║");
            System.out.println("║ 6. Insertar un nuevo jugador (Crear)           ║");
            System.out.println("║ 7. Actualizar un jugador existente (Actualizar)║");
            System.out.println("║ 8. Eliminar un jugador (Eliminar)              ║");
            System.out.println("║ 9. Regresar al menú principal                  ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.print("👉 Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        Main.limpiarConsola();
                        System.out.println("📋 Listando equipos desde la base de datos...");
                        equipoService.listarEquipos();
                        break;
                    case 2:
                        Main.limpiarConsola();
                        System.out.println("➕ Insertando un nuevo equipo...");
                        equipoService.insertarEquipo(scanner);
                        break;
                    case 3:
                        Main.limpiarConsola();
                        System.out.println("🔄 Actualizando un equipo existente...");
                        equipoService.actualizarEquipo(scanner);
                        break;
                    case 4:
                        Main.limpiarConsola();
                        System.out.println("🗑 Eliminando un equipo...");
                        equipoService.eliminarEquipo(scanner);
                        break;
                    case 5:
                        Main.limpiarConsola();
                        System.out.println("📋 Listando jugadores de un equipo...");
                        jugadorService.listarJugadores(scanner);
                        break;
                    case 6:
                        Main.limpiarConsola();
                        System.out.println("➕ Insertando un nuevo jugador...");
                        jugadorService.insertarJugador(scanner);
                        break;
                    case 7:
                        Main.limpiarConsola();
                        System.out.println("🔄 Actualizando un jugador existente...");
                        jugadorService.actualizarJugador(scanner);
                        break;
                    case 8:
                        Main.limpiarConsola();
                        System.out.println("🗑 Eliminando un jugador...");
                        jugadorService.eliminarJugador(scanner);
                        break;
                    case 9:
                        System.out.println("🔙 Regresando al menú principal...");
                        Main.pausar(2);
                        Main.limpiarConsola();
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente de nuevo.");
                        Main.pausar(2);
                        Main.limpiarConsola();
                }
            } else {
                System.out.println("❌ Entrada no válida. Por favor, ingrese un número.");
                opcion = -1;
                Main.pausar(2);
                Main.limpiarConsola();
            }
        } while (opcion != 9);
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