/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author jorge
 */
public class Jugador_equipo implements Serializable {
    // Atributos

    protected int codigoEquipo;
    protected int codigoJugador;
    protected int añoEntrada;
    protected Integer añoSalida;
    protected Integer partidosTitular;

    // Constructor
    public Jugador_equipo(int codigoEquipo, int codigoJugador, int añoEntrada, Integer añoSalida, Integer partidosTitular) {
        this.codigoEquipo = codigoEquipo;
        this.codigoJugador = codigoJugador;
        this.añoEntrada = añoEntrada;
        this.añoSalida = añoSalida;
        this.partidosTitular = partidosTitular;
    }

    public Jugador_equipo() {
    }

    // Metodos
    public int getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(int codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    public int getCodigoJugador() {
        return codigoJugador;
    }

    public void setCodigoJugador(int codigoJugador) {
        this.codigoJugador = codigoJugador;
    }

    public int getAñoEntrada() {
        return añoEntrada;
    }

    public void setañoEntrada(int añoEntrada) {
        this.añoEntrada = añoEntrada;
    }

    public Integer getAñoSalida() {
        return añoSalida;
    }

    public void setAñoSalida(Integer añoSalida) {
        this.añoSalida = añoSalida;
    }

    public Integer getPartidosTitular() {
        return partidosTitular;
    }

    public void setPartidosTitular(Integer partidosTitular) {
        this.partidosTitular = partidosTitular;
    }

    @Override
    public String toString() {
        return "Jugador_equipo{" + "codigoEquipo=" + codigoEquipo + 
                ", codigoJugador=" + codigoJugador + 
                ", a\u00f1oEntrada=" + añoEntrada + 
                ", a\u00f1oSalida=" + añoSalida + 
                ", partidosTitular=" + partidosTitular + '}';
    }   

}
