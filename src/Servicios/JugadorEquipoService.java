/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Interfaces.MetodosComunes;
import Modelos.Jugador_equipo;
import Utils.Constantes;
import com.google.gson.reflect.TypeToken;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jorge
 */
public class JugadorEquipoService implements MetodosComunes<Jugador_equipo>{
    
   // Implementar el crud de JugadorEquipo
    
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
    
    //EXPORTACIONES

    /**
     * Metodos de exportaciona TXT de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void exportarTXT() throws SeHaProducidoUnError {
        List<Jugador_equipo>lista= consultarTodos();
        List<String>lineas=new ArrayList<>();
        
        for (Jugador_equipo je : lista) {
            lineas.add(je.getCodigoEquipo() + Constantes.SEPARADOR_TXT 
                    + je.getCodigoJugador() + Constantes.SEPARADOR_TXT 
                    + je.getAñoEntrada() + Constantes.SEPARADOR_TXT 
                    + (je.getAñoSalida() != null ? je.getAñoSalida(): "null") + Constantes.SEPARADOR_TXT + (je.getPartidosTitular()!=null ? je.getPartidosTitular():"null"));
        }
        
        MetodosFicheros.exportarTxt(Constantes.FICHERO_JUGADOR_EQUIPO, lineas);
    }
    
    /**
     * Metodos de exportaciona CSv de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void exportarCSv() throws SeHaProducidoUnError {
        List<Jugador_equipo>lista = consultarTodos();
        List<String>lineas= new ArrayList<>();
        for (Jugador_equipo je : lista) {
            lineas.add(je.getCodigoEquipo() + Constantes.SEPARADOR_CSV 
                    + je.getCodigoJugador() + Constantes.SEPARADOR_CSV 
                    + je.getAñoEntrada() + Constantes.SEPARADOR_CSV 
                    + (je.getAñoSalida() != null ? je.getAñoSalida():"null") 
                    + Constantes.SEPARADOR_CSV + (je.getPartidosTitular()!=null ? je.getPartidosTitular():"null"));
        }
        MetodosFicheros.exportarCsv(Constantes.FICHERO_JUGADOR_EQUIPO, lineas);
    }
    
    /**
     * Metodos de exportaciona Binario de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void exportarBinario() throws SeHaProducidoUnError {
        MetodosFicheros.exportarBinario(Constantes.FICHERO_JUGADOR_EQUIPO, consultarTodos());
    }
    
    
    /**
     * Metodos de exportaciona Json de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void exportarJson() throws SeHaProducidoUnError {
        MetodosFicheros.exportarJson(Constantes.FICHERO_JUGADOR_EQUIPO, consultarTodos());
    }
    
    
    //IMPORTACION
    
    /**
     * Metodos de importacion Txt de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void importarTxt() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<String>lineas=MetodosFicheros.importarTxt(Constantes.FICHERO_JUGADOR_EQUIPO, txtImportado);
        for (String linea : lineas) {
            insertar(parsear(linea.split(Constantes.SEPARADOR_TXT)));
        }
        txtImportado=true;
    }
    
    /**
     * Metodos de importacion Csv de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void importarCsv() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<String>lineas=MetodosFicheros.importarCsv(Constantes.FICHERO_JUGADOR_EQUIPO, csvImportado);
        
        for (String linea : lineas) {
            insertar(parsear(linea.split(Constantes.SEPARADOR_CSV)));
        }
        csvImportado=true;
    }
    
    /**
     * Metodos de importacion Binario de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void importarBinario() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<Jugador_equipo>lista=MetodosFicheros.exportarBinario(Constantes.FICHERO_JUGADOR_EQUIPO, binImportado);
        
        for (Jugador_equipo je : lista) {
            insertar(je);
        }
        binImportado=true;
    }
    
    /**
     * Metodos de importacion Json de JugadorEquipo
     * @throws SeHaProducidoUnError 
     */
    public void importarJson() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<Jugador_equipo>lista=MetodosFicheros.importarJson(Constantes.FICHERO_JUGADOR_EQUIPO, jsonImportado, 
                new TypeToken<List<Jugador_equipo>>(){}.getType());
        
        for (Jugador_equipo je : lista) {
            insertar(je);
        }
        jsonImportado=true;
    }
    
    
    //METODOS AUXILIARES
    
    /**
     * Mapea una fila mediante el REesultSet a un objeto en este caso de JugadorEquipo
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Jugador_equipo mapear(ResultSet rs) throws SQLException {
        Integer añoSalida = rs.getObject("año_salida") !=null ? rs.getInt("año_salida"):null;
        Integer partidosTitular = rs.getObject("partidos_titular") !=null ? rs.getInt("partidos_titular"):null;
        
        return new Jugador_equipo(rs.getInt("codigo_equipo"),rs.getInt("codigo_jugador"),rs.getInt("año_entrada"),añoSalida,partidosTitular);
    }
    
    
    private Jugador_equipo parsear(String[]campos) throws ElDatoIntroducidoEsIncorrecto{
        if (campos.length < 5) {
            throw new ElDatoIntroducidoEsIncorrecto("Formato de linea incorrecto");
        }
        
        Integer añoSalida=campos
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}