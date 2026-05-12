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
public class JugadorEquipoService implements MetodosComunes<JugadorEquipo>{
    
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
    public void insertar(JugadorEquipo je) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validar(je);
        String sql = "INSET INTO jugador_equipo(codigo_equipo,codigo_jugador,año_entrada,año_salida,partidos_titular)" + "VALUES (?,?,?,?,?)";
        try {
            Connection con = MetodosBaseDeDatos.getConexion);
            PreparedStatement ps= con.prepareStatement(sql);
            
            ps.setInt(1, je.getCodigoEquipo());
            ps.setInt(2, je.getCodigoJugador());
            ps.setInt(2, je.getAñontrada());
            
            if (je.getAñoSalida() != null) {
                ps.setInt(4, je.getAñoSalida());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            
            if (je.getPartidosTitular() != null) {
                ps.setInt(5, je.getParrtidosTitular());
            } else {
               ps.setNull(5, Types.INTEGER); 
            }
            ps.executeUpdate();
            listaSesion.add(je);
            
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al insertar jugador_equipo: " + e.getMessage());
        }   
        }
    }

    @Override
    public void actualizar(JugadorEquipo je) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarJugadorEquipo(je);
        String sql = "UPDATE jugador_equipo SET codigo_equipo=?, codigo_jugador=?, año_entrada=?, año_salida=?, partidos_titular=? " + "WHERE codigo_equipo=?, codigo_jugador=?";
        
        tr
    }

    @Override
    public void eliminar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JugadorEquipo consultar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<JugadorEquipo> consultarTodos() throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
    
   
    
    
   
   
   
   
   
    
  
    
}
