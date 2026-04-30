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
public class Equipo {
    // Atributos

    protected int codigo;
    protected String nombre;
    protected LocalDate añoFundacion;
    protected String lugarSede;
    protected String estadio;
    protected int sociosAficionados;

    // Constructor
    public Equipo(int codigo, String nombre, LocalDate añoFundacion, String lugarSede, String estadio, int sociosAficionados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.lugarSede = lugarSede;
        this.estadio = estadio;
        this.sociosAficionados = sociosAficionados;
    }
    
    // Metodos

}
