/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author jorge
 */
public class ContenedorEquipos {

    // Atributos 
    private ArrayList<Equipo> listaEquipos;
    private HashMap<Integer, Equipo> mapaEquipos;
    private TreeSet<Equipo> conjuntoEquiposOrdenados;

    // Constructor
    public ContenedorEquipos(ArrayList<Equipo> listaEquipos, HashMap<Integer, Equipo> mapaEquipos, TreeSet<Equipo> conjuntoEquiposOrdenados) {
        this.listaEquipos = listaEquipos;
        this.mapaEquipos = mapaEquipos;
        this.conjuntoEquiposOrdenados = conjuntoEquiposOrdenados;
    }

    // Metodos
    // Añade el equipo insertado en cada uno de las colecciones
    public void añadirEquipo(Equipo equipo) {
        listaEquipos.add(equipo);
        mapaEquipos.put(equipo.getCodigo(), equipo);
        conjuntoEquiposOrdenados.add(equipo);
    }

    // Devuelve los equipos introducidos en la sesion
    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    // Obtiene introduciendo el codigo el mapa
    public Equipo getEquipoPorCodigo(int codigo) {
        return mapaEquipos.get(codigo);
    }

    // Obtiene el mapa completo de los equipos
    public Map<Integer, Equipo> getMapaEquipos() {
        return mapaEquipos;
    }

    // Obtiene el conjunto de equipos ordenados alfabeticamente por nombre
    public TreeSet<Equipo> getConjuntoEquiposOrdenados() {
        return conjuntoEquiposOrdenados;
    }

    // Devuelve el numero completo de equipos almacenados en el contenedor
    public int getTamaño() {
        return listaEquipos.size();
    }

    // Comprueba dsi el contenedor esta vacio
    public boolean estaVacio() {
        return listaEquipos.isEmpty();
    }

    // Borra todos los equipos del contenedor
    public void limpiar() {
        listaEquipos.clear();
        mapaEquipos.clear();
        conjuntoEquiposOrdenados.clear();
    }

    // Muestra por la consola los equipos introducidos desde el arranque de la aplicacion
    public void mostrarEquiposSesion() {
        if (listaEquipos.isEmpty()) {
            System.out.println("No se han insertado equipos durante esta sesión.");
        } else {
            System.out.println("Equipos insertados durante esta sesión (" + listaEquipos.size() + "):");
            for (Equipo e : listaEquipos) {
                System.out.println("  " + e.toString());
            }
        }
    }

    public void añadirPartido(Partido entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
