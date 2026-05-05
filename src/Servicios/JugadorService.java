/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Interfaces.MetodosComunes;
import Modelos.Jugador;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class JugadorService implements MetodosComunes {

    // Implementar el crud de Jugador
    public static void InsertarJugador() {

    }

    @Override
    public void insertar(Object entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        Jugador j = (Jugador) entidad;
        validarJugador(j);
        System.out.println("Jugador insertado correctamente: " + j.toString());
        //aqui insercion en BD (mas adelante)   
    }

    @Override
    public void actualizar(Object entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object consultar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List consultarTodos() throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //Excepcion valores vacios
    public void validarNoVacio(String valor) throws ElDatoIntroducidoEsIncorrecto {
        if (valor == null || valor.isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("No puede estar vacio");
        }
    }

    private void validarJugador(Jugador j) throws ElDatoIntroducidoEsIncorrecto {
        validarNoVacio(j.getNombre());
        validarNoVacio(j.getFechaNacimiento());
        validarNoVacio(j.getNacionalidad());
        validarNoVacio(j.getPosicion());
    }

    //*******corregir
    /*//Ajustar Codigo Contador desde el ultimo en la BD
    public static int obtenerUltimoCodigo() {
        int ultimoCodigo = 0;
        String sql = "Select max(codigo) from jugador";
        try ( Connection con = DriverManager.getConnection("jdbc:tu_bd");//******  
                Statement st = con.createStatement();  
                ResultSet rs = st.executeQuery(sql)){

            if (rs.next()) {
                ultimoCodigo = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoCodigo;
    }*/

    

}
