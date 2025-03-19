package com.crudbasededato.menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.crudbasededato.EquipoService;
import com.crudbasededato.JugadorService;
import com.crudbasededato.Validaciones;
import com.crudbasededato.model.Equipo;
import com.crudbasededato.model.jugador;

public class adicionales {

    public static void mostrarMenuRetosAdicionales(EquipoService equipoService, JugadorService jugadorService, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n------------------------------------");
            System.out.println("\n--- Submenú de Retos Adicionales ---");
            System.out.println("\n------------------------------------");
            System.out.println("1. Listar equipos con más de 15 puntos");
            System.out.println("2. Obtener el promedio de goles a favor por equipo");
            System.out.println("3. Mostrar el equipo con más victorias");
            System.out.println("4. Encontrar el jugador más alto de todos los equipos");
            System.out.println("5. Contar cuántos jugadores son delanteros");
            System.out.println("6. Obtener los nombres de los entrenadores de equipos que han empatado al menos un partido");
            System.out.println("7. Crear un Map con los nombres de los equipos y sus goles a favor");
            System.out.println("8. Listar jugadores brasileños ordenados por edad");
            System.out.println("9. Filtrar equipos cuyo entrenador tiene más de 10 caracteres en su nombre");
            System.out.println("10. Determinar si algún equipo tiene más de 25 puntos");
            System.out.println("11. Agrupar jugadores por posición y contar cuántos hay en cada una");
            System.out.println("12. Obtener equipos con más de 20 goles a favor ordenados de mayor a menor");
            System.out.println("13. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        listarEquiposConMasDe15Puntos(equipoService);
                        break;
                    case 2:
                        obtenerPromedioGolesAFavor(equipoService);
                        break;
                    case 3:
                        mostrarEquipoConMasVictorias(equipoService);
                        break;
                    case 4:
                        encontrarJugadorMasAltoDeTodosLosEquipos(jugadorService);
                        break;
                    case 5:
                        contarJugadoresDelanteros(jugadorService);
                        break;
                    case 6:
                        obtenerEntrenadoresDeEquiposConEmpates(equipoService);
                        break;
                    case 7:
                        crearMapEquiposYGolesAFavor(equipoService);
                        break;
                    case 8:
                        listarJugadoresBrasilenosOrdenadosPorEdad(jugadorService);
                        break;
                    case 9:
                        filtrarEquiposConEntrenadorLargo(equipoService);
                        break;
                    case 10:
                        determinarSiAlgunEquipoTieneMasDe25Puntos(equipoService);
                        break;
                    case 11:
                        agruparJugadoresPorPosicion(jugadorService);
                        break;
                    case 12:
                        obtenerEquiposConMasDe20GolesOrdenados(equipoService);
                        break;
                    case 13:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                opcion = -1;
                pausar(2);
                limpiarConsola();
            }
        } while (opcion != 13);
    }

    private static void listarEquiposConMasDe15Puntos(EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.filtrarEquiposConMasDe15Puntos();
        System.out.println("-------------------------------");
        System.out.println("\nEquipos con más de 15 puntos:");
        System.out.println("-------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getNombre()));
    }

    private static void obtenerPromedioGolesAFavor(EquipoService equipoService) {
        double promedio = equipoService.obtenerPromedioGolesAFavor();
        System.out.println("--------------------------------------");
        System.out.println("\nPromedio de goles a favor: " + promedio);
        System.out.println("--------------------------------------");
    }

    private static void mostrarEquipoConMasVictorias(EquipoService equipoService) {
        Equipo equipo = equipoService.encontrarEquipoConMasVictorias();
        System.out.println("------------------------------");
        System.out.println("\nEquipo con más victorias: " + (equipo != null ? equipo.getNombre() : "No encontrado"));
        System.out.println("------------------------------");
    }

    private static void encontrarJugadorMasAltoDeTodosLosEquipos(JugadorService jugadorService) {
        jugador jugador = jugadorService.encontrarJugadorMasAltoDeTodosLosEquipos();
        System.out.println("-------------------------------------");
        System.out.println("\nJugador más alto de todos los equipos: " + (jugador != null ? jugador.getNombre() : "No encontrado"));
        System.out.println("-------------------------------------");
    }

    private static void contarJugadoresDelanteros(JugadorService jugadorService) {
        long totalDelanteros = jugadorService.contarJugadoresDelanteros();
        System.out.println("---------------------");
        System.out.println("\nTotal de delanteros: " + totalDelanteros);
        System.out.println("---------------------");
    }

    private static void obtenerEntrenadoresDeEquiposConEmpates(EquipoService equipoService) {
        List<String> entrenadores = equipoService.obtenerEntrenadoresDeEquiposConEmpates();
        System.out.println("---------------------------------------------------------------");
        System.out.println("\nEntrenadores de equipos que han empatado al menos un partido:");
        System.out.println("---------------------------------------------------------------");
        entrenadores.forEach(System.out::println);
    }

    private static void crearMapEquiposYGolesAFavor(EquipoService equipoService) {
        Map<String, Integer> map = equipoService.crearMapEquiposYGolesAFavor();
        System.out.println("----------------------------------");
        System.out.println("\nMapa de equipos y goles a favor:");
        System.out.println("----------------------------------");
        map.forEach((nombre, goles) -> System.out.println(nombre + ": " + goles));
    }

    private static void listarJugadoresBrasilenosOrdenadosPorEdad(JugadorService jugadorService) {
        List<jugador> jugadores = jugadorService.listarJugadoresBrasilenosOrdenadosPorEdad();
        System.out.println("------------------------------------------");
        System.out.println("\nJugadores brasileños ordenados por edad:");
        System.out.println("------------------------------------------");
        jugadores.forEach(j -> System.out.println(j.getNombre() + " - Edad: " + j.getEdad()));
    }

    private static void filtrarEquiposConEntrenadorLargo(EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.filtrarEquiposConEntrenadorLargo();
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nEquipos cuyo entrenador tiene más de 10 caracteres en su nombre:");
        System.out.println("------------------------------------------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getNombre()));
    }

    private static void determinarSiAlgunEquipoTieneMasDe25Puntos(EquipoService equipoService) {
        boolean tieneMasDe25Puntos = equipoService.algunEquipoTieneMasDe25Puntos();
        System.out.println("--------------------------------------");
        System.out.println("\n¿Algún equipo tiene más de 25 puntos? " + (tieneMasDe25Puntos ? "Sí" : "No"));
        System.out.println("--------------------------------------");
    }

    private static void agruparJugadoresPorPosicion(JugadorService jugadorService) {
        Map<String, Long> jugadoresPorPosicion = jugadorService.agruparJugadoresPorPosicion();
        System.out.println("-----------------------------------");
        System.out.println("\nJugadores agrupados por posición:");
        System.out.println("-----------------------------------");
        jugadoresPorPosicion.forEach((posicion, cantidad) -> System.out.println(posicion + ": " + cantidad));
    }

    private static void obtenerEquiposConMasDe20GolesOrdenados(EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.obtenerEquiposConMasDe20GolesOrdenados();
        System.out.println("-----------------------------------------------------------------");
        System.out.println("\nEquipos con más de 20 goles a favor ordenados de mayor a menor:");
        System.out.println("-----------------------------------------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getNombre() + " - Goles: " + e.getGolesAFavor()));
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