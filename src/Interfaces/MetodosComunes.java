/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import java.util.List;

/**
 *
 * @author jorge
 * @param <T>
 */

/**
 * REALIZADO POR JORGE
 * @author jorge
 */
public interface MetodosComunes<T> {

    // Insertar una nueva entidad en la base de datos y en el contenedor en memoria
    void insertar(T entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError;

    // Actualiza la entidad 
    void actualizar(T entidad) throws ElDatoIntroducidoEsIncorrecto, SeHaProducidoUnError;

    // Elimina la entidad introduciendo su codigo
    void eliminar(int codigo) throws SeHaProducidoUnError;

    // Consulta la entidad mediante su codigo 
    T consultar(int codigo) throws SeHaProducidoUnError;

    // Obtiene todas las listas de la tabla y las ordena de forma ascendente por nombre
    List<T> consultarTodos() throws SeHaProducidoUnError;

}
