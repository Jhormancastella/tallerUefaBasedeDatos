package com.crudbasededato.menu;

import java.util.List;
import java.util.Scanner;

import com.crudbasededato.domain.entity.Equipo;
import com.crudbasededato.service.EquipoService;
import com.crudbasededato.service.JugadorService;
import com.crudbasededato.service.Validaciones;
import com.crudbasededato.Main;

public class SubmenuJSON {
   
    public static void mostrarSubmenuJSON(List<Equipo> equipos, EquipoService equipoService, JugadorService jugadorService, Scanner scanner) {
        int opcion;
        limpiarConsola();
        do {
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("          🗂 Submenú JSON 🗂           ");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║ 1. Retos Base                       ║");
            System.out.println("║ 2. Retos Adicionales                ║");
            System.out.println("║ 3. Regresar al menú principal       ║");
            System.out.println("╚═════════════════════════════════════╝");
            System.out.print("👉 Seleccione una opción: ");
            String input = scanner.nextLine();

            if (Validaciones.esNumero(input)) {
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        Main.limpiarConsola();
                        RetosBase.mostrarMenuRetosBase(equipos, equipoService, jugadorService, scanner);
                        break;
                    case 2:
                        Main.limpiarConsola();
                        adicionales.mostrarMenuRetosAdicionales(equipos, equipoService, jugadorService, scanner);
                        break;
                    case 3:
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
        } while (opcion != 3);
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