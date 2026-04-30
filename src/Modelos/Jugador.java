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
public class Jugador {
    
    // Atributos
    protected int codigo;
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected String nacionalidad;
    protected String posicion;         
    
    // Constructor
      public Jugador(int codigo, String nombre, LocalDate fechaNacimiento, String nacionalidad, String posicion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
    }
      
    // Metodos

  
    
    
}
