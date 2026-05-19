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
public class Partido implements Serializable {

    // Atributos
    protected int codigoEquipoLocal;
    protected int codigoEquipoVisitante;
    protected int añoTemporada;
    protected String fecha;
    protected Integer puntuacionLocal;
    protected Integer puntuacionVisitante;

    // Constructor
    public Partido(int codigoEquipoLocal, int codigoEquipoVisitante, int añoTemporada, String fecha, Integer puntuacionLocal, Integer puntuacionVisitante) {
        this.codigoEquipoLocal = codigoEquipoLocal;
        this.codigoEquipoVisitante = codigoEquipoVisitante;
        this.añoTemporada = añoTemporada;
        this.fecha = fecha;
        this.puntuacionLocal = puntuacionLocal;
        this.puntuacionVisitante = puntuacionVisitante;
    }

    public Partido() {
    }

    // Metodos
    public int getCodigoEquipoLocal() {
        return codigoEquipoLocal;
    }

    public void setCodigoEquipoLocal(int codigoEquipoLocal) {
        this.codigoEquipoLocal = codigoEquipoLocal;
    }

    public int getCodigoEquipoVisitante() {
        return codigoEquipoVisitante;
    }

    public void setCodigoEquipoVisitante(int codigoEquipoVisitante) {
        this.codigoEquipoVisitante = codigoEquipoVisitante;
    }

    public int getAñoTemporada() {
        return añoTemporada;
    }

    public void setAñoTemporada(int añoTemporada) {
        this.añoTemporada = añoTemporada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public LocalDate getfechaEnDate() {
        //dd/MM/YYYY
        return LocalDate.of(Integer.parseInt(fecha.substring(6, 10)), Integer.parseInt(fecha.substring(3, 5)), Integer.parseInt(fecha.substring(0, 2)));
    }

    public Integer getPuntuacionLocal() {
        return puntuacionLocal;
    }

    public void setPuntuacionLocal(Integer puntuacionLocal) {
        this.puntuacionLocal = puntuacionLocal;
    }

    public Integer getPuntuacionVisitante() {
        return puntuacionVisitante;
    }

    public void setPuntuacionVisitante(Integer puntuacionVisitante) {
        this.puntuacionVisitante = puntuacionVisitante;
    }

    // Devuelve los datos del partido 
    @Override
    public String toString() {
        return "Partido{" + "codigoEquipoLocal=" + codigoEquipoLocal
                + ", codigoEquipoVisitante=" + codigoEquipoVisitante + ", "
                + "a\u00f1oTemporada=" + añoTemporada + ", "
                + "fecha=" + fecha + ", "
                + "puntuacionLocal=" + puntuacionLocal + ", "
                + "puntuacionVisitante=" + puntuacionVisitante + '}';
    }

}
