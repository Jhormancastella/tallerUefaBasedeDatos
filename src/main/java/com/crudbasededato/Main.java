package com.crudbasededato;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.infrastructure.jsonReader;
import com.crudbasededato.infrastructure.database.ConnMySql;
import com.crudbasededato.infrastructure.database.ConnectionDb;
import com.crudbasededato.infrastructure.database.ConnectionFactory;
import com.crudbasededato.menu.RetosBase;
import com.crudbasededato.menu.adicionales;
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
            System.out.println("Error: Archivo JSON no encontrado en el classpath.");
            return;
        }

        equipos = jsonReader.leerEquiposDesdeJson(inputStream);
        equipoService = new EquipoService();
        jugadorService = new JugadorService();
        scanner = new Scanner(System.in);

        if (equipos != null) {
            mostrarMenuPrincipal();
        } else {
            System.out.println("Error al leer el archivo JSON.");
        }
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("------------------------------------");
            System.out.println("---------- Menú Principal ----------");
            System.out.println("------------------------------------");
            System.out.println("1. Retos con consumo de archivo JSON");
            System.out.println("2. Retos con la base de datos (MySQL)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        mostrarSubmenuJSON();
                        break;
                    case 2:
                        mostrarSubmenuMySQL();
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        pausar(3);
                        limpiarConsola();
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
        } while (opcion != 3);
    }

    private static void mostrarSubmenuJSON() {
        int opcion;
        do {
            System.out.println("------------------------------");
            System.out.println("-------- Submenú JSON --------");
            System.out.println("------------------------------");
            System.out.println("1. Retos Base");
            System.out.println("2. Retos Adicionales");
            System.out.println("3. Regresar al menu principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        RetosBase.mostrarMenuRetosBase(equipos, equipoService, jugadorService, scanner);
                        break;
                    case 2:
                        adicionales.mostrarMenuRetosAdicionales(equipos, equipoService, jugadorService, scanner);
                        break;
                    case 3:
                        System.out.println("Regresando al menú principal...");
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
        } while (opcion != 3);
    }

    private static void mostrarSubmenuMySQL() {

        // Crear una instancia de ConnMySql usando ConnectionFactory

        ConnectionDb connectionDb = ConnectionFactory.crearConexion();
        ConnMySql connMySql = (ConnMySql) connectionDb;
    
        // Verificar la conexión a la base de datos

        System.out.println("Verificando conexión a la base de datos...");
        boolean isConnected = connMySql.testConnection();
    
        if (!isConnected) {
            System.out.println("No se pudo conectar a la base de datos. Regresando al menú principal...");

            pausar(3); // Pausa para que el usuario lea el mensaje
            
            return; // Regresar al menú principal
        }
    
        // Si la conexión es exitosa, mostrar el submenú

        int opcion;
        do {
            System.out.println("-----------------------------------------------");
            System.out.println("---------------- Submenú MySQL ----------------");
            System.out.println("-----------------------------------------------");
            System.out.println("1. Listar equipos (Leer)");
            System.out.println("2. Insertar un nuevo equipo (Crear)");
            System.out.println("3. Actualizar un equipo existente (Actualizar)");
            System.out.println("4. Eliminar un equipo (Eliminar)");
            System.out.println("5. Listar jugadores de un equipo (Leer)");
            System.out.println("6. Insertar un nuevo jugador (Crear)");
            System.out.println("7. Actualizar un jugador existente (Actualizar)");
            System.out.println("8. Eliminar un jugador (Eliminar)");
            System.out.println("9. Regresar al menu principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();
    
            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);
    
                switch (opcion) {
                    case 1:
                        // Lógica para listar equipos (Leer) // Método para listar equipos
                        System.out.println("---------------------------------------------");
                        System.out.println("\n Listando equipos desde la base de datos...");
                        System.out.println("---------------------------------------------");
                        equipoService.listarEquipos();
                        break;
                    case 2:
                        // Lógica para insertar un nuevo equipo (Crear) // Método para insertar un equipo
                        System.out.println("-------------------------------");
                        System.out.println("\nInsertando un nuevo equipo...");
                        System.out.println("-------------------------------");
                        equipoService.insertarEquipo(scanner); 
                        break;
                    case 3:
                        // Lógica para actualizar un equipo existente (Actualizar) // Método para actualizar un equipo
                        System.out.println("-------------------------------------");
                        System.out.println("\nActualizando un equipo existente...");
                        System.out.println("-------------------------------------");
                        equipoService.actualizarEquipo(scanner); 
                        break;
                    case 4:
                        // Lógica para eliminar un equipo (Eliminar) // Método para eliminar un equipo
                        System.out.println("-----------------------");
                        System.out.println("\nEliminando un equipo...");
                        System.out.println("-----------------------");
                        equipoService.eliminarEquipo(scanner); 
                        break;
                    case 5:
                        // Lógica para listar jugadores de un equipo (Leer) // Método para listar jugadores
                        System.out.println("----------------------------------");
                        System.out.println("\nListando jugadores de un equipo.");
                        System.out.println("----------------------------------");
                        jugadorService.listarJugadores(scanner); 
                        break;
                    case 6:
                        // Lógica para insertar un nuevo jugador (Crear) // Método para insertar un jugador

                        System.out.println("Insertando un nuevo jugador...");
                        jugadorService.insertarJugador(scanner); 
                        break;
                    case 7:
                        // Lógica para actualizar un jugador existente (Actualizar) // Método para actualizar un jugador
                        
                        System.out.println("Actualizando un jugador existente...");
                        jugadorService.actualizarJugador(scanner); 
                        break;
                    case 8:
                        // Lógica para eliminar un jugador (Eliminar) // Método para eliminar un jugador

                        System.out.println("Eliminando un jugador...");
                        jugadorService.eliminarJugador(scanner); 
                        break;
                    case 9:
                        System.out.println("Regresando al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                opcion = -1;
            }
        } while (opcion != 9);
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