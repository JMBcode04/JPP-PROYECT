/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Utils.Constantes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class MetodosBaseDeDatos {
    
    private static Connection con;
    
    public static Connection AccederBaseDeDatos() {
        
        try {
            String url = Constantes.URL;
            String user = Constantes.USER;
            String password = Constantes.PASSWORD[0];
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion Establecida");
            return con;

            //si no se mete con la primera contraseña, prueba con la siguiente
        } catch (SQLException ex) {
            //System.err.println("No se ha establecido la conexion con la base de datos");
            //System.err.println(ex.toString());
            //ex.printStackTrace();
            System.out.println("Error de conexión. Reintentando....");
            String url = Constantes.URL;
            String user = Constantes.USER;
            String password = Constantes.PASSWORD[1];
            try {
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion Establecida");
            } catch (SQLException ex1) {
                System.err.println("No se ha establecido la conexion con la base de datos");
            }
            return con;
                   
        }
        //return null;
    }

    public static void CerrarBaseDeDatos() {
        try {
            con.close();
            System.out.println("La base de datos se ha cerrado correctamente");
        } catch (SQLException ex1) {
            System.err.println("No se ha cerrado la base de datos");
            Logger.getLogger(MetodosBaseDeDatos.class.getName()).log(Level.SEVERE, null, ex1);
        }

    }

}
