package com.crudbasededato;

public class Validaciones {

    /**
     * Verifica si una cadena de texto es un número válido.
     *
     * @param input La cadena de texto a validar.
     * @return `true` si la cadena es un número válido, `false` en caso contrario.
     */
    public static boolean esNumero(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false; // Si la entrada es nula o está vacía, no es un número.
        }

        try {
            // Intenta convertir la cadena a un número entero.
            Integer.parseInt(input);
            return true; // Si no hay excepción, es un número válido.
        } catch (NumberFormatException e) {
            // Si se lanza una excepción, no es un número válido.
            return false;
        }
    }
}