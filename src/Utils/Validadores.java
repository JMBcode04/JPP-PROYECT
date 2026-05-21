/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Excepciones.ElDatoIntroducidoEsIncorrecto;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR JORGE
 * @author jorge
 */
public class Validadores {

    // SUB MENU JUGADORES/EQUIPOS
    // Valida que el nombre no este vacio, tenga al menos 3 caracteres y no contenga digitos
    public static boolean validarNombre(String nombre) {
        if (nombre.isEmpty()) {
            System.err.println("El nombre esta vacio");
            //throw new ElDatoIntroducidoEsIncorrecto("El nombre del jugador no puede estar vacío.");
            return false;
        }
        if (nombre.length() < 3) {
            System.err.println("Londitud menor que 3");
            return false;
        }

        for (int i = 0; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i))) {
                System.err.println("La cadena no puede tener dígitos");
                return false;
            }
        }
        return true;
    }

    // Misma logica que validarNombre: sin digitos, no vacio y minimo 3 caracteres
    public static boolean validarNacionalidad(String nacionalidad) {
        if (nacionalidad.isEmpty()) {
            System.err.println("El nombre esta vacio");
            return false;
        }
        if (nacionalidad.length() < 3) {
            return false;
        }

        for (int i = 0; i < nacionalidad.length(); i++) {
            if (Character.isDigit(nacionalidad.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Valida que la posicion no este vacia, tenga al menos 3 letras y no contenga numeros
    public static boolean validarPosicion(String posicion) {
        if (posicion.isEmpty()) {
            System.err.println("El nombre esta vacio");
            return false;
        }
        if (posicion.length() < 3) {
            return false;
        }

        for (int i = 0; i < posicion.length(); i++) {
            if (Character.isDigit(posicion.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Verifica que el ano sea positivo y no mayor al ano actual
    public static boolean añoFundacion(int añoFundacion) {

        if (añoFundacion < 0) {
            System.err.println("No puede ser un numero negativo");
            return false;
        }

        if (añoFundacion > 2026) {
            System.err.println("El año introducido todavia no existe");
            return false;
        }

        return true;
    }

    // Valida que el lugar de sede no este vacio, tenga al menos 3 caracteres y no tenga digitos
    public static boolean lugarSede(String lugarSede) {

        if (lugarSede.isEmpty()) {
            System.err.println("El nombre esta vacio");
            return false;
        }
        if (lugarSede.length() < 3) {
            return false;
        }

        for (int i = 0; i < lugarSede.length(); i++) {
            if (Character.isDigit(lugarSede.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Mismo criterio que lugarSede aplicado al nombre del estadio
    public static boolean estadio(String estadio) {

        if (estadio.isEmpty()) {
            System.err.println("El nombre esta vacio");
            return false;
        }
        if (estadio.length() < 3) {
            return false;
        }

        for (int i = 0; i < estadio.length(); i++) {
            if (Character.isDigit(estadio.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Comprueba que el numero de socios sea un valor no negativo
    public static boolean numeroSocios(int sociosAficionados) {

        if (sociosAficionados < 0) {
            System.err.println("No puede ser un numero negativo");
            return false;
        }
        return true;
    }

}
