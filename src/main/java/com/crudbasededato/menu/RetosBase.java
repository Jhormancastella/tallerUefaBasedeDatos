package com.crudbasededato.menu;

import java.util.List;
import java.util.Scanner;

import com.crudbasededato.service.EquipoService;
import com.crudbasededato.service.JugadorService;
import com.crudbasededato.service.Validaciones;
import com.crudbasededato.domain.entity.*;;

public class RetosBase {

    public static void mostrarMenuRetosBase(List<Equipo> equipos, EquipoService equipoService,
            JugadorService jugadorService, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Retos Base ---");
            System.out.println("1. Listar equipos fundados después del año 2000");
            System.out.println("2. Imprimir nombres de entrenadores");
            System.out.println("3. Calcular promedio de edad de jugadores por equipo");
            System.out.println("4. Listar equipos con más de 20 victorias");
            System.out.println("5. Obtener el jugador más alto de cada equipo");
            System.out.println("6. Calcular el total de goles por equipo");
            System.out.println("7. Regresar al menu principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        listarEquiposFundadosDespuesDe2000(equipos, equipoService);
                        break;
                    case 2:
                        imprimirNombresEntrenadores(equipos);
                        break;
                    case 3:
                        calcularPromedioEdadJugadoresPorEquipo(equipos, jugadorService);
                        break;
                    case 4:
                        listarEquiposConMasDe20Victorias(equipos);
                        break;
                    case 5:
                        obtenerJugadorMasAltoPorEquipo(equipos, jugadorService);
                        break;
                    case 6:
                        calcularTotalGolesPorEquipo(equipos);
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

    private static void listarEquiposFundadosDespuesDe2000(List<Equipo> equipos, EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.filtrarEquiposFundadosDespuesDe(2000, equipos);
        System.out.println("----------------------------------------");
        System.out.println("\nEquipos fundados después del año 2000:");
        System.out.println("----------------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getName()));
    }

    private static void imprimirNombresEntrenadores(List<Equipo> equipos) {
        System.out.println("------------------------------");
        System.out.println("\nNombres de los entrenadores:");
        System.out.println("------------------------------");
        equipos.forEach(e -> System.out.println(e.getCoach()));
    }

    private static void calcularPromedioEdadJugadoresPorEquipo(List<Equipo> equipos, JugadorService jugadorService) {
        System.out.println("-----------------------------------------------");
        System.out.println("\nPromedio de edad de los jugadores por equipo:");
        System.out.println("-----------------------------------------------");
        equipos.forEach(e -> {
            double promedioEdad = jugadorService.calcularPromedioEdad(e.getPlayers());
            System.out.println("Equipo: " + e.getName() + ", Promedio de edad: " + promedioEdad);
        });
    }

    private static void listarEquiposConMasDe20Victorias(List<Equipo> equipos) {
        System.out.println("----------------------------------");
        System.out.println("\nEquipos con más de 20 victorias:");
        System.out.println("----------------------------------");
        equipos.stream()
                .filter(e -> Integer.parseInt(e.getStatistics().get(0).getPg()) > 20)
                .forEach(e -> System.out.println(e.getName()));
    }

    private static void obtenerJugadorMasAltoPorEquipo(List<Equipo> equipos, JugadorService jugadorService) {
        System.out.println("----------------------------------");
        System.out.println("\nJugador más alto de cada equipo:");
        System.out.println("----------------------------------");
        equipos.forEach(e -> {
            jugador jugadorMasAlto = jugadorService.encontrarJugadorMasAlto(e.getPlayers());
            System.out.println("Equipo: " + e.getName() + ", Jugador más alto: " +jugadorMasAlto.getName() + ", Altura: " + jugadorMasAlto.getHeight() + " cm" + (jugadorMasAlto != null ? jugadorMasAlto.getName() : "No encontrado"));
        });
    }

    private static void calcularTotalGolesPorEquipo(List<Equipo> equipos) {
        System.out.println("----------------------------");
        System.out.println("\nTotal de goles por equipo:");
        System.out.println("----------------------------");
        equipos.forEach(e -> {
            int totalGoles = Integer.parseInt(e.getStatistics().get(0).getGf());
            System.out.println("Equipo: " + e.getName() + ", Total de goles: " + totalGoles);
        });
    }

    // Método para poner una pausa
    public static void pausar(int segundos) {
        try {
            Thread.sleep(segundos * 1000); // Convierte segundos a milisegundos
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
