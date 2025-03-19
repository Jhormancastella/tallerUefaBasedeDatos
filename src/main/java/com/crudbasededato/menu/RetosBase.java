package com.crudbasededato.menu;

import java.util.List;
import java.util.Scanner;

import com.crudbasededato.EquipoService;
import com.crudbasededato.JugadorService;
import com.crudbasededato.Validaciones;
import com.crudbasededato.model.Equipo;
import com.crudbasededato.model.jugador;

public class RetosBase {

    public static void mostrarMenuRetosBase(EquipoService equipoService, JugadorService jugadorService, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Retos Base ---");
            System.out.println("1. Listar equipos fundados después del año 2000");
            System.out.println("2. Imprimir nombres de entrenadores");
            System.out.println("3. Calcular promedio de edad de jugadores por equipo");
            System.out.println("4. Listar equipos con más de 20 victorias");
            System.out.println("5. Obtener el jugador más alto de cada equipo");
            System.out.println("6. Calcular el total de goles por equipo");
            System.out.println("7. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        listarEquiposFundadosDespuesDe2000(equipoService);
                        break;
                    case 2:
                        imprimirNombresEntrenadores(equipoService);
                        break;
                    case 3:
                        calcularPromedioEdadJugadoresPorEquipo(jugadorService);
                        break;
                    case 4:
                        listarEquiposConMasDe20Victorias(equipoService);
                        break;
                    case 5:
                        obtenerJugadorMasAltoPorEquipo(jugadorService);
                        break;
                    case 6:
                        calcularTotalGolesPorEquipo(equipoService);
                        break;
                    case 7:
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
        } while (opcion != 7);
    }

    private static void listarEquiposFundadosDespuesDe2000(EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.filtrarEquiposFundadosDespuesDe(2000);
        System.out.println("----------------------------------------");
        System.out.println("\nEquipos fundados después del año 2000:");
        System.out.println("----------------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getNombre()));
    }

    private static void imprimirNombresEntrenadores(EquipoService equipoService) {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        System.out.println("------------------------------");
        System.out.println("\nNombres de los entrenadores:");
        System.out.println("------------------------------");
        equipos.forEach(e -> System.out.println(e.getEntrenador()));
    }

    private static void calcularPromedioEdadJugadoresPorEquipo(JugadorService jugadorService) {
        List<Equipo> equipos = jugadorService.obtenerEquiposConJugadores();
        System.out.println("-----------------------------------------------");
        System.out.println("\nPromedio de edad de los jugadores por equipo:");
        System.out.println("-----------------------------------------------");
        equipos.forEach(e -> {
            double promedioEdad = jugadorService.calcularPromedioEdad(e.getJugadores());
            System.out.println("Equipo: " + e.getNombre() + ", Promedio de edad: " + promedioEdad);
        });
    }

    private static void listarEquiposConMasDe20Victorias(EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.filtrarEquiposConMasDe20Victorias();
        System.out.println("----------------------------------");
        System.out.println("\nEquipos con más de 20 victorias:");
        System.out.println("----------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getNombre()));
    }

    private static void obtenerJugadorMasAltoPorEquipo(JugadorService jugadorService) {
        List<Equipo> equipos = jugadorService.obtenerEquiposConJugadores();
        System.out.println("----------------------------------");
        System.out.println("\nJugador más alto de cada equipo:");
        System.out.println("----------------------------------");
        equipos.forEach(e -> {
            Jugador jugadorMasAlto = jugadorService.encontrarJugadorMasAlto(e.getJugadores());
            System.out.println("Equipo: " + e.getNombre() + ", Jugador más alto: " + (jugadorMasAlto != null ? jugadorMasAlto.getNombre() : "No encontrado"));
        });
    }

    private static void calcularTotalGolesPorEquipo(EquipoService equipoService) {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        System.out.println("----------------------------");
        System.out.println("\nTotal de goles por equipo:");
        System.out.println("----------------------------");
        equipos.forEach(e -> {
            int totalGoles = e.getGolesAFavor();
            System.out.println("Equipo: " + e.getNombre() + ", Total de goles: " + totalGoles);
        });
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