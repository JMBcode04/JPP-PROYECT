/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR JORGE
 * @author jorge
 */

// Excepcion personalizada para datos invalidos introducidos por el usuario
public class ElDatoIntroducidoEsIncorrecto extends Exception {

    // Constructor con mensaje personalizado
    public ElDatoIntroducidoEsIncorrecto(String mensaje) {
        super(mensaje);
    }

    // Sirve para que lance el mensaje cuando salte el error
    public ElDatoIntroducidoEsIncorrecto() {
        super("El dato introducido es incorrecto. Por favor vuelva a intentarlo.");
    }
}
