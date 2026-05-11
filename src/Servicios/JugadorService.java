/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Interfaces.MetodosComunes;
import Modelos.ContenedorJugador;
import Modelos.Jugador;
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
public class JugadorService implements MetodosComunes<Jugador> {

    private ContenedorJugador contenedor;

    private boolean txtImportado = false;
    private boolean csvImportado = false;
    private boolean binImportado = false;
    private boolean jsonImportado = false;

    public JugadorService(ContenedorJugador contenedor) {
        this.contenedor = contenedor;
    }

    // Implementar el crud de Jugador
    @Override
    public void insertar(Jugador entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarJugador(entidad);
        System.out.println("Jugador insertado correctamente: " + entidad.toString());
        //aqui insercion en BD (mas adelante)   
        String sql = "INSERT INTO jugador (codigo, nombre, fecha_nacimiento, nacionalidad, posicion)"
                + "values(?,?,?,?,?)";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entidad.getCodigo());
            ps.setString(2, entidad.getNombre());
            ps.setString(3, entidad.getFechaNacimiento());
            ps.setString(4, entidad.getNacionalidad());
            ps.setString(5, entidad.getPosicion());

            ps.executeUpdate();

            System.out.println("Jugador insertado correctamente: " + entidad);

        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al insertar jugador: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Jugador entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarJugador(entidad);
        String sql = "UPDATE jugador SET nombre=?, fecha_nacimiento=?, nacionalidad=?, posicion=? WHERE codigo=?";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getFechaNacimiento());
            ps.setString(3, entidad.getNacionalidad());
            ps.setString(4, entidad.getPosicion());
            ps.setInt(5, entidad.getCodigo());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al actualizar jugador: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int codigo) throws SeHaProducidoUnError {
        String sql = "DELETE FROM jugador WHERE codigo=?";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al eliminar jugador: " + e.getMessage());
        }
    }

    @Override
    public Jugador consultar(int codigo) throws SeHaProducidoUnError {
        String sql = "SELECT * FROM jugador WHERE codigo=?";
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearJugador(rs);
                } else {
                    throw new SeHaProducidoUnError();
                }
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al consultar jugador: " + e.getMessage());
        }
    }

    @Override
    public List<Jugador> consultarTodos() throws SeHaProducidoUnError {
        String sql = "SELECT * FROM jugador ORDER BY nombre ASC";
        List<Jugador> lista = new ArrayList<>();
        try ( Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearJugador(rs));
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al consultar todos los jugadores: " + e.getMessage());
        }
        return lista;

    }

    //EXPORTAR
    public void exportarTxt() throws SeHaProducidoUnError {
        List<Jugador> jugadores = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Jugador j : jugadores) {
            lineas.add(j.getCodigo() + Constantes.SEPARADOR_TXT
                    + j.getNombre() + Constantes.SEPARADOR_TXT
                    + j.getFechaNacimiento() + Constantes.SEPARADOR_TXT
                    + j.getNacionalidad() + Constantes.SEPARADOR_TXT
                    + j.getPosicion());
        }
        MetodosFicheros.exportarTxt(Constantes.FICHERO_JUGADOR, lineas);
    }

    public void exportarCsv() throws SeHaProducidoUnError {
        List<Jugador> jugadores = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Jugador j : jugadores) {
            lineas.add(j.getCodigo() + Constantes.SEPARADOR_CSV
                    + j.getNombre() + Constantes.SEPARADOR_CSV
                    + j.getFechaNacimiento() + Constantes.SEPARADOR_CSV
                    + j.getNacionalidad() + Constantes.SEPARADOR_CSV
                    + j.getPosicion());
        }
        MetodosFicheros.exportarCsv(Constantes.FICHERO_JUGADOR, lineas);
    }

    public void exportarBinario() throws SeHaProducidoUnError {
        List<Jugador> jugadores = consultarTodos();
        MetodosFicheros.exportarBinario(Constantes.FICHERO_JUGADOR, jugadores);
    }

    public void exportarJson() throws SeHaProducidoUnError {
        List<Jugador> jugadores = consultarTodos();
        MetodosFicheros.exportarJson(Constantes.FICHERO_JUGADOR, jugadores);
    }

    //IMPORTAR
    public void importarTxt() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarTxt(Constantes.FICHERO_JUGADOR, txtImportado);
        for (String linea : lineas) {
            String[] campos = linea.split(Constantes.SEPARADOR_TXT);
            insertar(parsearJugador(campos));
        }
        txtImportado = true;
    }
    
    public void importarCsv() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarCsv(Constantes.FICHERO_JUGADOR, csvImportado);
        for (String linea : lineas) {
            String[] campos = linea.split(Constantes.SEPARADOR_CSV);
            insertar(parsearJugador(campos));
        }
        csvImportado = true;
    }
     public void importarBinario() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto, ClassNotFoundException {
        List<Jugador> jugadores = MetodosFicheros.importarBinario(Constantes.FICHERO_JUGADOR, binImportado);
        for (Jugador j : jugadores) {
            insertar(j);
        }
        binImportado = true;
    }
     public void importarJson() throws SeHaProducidoUnError, YaImportadoException, ElDatoIntroducidoEsIncorrecto {
        List<Jugador> jugadores = MetodosFicheros.importarJson(
                Constantes.FICHERO_JUGADOR, jsonImportado,
                new TypeToken<List<Jugador>>(){}.getType());
        for (Jugador j : jugadores) {
            insertar(j);
        }
        jsonImportado = true;
    }
    
    //*******corregir
    //Ajustar Codigo Contador desde el ultimo en la BD
    public static int obtenerUltimoCodigo() throws SeHaProducidoUnError {
        int ultimoCodigo = 0;
        String sql = "Select max(codigo) from jugador";
        try ( Connection con = DriverManager.getConnection(Constantes.URL);//******  
                  PreparedStatement pst = con.prepareStatement(sql);  ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                ultimoCodigo = rs.getInt(1);
                if (rs.wasNull()) {//si la tabla esta vacia
                    ultimoCodigo = 0;
                }
            }

        } catch (SQLException e) {
            throw new SeHaProducidoUnError(e.getMessage());
        }

        return ultimoCodigo;
    }

    private Jugador mapearJugador(ResultSet rs) throws SQLException {
        return new Jugador(
                rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getString("fecha_nacimiento"),
                rs.getString("nacionalidad"),
                rs.getString("posicion")
        );
    }

    private Jugador parsearJugador(String[] campos) throws ElDatoIntroducidoEsIncorrecto {
        if (campos.length < 5) {
            throw new ElDatoIntroducidoEsIncorrecto("Formato de línea incorrecto para jugador.");
        }
        return new Jugador(
                Integer.parseInt(campos[0].trim()),
                campos[1].trim(),
                campos[2].trim(),
                campos[3].trim(),
                campos[4].trim()
        );
    }

    private void validarJugador(Jugador jugador) throws ElDatoIntroducidoEsIncorrecto {
        if (jugador.getNombre() == null || jugador.getNombre().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("El nombre del jugador no puede estar vacío.");
        }
        if (jugador.getFechaNacimiento() == null || jugador.getFechaNacimiento().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("La fecha de nacimiento no puede estar vacía.");
        }
        if (jugador.getNacionalidad() == null || jugador.getNacionalidad().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("La nacionalidad no puede estar vacía.");
        }
        if (jugador.getPosicion() == null || jugador.getPosicion().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("La posición no puede estar vacía.");
        }
    }

    public void verDatosInsertadosSesion() {
        contenedor.mostrarJugadoresSesion();
    }

}
