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
// Excepcion general para errores inesperados en la aplicacion (ej: codigo no encontrado en BD)
public class SeHaProducidoUnError extends Exception {

    // Constructor con mensaje personalizado
    public SeHaProducidoUnError(String mensaje) {
        super(mensaje);
    }

    // Sirve para que lance el mensaje cuando salte el error
    public SeHaProducidoUnError() {
        super("Se ha producido un error inesperado en la aplicación. No existe el código introducido.");
    }
}
