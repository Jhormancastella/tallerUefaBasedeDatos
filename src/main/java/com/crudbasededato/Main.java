package com.crudbasededato;

import java.util.List;
import java.util.Scanner;

import com.crudbasededato.infrastructure.database.ConnectMysqlFactory;
import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.menu.RetosBase;
import com.crudbasededato.menu.adicionales;
import com.crudbasededato.model.Equipo;
import com.crudbasededato.EquipoService;
import com.crudbasededato.JugadorService;

public class Main {
    private static EquipoService equipoService;
    private static JugadorService jugadorService;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Inicialización de la conexión a la base de datos
        ConnectionDb conexionDb = ConnectMysqlFactory.crearConexion();

        // Inicialización de servicios
        equipoService = new EquipoService(conexionDb);
        jugadorService = new JugadorService(conexionDb);
        scanner = new Scanner(System.in);

        // Limpiar la consola
        limpiarConsola();

        // Mostrar el menú principal
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("------------------------");
            System.out.println("---- Menú Principal ----");
            System.out.println("------------------------");
            System.out.println("1. Retos Base");
            System.out.println("2. Retos Adicionales");
            System.out.println("3. Operaciones CRUD");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        RetosBase.mostrarMenuRetosBase(equipoService, jugadorService, scanner);
                        break;
                    case 2:
                        adicionales.mostrarMenuRetosAdicionales(null, equipoService, jugadorService, scanner);
                        break;
                    case 3:
                        mostrarMenuCRUD();
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                opcion = -1;
                pausar(3);
                limpiarConsola();
            }
        } while (opcion != 4);
    }

    private static void mostrarMenuCRUD() {
        int opcion;
        do {
            System.out.println("------------------------");
            System.out.println("---- Menú CRUD ----");
            System.out.println("------------------------");
            System.out.println("1. Crear Equipo");
            System.out.println("2. Leer Equipos");
            System.out.println("3. Actualizar Equipo");
            System.out.println("4. Eliminar Equipo");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        crearEquipo();
                        break;
                    case 2:
                        leerEquipos();
                        break;
                    case 3:
                        actualizarEquipo();
                        break;
                    case 4:
                        eliminarEquipo();
                        break;
                    case 5:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                opcion = -1;
                pausar(3);
                limpiarConsola();
            }
        } while (opcion != 5);
    }

    private static void crearEquipo() {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la ciudad del equipo: ");
        String ciudad = scanner.nextLine();

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setCiudad(ciudad);

        equipoService.crearEquipo(equipo);
        System.out.println("Equipo creado exitosamente.");
    }

    private static void leerEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
        } else {
            for (Equipo equipo : equipos) {
                System.out.println("ID: " + equipo.getId() + ", Nombre: " + equipo.getNombre() + ", Ciudad: " + equipo.getCiudad());
            }
        }
    }

    private static void actualizarEquipo() {
        leerEquipos();
        System.out.print("Ingrese el ID del equipo a actualizar: ");
        Long id = Long.parseLong(scanner.nextLine());

        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if (equipo != null) {
            System.out.print("Ingrese el nuevo nombre del equipo: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese la nueva ciudad del equipo: ");
            String ciudad = scanner.nextLine();

            equipo.setNombre(nombre);
            equipo.setCiudad(ciudad);

            equipoService.actualizarEquipo(equipo);
            System.out.println("Equipo actualizado exitosamente.");
        } else {
            System.out.println("Equipo no encontrado.");
        }
    }

    private static void eliminarEquipo() {
        leerEquipos();
        System.out.print("Ingrese el ID del equipo a eliminar: ");
        Long id = Long.parseLong(scanner.nextLine());

        equipoService.eliminarEquipo(id);
        System.out.println("Equipo eliminado exitosamente.");
    }

    // Método para poner una pausa
    public static void pausar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            System.out.println("Error en la pausa: " + e.getMessage());
        }
    }

    // Método para limpiar la consola
    private static void limpiarConsola() {
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