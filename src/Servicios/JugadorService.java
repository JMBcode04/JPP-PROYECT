/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import static Servicios.MetodosBaseDeDatos.con;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class JugadorService {

    // Implementar el crud de Jugador
    public static void InsertarJugador() {

        MetodosBaseDeDatos.AccederBaseDeDatos();
        
        
        
        
        MetodosBaseDeDatos.CerrarBaseDeDatos();

    }
}
