/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Interfaces.MetodosComunes;
import Modelos.ContenedorEquipos;
import Modelos.Equipo;
import Modelos.Partido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PartidoService implements MetodosComunes<Partido>{
    
     // Sirve como memoria para almacenar los equipos introducidos durante la sesion
    private ContenedorEquipos contenedor;

    // Son controladores para la importacion de cada tipo de fichero
    private boolean txtImportado = false;
    private boolean csvImportado = false;
    private boolean binImportado = false;
    private boolean jsonImportado = false;
    
    //Constructor

    public PartidoService(ContenedorEquipos contenedor) {
        this.contenedor = contenedor;
    }

    @Override
    public void insertar(Partido entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarPartido(entidad);
        String sql = "INSERT INTO partido (codigoEquipoLocal, codigoEquipoVisitante, añoTemporada, fecha, puntuacionLocal, puntuacionVisitante) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, entidad.getCodigoEquipoLocal());
            ps.setInt(2, entidad.getCodigoEquipoVisitante());
            ps.setInt(3, entidad.getAñoTemporada());
            ps.setString(4, entidad.getFecha());
            ps.setInt(5, entidad.getPuntuacionLocal());
            ps.setInt(6, entidad.getPuntuacionVisitante());
            ps.executeUpdate();
            contenedor.añadirPartido(entidad);
            
        } catch (SQLException e) {
            throw  new SeHaProducidoUnError("Error al insertar partido: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Partido entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarPartido(entidad);
        String sql = "UPDATE partido SET añoTemporada=?, fecha=?, puntuacionLocal=?, puntuacionVisitante=? "
                + "WHERE codigoEquipoLocal=? and codigoEquipoVisitante=?";

    }

    @Override
    public void eliminar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Partido consultar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Partido> consultarTodos() throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void validarPartido(Partido partido)throws ElDatoIntroducidoEsIncorrecto{
        if(partido.getCodigoEquipoLocal()<=0){
            throw  new ElDatoIntroducidoEsIncorrecto("El codigo tiene que ser mayor a 0");
        }
        if(partido.getCodigoEquipoVisitante()<=0){
            throw  new ElDatoIntroducidoEsIncorrecto("El codigo tiene que ser mayor a 0");
        }
        if(partido.getAñoTemporada()<=0){
            throw  new ElDatoIntroducidoEsIncorrecto("El año tiene que ser un numero positivio");
        }
        if(partido.getFecha()==null||partido.getFecha().isBlank()){
            throw  new ElDatoIntroducidoEsIncorrecto("La fecha no puede estar vacia");
        }
        if(partido.getPuntuacionLocal()<=0){
            throw  new ElDatoIntroducidoEsIncorrecto("La puntuiacion tiene que ser un numero positivio");
        }
        if(partido.getPuntuacionVisitante()<=0){
            throw  new ElDatoIntroducidoEsIncorrecto("La puntuiacion tiene que ser un numero positivio");
        }
    }

    
}
