/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author jorge
 */
public class ContenedorJugador {

    // Atributos 
    private ArrayList<Jugador> listaJugadores;
    private HashMap<Integer, Jugador> mapaJugadores;
    private LinkedList<Jugador> colaJugadores;

    // Constructor
    public ContenedorJugador() {
        
    }


    // Metodos
    
    
    // Añade el jugador introducido a las diferentes colecciones
    public void añadirJugador(Jugador jugador) {
        listaJugadores.add(jugador);
        mapaJugadores.put(jugador.getCodigo(), jugador);
        colaJugadores.add(jugador);
    }

    // Obtiene la lista de todos los jugadores añadidos durante la sesion
    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    // Obtiene un jugador por su codigo
    public Jugador getJugadorPorCodigo(int codigo) {
        return mapaJugadores.get(codigo);
    }

    // Obtiene el mapa completo de los jugadores
    public Map<Integer, Jugador> getMapaJugadores() {
        return mapaJugadores;
    }

    // Obtiene una lista enlazada de los jugadores
    public LinkedList<Jugador> getColaJugadores() {
        return colaJugadores;
    }

    //Devuelve el numero de jugadores almacenados en el contenedor
    public int getTamaño() {
        return listaJugadores.size();
    }

    //Comprueba si el contenedor tiene jugadores o no
    public boolean estaVacio() {
        return listaJugadores.isEmpty();
    }

    // Borra todos los jugadores de las colecciones y del contenedor
    public void limpiar() {
        listaJugadores.clear();
        mapaJugadores.clear();
        colaJugadores.clear();
    }

    // Muestra por consola todos los jugadores que se han añadido durante la sesion
    public void mostrarJugadoresSesion() {
        if (listaJugadores.isEmpty()) {
            System.out.println("No se han insertado jugadores durante esta sesión.");
        } else {
            System.out.println("Jugadores insertados durante esta sesión (" + listaJugadores.size() + "):");
            for (Jugador j : listaJugadores) {
                System.out.println("  " + j.toString());
            }
        }
    }
}
