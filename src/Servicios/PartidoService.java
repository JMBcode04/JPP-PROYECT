/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Modelos.ContenedorPartidos;
import Modelos.Partido;
import Utils.Constantes;
import com.google.gson.reflect.TypeToken;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author jorge
 */
public class PartidoService {

    // Atributos
    // Sirve como memoria para almacenar los equipos introducidos durante la sesion
    private ContenedorPartidos contenedor;

    // Son controladores para la importacion de cada tipo de fichero
    private boolean txtImportado = false;
    private boolean csvImportado = false;
    private boolean binImportado = false;
    private boolean jsonImportado = false;

    //Constructor
    public PartidoService(ContenedorPartidos contenedor) {
        this.contenedor = contenedor;
    }

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
            if (entidad.getPuntuacionLocal() != null) {
                ps.setInt(5, entidad.getPuntuacionLocal());
            } else {
                ps.setNull(5, Types.INTEGER);
            }
            if (entidad.getPuntuacionVisitante() != null) {
                ps.setInt(6, entidad.getPuntuacionVisitante());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            if (entidad.getPuntuacionVisitante() != null) {
                ps.setInt(6, entidad.getPuntuacionVisitante());
            } else {
                ps.setNull(6, Types.INTEGER);
            }
            ps.executeUpdate();
            contenedor.añadirPartido(entidad);
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al insertar partido: " + e.getMessage());
        }
    }

    public void actualizar(Partido entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarPartido(entidad);
        String sql = "UPDATE partido SET añoTemporada=?, fecha=?, puntuacionLocal=?, puntuacionVisitante=? "
                + "WHERE codigoEquipoLocal=? and codigoEquipoVisitante=?";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entidad.getAñoTemporada());
            ps.setString(2, entidad.getFecha());
            if (entidad.getPuntuacionLocal() != null) {
                ps.setInt(3, entidad.getPuntuacionLocal());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            if (entidad.getPuntuacionVisitante() != null) {
                ps.setInt(4, entidad.getPuntuacionVisitante());
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setInt(5, entidad.getCodigoEquipoLocal());
            ps.setInt(6, entidad.getCodigoEquipoVisitante());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al actualizar partido: " + e.getMessage());
        }

    }

    public void eliminar(int codigoLocal, int codigoVisitante, int añoTemporada) throws SeHaProducidoUnError {
        String sql = "DELETE FROM partido WHERE codigoEquipoLocal=? AND codigoEquipoVisitante=? AND año_temporada =?";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoLocal);
            ps.setInt(2, codigoVisitante);
            ps.setInt(3, añoTemporada);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al eliminar partido: " + e.getMessage());
        }
    }

    public Partido consultar(int codigoLocal, int codigoVisitante, int añoTemporada) throws SeHaProducidoUnError {
        String sql = "SELECT * FROM partido WHERE codigo_equipo_local=? AND codigo_equipo_visitante=? AND año_temporada=?";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoLocal);
            ps.setInt(2, codigoVisitante);
            ps.setInt(3, añoTemporada);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                } else {
                    throw new SeHaProducidoUnError("Se ha producido un error");
                }
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al consultar partido: " + e.getMessage());
        }
    }

    public List<Partido> consultarTodos() throws SeHaProducidoUnError {
        String sql = "SELECT * FROM partido PRDER BY fecha ASC";
        List<Partido> lista = new ArrayList<>();
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al consultar todos los partidos: " + e.getMessage());
        }
        return lista;
    }

    //EXPORTAR
    public void exportarTxt() throws SeHaProducidoUnError {
        List<Partido> lista = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Partido p : lista) {
            lineas.add(p.getCodigoEquipoLocal() + Constantes.SEPARADOR_TXT +
                       p.getCodigoEquipoVisitante() + Constantes.SEPARADOR_TXT +
                       p.getAñoTemporada()+ Constantes.SEPARADOR_TXT +
                       p.getFecha() + Constantes.SEPARADOR_TXT +
                       (p.getPuntuacionLocal() != null ? p.getPuntuacionLocal() : "null") + Constantes.SEPARADOR_TXT +
                       (p.getPuntuacionVisitante() != null ? p.getPuntuacionVisitante() : "null"));
        }
        MetodosFicheros.exportarTxt(Constantes.FICHERO_PARTIDO, lineas);
    }
    
    public void exportarCsv() throws SeHaProducidoUnError {
        List<Partido> lista = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Partido p : lista) {
            lineas.add(p.getCodigoEquipoLocal() + Constantes.SEPARADOR_CSV
                    + p.getCodigoEquipoVisitante() + Constantes.SEPARADOR_CSV
                    + p.getAñoTemporada() + Constantes.SEPARADOR_CSV
                    + p.getFecha() + Constantes.SEPARADOR_CSV
                    + (p.getPuntuacionLocal() != null ? p.getPuntuacionLocal() : "null") + Constantes.SEPARADOR_CSV
                    + (p.getPuntuacionVisitante() != null ? p.getPuntuacionVisitante() : "null"));
        }
        MetodosFicheros.exportarCsv(Constantes.FICHERO_PARTIDO, lineas);
    }

    public void exportarBinario() throws SeHaProducidoUnError {
        MetodosFicheros.exportarBinario(Constantes.FICHERO_PARTIDO, consultarTodos());
    }
    
    public void exportarJson() throws SeHaProducidoUnError {
        MetodosFicheros.exportarJson(Constantes.FICHERO_PARTIDO, consultarTodos());
    }

    //IMPORTAR
    public void importarTxt() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarTxt(Constantes.FICHERO_PARTIDO, txtImportado);
        for (String linea : lineas) {
            insertar(parsear(linea.split(Constantes.SEPARADOR_TXT)));
        }
        txtImportado = true;
    }
    
    public void importarCsv() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarCsv(Constantes.FICHERO_PARTIDO, csvImportado);
        for (String linea : lineas) {
            insertar(parsear(linea.split(Constantes.SEPARADOR_CSV)));
        }
        csvImportado = true;
    }
    
    public void importarBinario() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto, ClassNotFoundException {
        List<Partido> lista = MetodosFicheros.importarBinario(Constantes.FICHERO_PARTIDO, binImportado);
        for (Partido p : lista) {
            insertar(p);
        }
        binImportado = true;
    }
    
    public void importarJson() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<Partido> lista = MetodosFicheros.importarJson(
                Constantes.FICHERO_PARTIDO, jsonImportado,
                new TypeToken<List<Partido>>(){}.getType());
        for (Partido p : lista) {
            insertar(p);
        }
        jsonImportado = true;
    }

    //METODOS
    
    private void validarPartido(Partido partido) throws ElDatoIntroducidoEsIncorrecto {
        if (partido.getCodigoEquipoLocal() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El codigo tiene que ser mayor a 0");
        }
        if (partido.getCodigoEquipoVisitante() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El codigo tiene que ser mayor a 0");
        }
        if (partido.getAñoTemporada() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El año tiene que ser un numero positivio");
        }
        if (partido.getFecha() == null || partido.getFecha().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("La fecha no puede estar vacia");
        }
        if (partido.getPuntuacionLocal() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("La puntuiacion tiene que ser un numero positivio");
        }
        if (partido.getPuntuacionVisitante() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("La puntuiacion tiene que ser un numero positivio");
        }
    }

    //transforma una fila de MYSQL a un objeto de Partido 
    //Mapea una fila del ResultSet a un objeto Partido.
    private Partido mapear(ResultSet rs) throws SQLException {
        Integer puntuacionLocal = rs.getObject("puntuacion_local") != null ? rs.getInt("puntuacion_local") : null;
        Integer puntuacionVisitante = rs.getObject("puntuacion_visitante") != null ? rs.getInt("puntuacion_visitante") : null;
        return new Partido(
                rs.getInt("codigo_equipo_local"),
                rs.getInt("codigo_equipo_visitante"),
                rs.getInt("año_temporada"),
                rs.getString("fecha"),
                puntuacionLocal,
                puntuacionVisitante
        );
    }

    //Parsea un array de campos de texto a un objeto Partido.
    private Partido parsear(String[] campos) throws ElDatoIntroducidoEsIncorrecto {
        if (campos.length < 6) {
            throw new ElDatoIntroducidoEsIncorrecto("Formato de línea incorrecto para partido.");
        }
        Integer puntuacionLocal = campos[4].trim().equals("null") ? null : Integer.parseInt(campos[4].trim());
        Integer puntuacionVisitante = campos[5].trim().equals("null") ? null : Integer.parseInt(campos[5].trim());
        return new Partido(
                Integer.parseInt(campos[0].trim()),
                Integer.parseInt(campos[1].trim()),
                Integer.parseInt(campos[2].trim()),
                campos[3].trim(),
                puntuacionLocal,
                puntuacionVisitante
        );
    }
    
    //Muestra los partidos insertados durante la sesión actual.
    public void verDatosInsertadosSesion() {
        contenedor.mostrarPartidosSesion();
    }
}
