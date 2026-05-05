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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class EquipoService implements MetodosComunes<Equipo> {

    // Atributos
    // Sirve como memoria para almacenar los equipos introducidos durante la sesion
    private ContenedorEquipos contenedor;

    // Son controladores para la importacion de cada tipo de fichero
    private boolean txtImportado = false;
    private boolean csvImportado = false;
    private boolean binImportado = false;
    private boolean jsonImportado = false;

    // Constructor
    public EquipoService(ContenedorEquipos contenedor) {
        this.contenedor = contenedor;
    }

    @Override
    public void insertar(Equipo entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
        validarEquipo(entidad);
        String sql = "INSERT INTO equipo (codigo, nombre, año_fundacion, lugar_sede, estadio, socios_aficionados) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = MetodosBaseDeDatos.AccederBaseDeDatos();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, entidad.getCodigo());
            ps.setString(2, entidad.getNombre());
            ps.setInt(3, entidad.getañoFundacion());
            ps.setString(4, entidad.getLugarSede());
            ps.setString(5, entidad.getEstadio());
            ps.setInt(6, entidad.getSociosAficionados());
            ps.executeUpdate();
            contenedor.añadirEquipo(entidad);
        } catch (SQLException e) {
            throw new SeHaProducidoUnError("Error al insertar equipo: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Equipo entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError {
    }

    @Override
    public void eliminar(int codigo) throws SeHaProducidoUnError {
    }

    @Override
    public Equipo consultar(int codigo) throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Equipo> consultarTodos() throws SeHaProducidoUnError {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    // Metodoo para validar el equipo
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
    
}
