/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Interfaces.MetodosComunes;
import Modelos.Jugador_equipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jorge
 */
public class JugadorEquipoService implements MetodosComunes<Jugador_equipo>{
    
   // Implementar el crud de Jugadorequipo
    
    //Atributos
    //Hace de memoria para poder almacenar los equipos introducidos durante la sesión.
    private List<Jugador_equipo> listaSesion;

    //Controladores para la importación de cada tipo de fichero.
    private boolean txtImportado  = false;
    private boolean csvImportado = false;
    private boolean binImportado = false;
    private boolean jsonImportado = false;

    //Constructor
    public JugadorEquipoService() {
        this.listaSesion = new ArrayList<>();
    }
    

    @Override
    public void insertar(Jugador_equipo je) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validar(je);
        String sql = "INSET INTO jugador_equipo(codigo_equipo,codigo_jugador,año_entrada,año_salida,partidos_titular)" + "VALUES (?,?,?,?,?)";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();PreparedStatement ps= con.prepareStatement(sql)) {
                        
            ps.setInt(1, je.getCodigoEquipo());
            ps.setInt(2, je.getCodigoJugador());
            ps.setInt(2, je.getAñoEntrada());
            
            if (je.getAñoSalida() != null) {
                ps.setInt(4, je.getAñoSalida());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            
            if (je.getPartidosTitular() != null) {
                ps.setInt(5, je.getPartidosTitular());
            } else {
               ps.setNull(5, Types.INTEGER); 
            }
            ps.executeUpdate();
            listaSesion.add(je);
            
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al insertar jugador_equipo: " + e.getMessage());
        }   
    }

    @Override
    public void actualizar(Jugador_equipo je) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validar(je);
        String sql= "UPDATE jugador_equipo SET año_salida=?, partidos_titular=?" + "WHERE codigo_equipo=? AND codigo_jugador=? AND año_entrada=?";
        
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)){
            if (je.getAñoSalida() != null) {
                ps.setInt(1, je.getAñoSalida());
            } else {
                ps.setNull(1, Types.INTEGER);
            }
            
            if (je.getPartidosTitular() !=null) {
                ps.setInt(2, je.getPartidosTitular());
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            
            ps.setInt(3, je.getCodigoEquipo());
            ps.setInt(4, je.getCodigoJugador());
            ps.setInt(5, je.getAñoEntrada());
            
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (Exception e) {
            throw new SeHaProducidoUnError("Error al actualizar jugador_equipo: " + e.getMessage());
        }
    }
    
    @Override
    public void eliminar(int codigoEquipo, int codigoJugador, int añoEntrada) throws SeHaProducidoUnError {
        String sql = "DELETE FROM jugador_equipo WHERE codigo_equipo=? AND codigo_jugador=? AND año_entrada=?";
        
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, codigoEquipo);
            ps.setInt(2, codigoJugador);
            ps.setInt(3, añoEntrada);
            
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
            
        } catch (Exception e) {
            throw new SeHaProducidoUnError("Error al eliminar jugador_equipo: " + e.getMessage());
        }
    }
    
    @Override
    public Jugador_equipo consultar(int codigoEquipo, int codigoJugador, int añoEntrada) throws SeHaProducidoUnError {
        String sql = "SELECT * FROM jugador_equipo WHERE codigo_equipo=? AND codigo_jugador=? AND añod_entrada=?";
        
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, codigoEquipo);
            ps.setInt(2, codigoJugador);
            ps.setInt(3, añoEntrada);
            
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return mapearJugador_equipo(rs);
                } else {
                    throw new SeHaProducidoUnError();
                }
            }
        } catch (Exception e) {
            throw new SeHaProducidoUnError("Error al consultar jugador_equipo: " + e.getMessage());
        }
    }

    @Override
    public List<Jugador_equipo> consultarTodos() throws SeHaProducidoUnError {
        String sql = "SELECT * FROM jugador_equipo ORDER BY año_entrada ASC";
        List<Jugador_equipo> lista = new ArrayList<>();
        
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()){
            while (rs.next()) {                
                lista.add(mapearJugador_equipo(rs));
            }
            
        } catch (Exception e) {
            throw new SeHaProducidoUnError("Error al consultar jugador_equipo: " + e.getMessage());
        }
        return lista;
    }

    
    
}