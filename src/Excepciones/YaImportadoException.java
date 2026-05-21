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
// Excepcion para evitar importar los mismos datos dos veces en la misma sesion
public class YaImportadoException extends Exception {

    // Constructor con mensaje personalizado
    public YaImportadoException(String mensaje) {
        super(mensaje);
    }

    // Sirve para que lance el mensaje cuando salte el error
    public YaImportadoException() {
        super("YaImportadoException: Los datos ya han sido importados previamente durante esta sesión.");
    }
}
