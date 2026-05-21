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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR PAULA
 * @author jorge
 */
public class JugadorEquipoService implements MetodosComunes<Jugador_equipo> {

    // Implementar el crud de JugadorEquipo
    //Atributos
    //Hace de memoria para poder almacenar los equipos introducidos durante la sesión.
    private List<Jugador_equipo> listaSesion;

    //Constructor
    public JugadorEquipoService() {
        this.listaSesion = new ArrayList<>();
    }

    @Override
    public void insertar(Jugador_equipo je) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validar(je);
        String sql = "INSERT INTO jugador_equipo(codigo_equipo,codigo_jugador,año_entrada,año_salida,partidos_titular)" + "VALUES (?,?,?,?,?)";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, je.getCodigoEquipo());
            ps.setInt(2, je.getCodigoJugador());
            ps.setInt(3, je.getAñoEntrada());

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
        String sql = "UPDATE jugador_equipo SET año_salida=?, partidos_titular=?" + "WHERE codigo_equipo=? AND codigo_jugador=? AND año_entrada=?";

        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {
            if (je.getAñoSalida() != null) {
                ps.setInt(1, je.getAñoSalida());
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            if (je.getPartidosTitular() != null) {
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
    public void eliminar(int codigoJugador) throws SeHaProducidoUnError {
        String sql = "DELETE FROM jugador_equipo WHERE codigo_jugador=? ";

        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoJugador);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }

        } catch (Exception e) {
            throw new SeHaProducidoUnError("Error al eliminar jugador_equipo: " + e.getMessage());
        }
    }

    @Override
    public Jugador_equipo consultar(int codigoJugador) throws SeHaProducidoUnError {
        String sql = "SELECT * FROM jugador_equipo WHERE codigo_jugador=? ";

        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoJugador);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
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

        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (Exception e) {
            throw new SeHaProducidoUnError("Error al consultar jugador_equipo: " + e.getMessage());
        }
        return lista;
    }

    //EXPORTACIONES
    /**
     * Metodos de exportaciona TXT de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void exportarTXT() throws SeHaProducidoUnError {
        List<Jugador_equipo> lista = consultarTodos();
        List<String> lineas = new ArrayList<>();

        for (Jugador_equipo je : lista) {
            lineas.add(je.getCodigoEquipo() + Constantes.SEPARADOR_TXT
                    + je.getCodigoJugador() + Constantes.SEPARADOR_TXT
                    + je.getAñoEntrada() + Constantes.SEPARADOR_TXT
                    + (je.getAñoSalida() != null ? je.getAñoSalida() : "null") + Constantes.SEPARADOR_TXT + (je.getPartidosTitular() != null ? je.getPartidosTitular() : "null"));
        }

        MetodosFicheros.exportarTxt(Constantes.FICHERO_JUGADOR_EQUIPO, lineas);
    }

    /**
     * Metodos de exportacion a CSv de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void exportarCSv() throws SeHaProducidoUnError {
        List<Jugador_equipo> lista = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Jugador_equipo je : lista) {
            lineas.add(je.getCodigoEquipo() + Constantes.SEPARADOR_CSV
                    + je.getCodigoJugador() + Constantes.SEPARADOR_CSV
                    + je.getAñoEntrada() + Constantes.SEPARADOR_CSV
                    + (je.getAñoSalida() != null ? je.getAñoSalida() : "null")
                    + Constantes.SEPARADOR_CSV + (je.getPartidosTitular() != null ? je.getPartidosTitular() : "null"));
        }
        MetodosFicheros.exportarCsv(Constantes.FICHERO_JUGADOR_EQUIPO, lineas);
    }

    /**
     * Metodos de exportaciona Binario de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void exportarBinario() throws SeHaProducidoUnError {
        MetodosFicheros.exportarBinario(Constantes.FICHERO_JUGADOR_EQUIPO, consultarTodos());
    }

    /**
     * Metodos de exportaciona Json de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void exportarJson() throws SeHaProducidoUnError {
        MetodosFicheros.exportarJson(Constantes.FICHERO_JUGADOR_EQUIPO, consultarTodos());
    }

    //IMPORTACION
    /**
     * Metodos de importacion Txt de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void importarTxt() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarTxt(Constantes.FICHERO_JUGADOR_EQUIPO);
        for (String linea : lineas) {
            try {
                Jugador_equipo je = parsear(linea.split(Constantes.SEPARADOR_TXT));
                if (!existeEnBD(je.getCodigoEquipo(), je.getCodigoJugador(), je.getAñoEntrada())) {
                    insertar(je);
                } else {
                    System.out.println("JugadorEquipo duplicado ignorado: eq=" + je.getCodigoEquipo() + " jug=" + je.getCodigoJugador());
                }
            } catch (ElDatoIntroducidoEsIncorrecto e) {
                System.out.println("Linea con datos incorrectos ignorada: " + linea);
            }
        }
    }

    /**
     * Metodos de importacion Csv de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void importarCsv() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarCsv(Constantes.FICHERO_JUGADOR_EQUIPO);

        for (String linea : lineas) {
            try {
                Jugador_equipo je = parsear(linea.split(Constantes.SEPARADOR_CSV));
                if (!existeEnBD(je.getCodigoEquipo(), je.getCodigoJugador(), je.getAñoEntrada())) {
                    insertar(je);
                } else {
                    System.out.println("JugadorEquipo duplicado ignorado: eq=" + je.getCodigoEquipo() + " jug=" + je.getCodigoJugador());
                }
            } catch (ElDatoIntroducidoEsIncorrecto e) {
                System.out.println("Linea con datos incorrectos ignorada: " + linea);
            }
        }
    }

    /**
     * Metodos de importacion Binario de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void importarBinario() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<Jugador_equipo> jugador_equipos;
        try {
            jugador_equipos = MetodosFicheros.importarBinario(Constantes.FICHERO_JUGADOR_EQUIPO);
            for (Jugador_equipo je : jugador_equipos) {
                try {
                    if (!existeEnBD(je.getCodigoEquipo(), je.getCodigoJugador(), je.getAñoEntrada())) {
                        insertar(je);
                    } else {
                        System.out.println("JugadorEquipo duplicado ignorado: eq=" + je.getCodigoEquipo() + " jug=" + je.getCodigoJugador());
                    }
                } catch (ElDatoIntroducidoEsIncorrecto e) {
                    System.out.println("JugadorEquipo con datos incorrectos ignorado.");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JugadorEquipoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodos de importacion Json de JugadorEquipo
     *
     * @throws SeHaProducidoUnError
     */
    public void importarJson() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<Jugador_equipo> lista = MetodosFicheros.importarJson(Constantes.FICHERO_JUGADOR_EQUIPO,
                new TypeToken<List<Jugador_equipo>>() {
                }.getType());

        for (Jugador_equipo je : lista) {
            try {
                if (!existeEnBD(je.getCodigoEquipo(), je.getCodigoJugador(), je.getAñoEntrada())) {
                    insertar(je);
                } else {
                    System.out.println("JugadorEquipo duplicado ignorado: eq=" + je.getCodigoEquipo() + " jug=" + je.getCodigoJugador());
                }
            } catch (ElDatoIntroducidoEsIncorrecto e) {
                System.out.println("JugadorEquipo con datos incorrectos ignorado.");
            }
        }
    }

    //METODOS AUXILIARES
    /**
     * Mapea una fila mediante el REesultSet a un objeto en este caso de
     * JugadorEquipo
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Jugador_equipo mapear(ResultSet rs) throws SQLException {
        Integer añoSalida = rs.getObject("año_salida") != null ? rs.getInt("año_salida") : null;
        Integer partidosTitular = rs.getObject("partidos_titular") != null ? rs.getInt("partidos_titular") : null;

        return new Jugador_equipo(rs.getInt("codigo_equipo"), rs.getInt("codigo_jugador"), rs.getInt("año_entrada"), añoSalida, partidosTitular);
    }

    /**
     *
     * @param campos
     * @return
     * @throws ElDatoIntroducidoEsIncorrecto
     */
    private Jugador_equipo parsear(String[] campos) throws ElDatoIntroducidoEsIncorrecto {
        if (campos.length < 5) {
            throw new ElDatoIntroducidoEsIncorrecto("Formato de linea incorrecto");
        }

        Integer añoSalida = campos[3].trim().equals("null") ? null : Integer.parseInt(campos[3].trim());
        Integer partidosTitular = campos[4].trim().equals("null") ? null : Integer.parseInt(campos[4].trim());

        return new Jugador_equipo(Integer.parseInt(campos[0].trim()), Integer.parseInt(campos[1].trim()), Integer.parseInt(campos[2].trim()), añoSalida, partidosTitular);
    }

    /**
     *
     * @param je
     * @throws ElDatoIntroducidoEsIncorrecto
     */
    private void validar(Jugador_equipo je) throws ElDatoIntroducidoEsIncorrecto {
        if (je.getCodigoEquipo() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El codigo de equipo debe ser un numero positivo");
        }
        if (je.getCodigoJugador() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El codigo de jugador debe ser un numero positivo");
        }
        if (je.getAñoEntrada() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El año de entrada debe ser un numero positivo");
        }
    }

    /**
     *
     */
    public void verDatosInsertadosSesion() {
        if (listaSesion.isEmpty()) {
            System.out.println("No se han insertado relaciones jugador_equipo durante esta sesion");
        } else {
            System.out.println("Relaciones jugador_equipo insertadas en sesion (" + listaSesion.size() + "):");

            for (Jugador_equipo je : listaSesion) {
                System.out.println(" " + je.toString());
            }
        }
    }

    private boolean existeEnBD(int codigoEquipo, int codigoJugador, int añoEntrada) throws SeHaProducidoUnError {
        String sql = "SELECT codigo_equipo FROM jugador_equipo WHERE codigo_equipo=? AND codigo_jugador=? AND año_entrada=?";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoEquipo);
            ps.setInt(2, codigoJugador);
            ps.setInt(3, añoEntrada);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al comprobar jugador_equipo en BD: " + e.getMessage());
        }
    }

}
