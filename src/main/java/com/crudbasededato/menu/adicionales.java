package com.crudbasededato.menu;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.domain.entity.jugador;
import com.crudbasededato.service.EquipoService;
import com.crudbasededato.service.JugadorService;
import com.crudbasededato.service.Validaciones;

public class adicionales {

    public static void mostrarMenuRetosAdicionales(List<Equipo> equipos, EquipoService equipoService, JugadorService jugadorService, Scanner scanner) {
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
            System.out.println("13. Regresar al menu principal");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        listarEquiposConMasDe15Puntos(equipos, equipoService);
                        break;
                    case 2:
                        obtenerPromedioGolesAFavor(equipos, equipoService);
                        break;
                    case 3:
                        mostrarEquipoConMasVictorias(equipos, equipoService);
                        break;
                    case 4:
                        encontrarJugadorMasAltoDeTodosLosEquipos(equipos, jugadorService);
                        break;
                    case 5:
                        contarJugadoresDelanteros(equipos, jugadorService);
                        break;
                    case 6:
                        obtenerEntrenadoresDeEquiposConEmpates(equipos, jugadorService);
                        break;
                    case 7:
                        crearMapEquiposYGolesAFavor(equipos, equipoService);
                        break;
                    case 8:
                        listarJugadoresBrasilenosOrdenadosPorEdad(equipos, jugadorService);
                        break;
                    case 9:
                        filtrarEquiposConEntrenadorLargo(equipos, equipoService);
                        break;
                    case 10:
                        determinarSiAlgunEquipoTieneMasDe25Puntos(equipos, equipoService);
                        break;
                    case 11:
                        agruparJugadoresPorPosicion(equipos, jugadorService);
                        break;
                    case 12:
                        obtenerEquiposConMasDe20GolesOrdenados(equipos, equipoService);
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

    private static void listarEquiposConMasDe15Puntos(List<Equipo> equipos, EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.listarEquiposConMasDe15Puntos(equipos);
        System.out.println("-------------------------------");
        System.out.println("\nEquipos con más de 15 puntos:");
        System.out.println("-------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getName()));
    }

    private static void obtenerPromedioGolesAFavor(List<Equipo> equipos, EquipoService equipoService) {
        double promedio = equipoService.obtenerPromedioGolesAFavor(equipos);
        System.out.println("--------------------------------------");
        System.out.println("\nPromedio de goles a favor: " + promedio);
        System.out.println("--------------------------------------");
    }

    private static void mostrarEquipoConMasVictorias(List<Equipo> equipos, EquipoService equipoService) {
        Equipo equipo = equipoService.encontrarEquipoConMasVictorias(equipos);
        System.out.println("------------------------------");
        System.out.println("\nEquipo con más victorias: " + (equipo != null ? equipo.getName() : "No encontrado"));
        System.out.println("------------------------------");
    }

    private static void encontrarJugadorMasAltoDeTodosLosEquipos(List<Equipo> equipos, JugadorService jugadorService) {
        jugador jugador = jugadorService.encontrarJugadorMasAltoDeTodosLosEquipos(equipos);
        System.out.println("-------------------------------------");
        System.out.println("\nJugador más alto de todos los equipos: " + (jugador != null ? jugador.getName() : "No encontrado"));
        System.out.println("-------------------------------------");
    }

    private static void contarJugadoresDelanteros(List<Equipo> equipos, JugadorService jugadorService) {
        long totalDelanteros = jugadorService.contarJugadoresDelanteros(equipos);
        System.out.println("---------------------");
        System.out.println("\nTotal de delanteros: " + totalDelanteros);
        System.out.println("---------------------");
    }

    private static void obtenerEntrenadoresDeEquiposConEmpates(List<Equipo> equipos, JugadorService jugadorService) {
        List<String> entrenadores = jugadorService.obtenerEntrenadoresDeEquiposConEmpates(equipos);
        System.out.println("---------------------------------------------------------------");
        System.out.println("\nEntrenadores de equipos que han empatado al menos un partido:");
        System.out.println("---------------------------------------------------------------");
        entrenadores.forEach(System.out::println);
    }

    private static void crearMapEquiposYGolesAFavor(List<Equipo> equipos, EquipoService equipoService) {
        Map<String, Integer> map = equipoService.crearMapEquiposYGolesAFavor(equipos);
        System.out.println("----------------------------------");
        System.out.println("\nMapa de equipos y goles a favor:");
        System.out.println("----------------------------------");
        map.forEach((nombre, goles) -> System.out.println(nombre + ": " + goles));
    }

    private static void listarJugadoresBrasilenosOrdenadosPorEdad(List<Equipo> equipos, JugadorService jugadorService) {
        List<jugador> jugadores = jugadorService.listarJugadoresBrasilenosOrdenadosPorEdad(equipos);
        System.out.println("------------------------------------------");
        System.out.println("\nJugadores brasileños ordenados por edad:");
        System.out.println("------------------------------------------");
        jugadores.forEach(j -> System.out.println(j.getName() + " - Edad: " + j.getAge()));
    }

    private static void filtrarEquiposConEntrenadorLargo(List<Equipo> equipos, EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.filtrarEquiposConEntrenadorLargo(equipos);
        System.out.println("------------------------------------------------------------------");
        System.out.println("\nEquipos cuyo entrenador tiene más de 10 caracteres en su nombre:");
        System.out.println("------------------------------------------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getName()));
    }

    private static void determinarSiAlgunEquipoTieneMasDe25Puntos(List<Equipo> equipos, EquipoService equipoService) {
        boolean tieneMasDe25Puntos = equipoService.algunEquipoTieneMasDe25Puntos(equipos);
        System.out.println("--------------------------------------");
        System.out.println("\n¿Algún equipo tiene más de 25 puntos? " + (tieneMasDe25Puntos ? "Sí" : "No"));
        System.out.println("--------------------------------------");
    }

    private static void agruparJugadoresPorPosicion(List<Equipo> equipos, JugadorService jugadorService) {
        Map<String, Long> jugadoresPorPosicion = jugadorService.agruparJugadoresPorPosicion(equipos);
        System.out.println("-----------------------------------");
        System.out.println("\nJugadores agrupados por posición:");
        System.out.println("-----------------------------------");
        jugadoresPorPosicion.forEach((posicion, cantidad) -> System.out.println(posicion + ": " + cantidad));
    }

    private static void obtenerEquiposConMasDe20GolesOrdenados(List<Equipo> equipos, EquipoService equipoService) {
        List<Equipo> equiposFiltrados = equipoService.obtenerEquiposConMasDe20GolesOrdenados(equipos);
        System.out.println("-----------------------------------------------------------------");
        System.out.println("\nEquipos con más de 20 goles a favor ordenados de mayor a menor:");
        System.out.println("-----------------------------------------------------------------");
        equiposFiltrados.forEach(e -> System.out.println(e.getName() + " - Goles: " + e.getStatistics().get(0).getGf()));
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