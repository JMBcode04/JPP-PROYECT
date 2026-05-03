/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;

/**
 *
 * @author jorge
 */
public class Jugador_equipo extends Jugador {
    // Atributos

    protected int codigoEquipo;
    protected int codigoJugador;
    protected int añoEntrada;
    protected int añoSalida;
    protected int partidosTitular;

    // Constructor
    public Jugador_equipo(int codigoEquipo, int codigoJugador, int añoEntrada, int añoSalida, int partidosTitular, int codigo, String nombre, String fechaNacimiento, String nacionalidad, String posicion) {
        super(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
        this.codigoEquipo = codigoEquipo;
        this.codigoJugador = codigoJugador;
        this.añoEntrada = añoEntrada;
        this.añoSalida = añoSalida;
        this.partidosTitular = partidosTitular;
    }

    // Metodos
}
