/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Interfaces.MetodosComunes;
import Modelos.ContenedorEquipos;
import Modelos.Equipo;
import Utils.Constantes;
import com.google.gson.reflect.TypeToken;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR JORGE
 * @author jorge
 */
// Servicio que implementa el CRUD de Equipo contra la BD y la gestion de ficheros
// Usa ContenedorEquipos para llevar un registro de los equipos insertados en sesion
public class EquipoService implements MetodosComunes<Equipo> {

    // Atributos
    // Sirve como memoria para almacenar los equipos introducidos durante la sesion
    private ContenedorEquipos contenedor;

    // Son controladores para 

    // Constructor
    public EquipoService(ContenedorEquipos contenedor) {
        this.contenedor = contenedor;
    }

    @Override
    public void insertar(Equipo entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarEquipo(entidad);
        String sql = "INSERT INTO equipo (codigo, nombre, año_fundacion, localidad, provincia, estadio, socios_aficionados) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.println("Provincia: " + entidad.getProvincia());

            ps.setInt(1, entidad.getCodigo());
            ps.setString(2, entidad.getNombre());
            ps.setInt(3, entidad.getañoFundacion());
            ps.setString(4, entidad.getLocalidad());   // columna real BD
            ps.setString(5, entidad.getProvincia());   // columna real BD
            ps.setString(6, entidad.getEstadio());
            ps.setInt(7, entidad.getSociosAficionados());
            ps.executeUpdate();
            contenedor.añadirEquipo(entidad);
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al insertar equipo: " + e.getMessage());
        }

    }

    @Override
    public void actualizar(Equipo entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarEquipo(entidad);
        String sql = "UPDATE equipo SET nombre=?, año_fundacion=?, localidad=?, provincia=?, estadio=?, socios_aficionados=? "
                + "WHERE codigo=?";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setInt(2, entidad.getañoFundacion());
            ps.setString(3, entidad.getLocalidad());
            ps.setString(4, entidad.getProvincia());
            ps.setString(5, entidad.getEstadio());
            ps.setInt(6, entidad.getSociosAficionados());
            ps.setInt(7, entidad.getCodigo());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al actualizar equipo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int codigo) throws SeHaProducidoUnError {
        String sql = "DELETE FROM equipo WHERE codigo=?";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SeHaProducidoUnError();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al eliminar equipo: " + e.getMessage());
        }

    }

    @Override
    public Equipo consultar(int codigo) throws SeHaProducidoUnError {
        String sql = "SELECT * FROM equipo WHERE codigo=?";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearEquipo(rs);
                } else {
                    throw new SeHaProducidoUnError();
                }
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al consultar equipo: " + e.getMessage());
        }
    }

    @Override
    public List<Equipo> consultarTodos() throws SeHaProducidoUnError {
        String sql = "SELECT * FROM equipo ORDER BY nombre ASC";
        List<Equipo> lista = new ArrayList<>();
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearEquipo(rs));
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al consultar todos los equipos: " + e.getMessage());
        }
        return lista;
    }

    // Metodos de exportacion e importacion a TXT de Equipos
    public void exportarTxt() throws SeHaProducidoUnError {
        List<Equipo> equipos = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Equipo e : equipos) {
            lineas.add(e.getCodigo() + Constantes.SEPARADOR_TXT
                    + e.getNombre() + Constantes.SEPARADOR_TXT
                    + e.getañoFundacion() + Constantes.SEPARADOR_TXT
                    + e.getLugarSede() + Constantes.SEPARADOR_TXT
                    + e.getEstadio() + Constantes.SEPARADOR_TXT
                    + e.getSociosAficionados());
        }
        MetodosFicheros.exportarTxt(Constantes.FICHERO_EQUIPO, lineas);
    }

    public void importarTxt() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarTxt(Constantes.FICHERO_EQUIPO);
        for (String linea : lineas) {
            try {
                String[] campos = linea.split(Constantes.SEPARADOR_TXT);
                Equipo e = parsearEquipo(campos);
                if (!existeEnBD(e.getCodigo())) {
                    insertar(e);
                } else {
                    System.out.println("Equipo duplicado ignorado, codigo: " + e.getCodigo());
                }
            } catch (ElDatoIntroducidoEsIncorrecto ex) {
                System.out.println("Linea con datos incorrectos ignorada: " + linea);
            }
        }
    }

    // Metodos de exportacion e importacion a binario de Equipos
    public void exportarBinario() throws SeHaProducidoUnError {
        List<Equipo> equipos = consultarTodos();
        MetodosFicheros.exportarBinario(Constantes.FICHERO_EQUIPO, equipos);
    }

    public void importarBinario() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto, ClassNotFoundException {
        List<Equipo> equipos = MetodosFicheros.importarBinario(Constantes.FICHERO_EQUIPO);
        for (Equipo e : equipos) {
            try {
                if (!existeEnBD(e.getCodigo())) {
                    insertar(e);
                } else {
                    System.out.println("Equipo duplicado ignorado, codigo: " + e.getCodigo());
                }
            } catch (ElDatoIntroducidoEsIncorrecto ex) {
                System.out.println("Equipo con datos incorrectos ignorado: " + e.getCodigo());
            }
        }
    }

    // Metodos de exportacion e importacion a CSV de Equipos
    public void exportarCsv() throws SeHaProducidoUnError {
        List<Equipo> equipos = consultarTodos();
        List<String> lineas = new ArrayList<>();
        for (Equipo e : equipos) {
            lineas.add(e.getCodigo() + Constantes.SEPARADOR_CSV
                    + e.getNombre() + Constantes.SEPARADOR_CSV
                    + e.getañoFundacion() + Constantes.SEPARADOR_CSV
                    + e.getLugarSede() + Constantes.SEPARADOR_CSV
                    + e.getEstadio() + Constantes.SEPARADOR_CSV
                    + e.getSociosAficionados());
        }
        MetodosFicheros.exportarCsv(Constantes.FICHERO_EQUIPO, lineas);
    }

    public void importarCsv() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<String> lineas = MetodosFicheros.importarCsv(Constantes.FICHERO_EQUIPO);
        for (String linea : lineas) {
            try {
                String[] campos = linea.split(Constantes.SEPARADOR_CSV);
                Equipo e = parsearEquipo(campos);
                if (!existeEnBD(e.getCodigo())) {
                    insertar(e);
                } else {
                    System.out.println("Equipo duplicado ignorado, codigo: " + e.getCodigo());
                }
            } catch (ElDatoIntroducidoEsIncorrecto ex) {
                System.out.println("Linea con datos incorrectos ignorada: " + linea);
            }
        }
    }

    // Metodos de exportacion y importacion a JSON de Equipos
    public void exportarJson() throws SeHaProducidoUnError {
        List<Equipo> equipos = consultarTodos();
        MetodosFicheros.exportarJson(Constantes.FICHERO_EQUIPO, equipos);
    }

    public void importarJson() throws SeHaProducidoUnError, ElDatoIntroducidoEsIncorrecto {
        List<Equipo> equipos = MetodosFicheros.importarJson(
                Constantes.FICHERO_EQUIPO,
                new TypeToken<List<Equipo>>() {
                }.getType());
        for (Equipo e : equipos) {
            try {
                if (!existeEnBD(e.getCodigo())) {
                    insertar(e);
                } else {
                    System.out.println("Equipo duplicado ignorado, codigo: " + e.getCodigo());
                }
            } catch (ElDatoIntroducidoEsIncorrecto ex) {
                System.out.println("Equipo con datos incorrectos ignorado: " + e.getCodigo());
            }
        }
    }

    // Sirve para mapear una fila mediante el ResultSet a un objeto en este caso de Equipo
    private Equipo mapearEquipo(ResultSet rs) throws SQLException {
        return new Equipo(
                rs.getInt("codigo"),
                rs.getString("nombre"),
                rs.getInt("año_fundacion"),
                rs.getString("localidad"),
                rs.getString("provincia"),
                rs.getString("estadio"),
                rs.getInt("socios_aficionados")
        );
    }

    // Metodo para validar el equipo
    private void validarEquipo(Equipo equipo) throws ElDatoIntroducidoEsIncorrecto {
        if (equipo.getNombre() == null || equipo.getNombre().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("El nombre del equipo no puede estar vacío.");
        }
        if (equipo.getLugarSede() == null || equipo.getLugarSede().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("El lugar de sede no puede estar vacío.");
        }
        if (equipo.getEstadio() == null || equipo.getEstadio().isBlank()) {
            throw new ElDatoIntroducidoEsIncorrecto("El estadio no puede estar vacío.");
        }
        if (equipo.getañoFundacion() <= 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El año de fundación debe ser un número positivo.");
        }
        if (equipo.getSociosAficionados() < 0) {
            throw new ElDatoIntroducidoEsIncorrecto("El número de socios no puede ser negativo.");
        }
    }

    private Equipo parsearEquipo(String[] campos) throws ElDatoIntroducidoEsIncorrecto {
        if (campos.length < 6) {
            throw new ElDatoIntroducidoEsIncorrecto("Formato de línea incorrecto para equipo.");
        }
        return new Equipo(
                Integer.parseInt(campos[0].trim()),
                campos[1].trim(),
                Integer.parseInt(campos[2].trim()),
                campos[3].trim(),
                campos[4].trim(),
                Integer.parseInt(campos[5].trim())
        );
    }

    // Muestra los equipos insertados durante la sesion actual
    public void verDatosInsertadosSesion() {
        contenedor.mostrarEquiposSesion();
    }

    private boolean existeEnBD(int codigo) throws SeHaProducidoUnError {
        String sql = "SELECT codigo FROM equipo WHERE codigo = ?";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al comprobar equipo en BD: " + e.getMessage());
        }
    }

}
