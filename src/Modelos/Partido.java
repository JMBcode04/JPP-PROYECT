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
public class Partido {
    // Atributos

    protected int codigoEquipoLocal;
    protected int codigoEquipoVisitante;
    protected int añoTemporada;
    protected LocalDate fecha;
    protected int partidoTitular;

    // Constructor
    public Partido(int codigoEquipoLocal, int codigoEquipoVisitante, int añoTemporada, LocalDate fecha, int partidoTitular) {
        this.codigoEquipoLocal = codigoEquipoLocal;
        this.codigoEquipoVisitante = codigoEquipoVisitante;
        this.añoTemporada = añoTemporada;
        this.fecha = fecha;
        this.partidoTitular = partidoTitular;
    }
    
    // Metodos

}
