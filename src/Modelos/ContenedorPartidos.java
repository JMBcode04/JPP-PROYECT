/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author jorge
 */
public class ContenedorPartidos {

    // Atributos 
    private ArrayList<Partido> listaPartidos= new ArrayList<>();
    private HashMap<String, Partido> mapaPartidos = new HashMap<>();
    private HashSet<Integer> temporadasRegistradas = new HashSet<>();

    // Constructor
    public ContenedorPartidos() { 
    }

    // Metodos
    // Genera una clave para poder identificar el partido 
    private String generarClave(int codigoLocal, int codigoVisitante, int anioTemporada) {
        return codigoLocal + "-" + codigoVisitante + "-" + anioTemporada;
    }

    // Añade los partidos a los distintos contenedores
    public void añadirPartido(Partido partido) {
        listaPartidos.add(partido);
        String clave = generarClave(partido.getCodigoEquipoLocal(),
                partido.getCodigoEquipoVisitante(),
                partido.getAñoTemporada());
        mapaPartidos.put(clave, partido);
        temporadasRegistradas.add(partido.getAñoTemporada());
    }

    // Genera una clave para poder identificar los partidos
    public Partido getPartido(int codigoLocal, int codigoVisitante, int anioTemporada) {
        return mapaPartidos.get(generarClave(codigoLocal, codigoVisitante, anioTemporada));
    }

    // Te devuelve la lista de partidos que has insertado durante la sesion
    public ArrayList<Partido> getListaPartidos() {
        return listaPartidos;
    }

    // Obtiene el mapa de todos los partidos
    public HashMap<String, Partido> getMapaPartidos() {
        return mapaPartidos;
    }

    // Obtiene el conjunto de temporadas distitnas 
    public HashSet<Integer> getTemporadasRegistradas() {
        return temporadasRegistradas;
    }

    // Te devuelve el numero de partidos que hay en el contenedor
    public int getTamaño() {
        return listaPartidos.size();
    }

    // Verifica si el contenedor esta vacio 
    public boolean estaVacio() {
        return listaPartidos.isEmpty();
    }

    // Borra los partidos de todos las colecciones y del contenedor
    public void limpiar() {
        listaPartidos.clear();
        mapaPartidos.clear();
        temporadasRegistradas.clear();
    }

    // Muestra los partidos insertados durante la sesion
    public void mostrarPartidosSesion() {
        if (listaPartidos.isEmpty()) {
            System.out.println("No se han insertado partidos durante esta sesión.");
        } else {
            System.out.println("Partidos insertados durante esta sesión (" + listaPartidos.size() + "):");
            for (Partido p : listaPartidos) {
                System.out.println("  " + p.toString());
            }
        }
    }
}
