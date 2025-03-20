package com.crudbasededato.service;

public class Validaciones {

    public static boolean esNumero(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esTextoValido(String input) {
        return input.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");
    }

    public static boolean esEmailValido(String input) {
        return input.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        
    }
    
}
