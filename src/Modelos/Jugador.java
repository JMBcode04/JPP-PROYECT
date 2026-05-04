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
    private int contadorCodigo = 0;
    protected int codigo;
    protected String nombre;
    protected String fechaNacimiento;
    protected String nacionalidad;
    protected String posicion;

    // Constructor
    public Jugador(String nombre, String fechaNacimiento, String nacionalidad, String posicion) {//para el main
        this.codigo = contadorCodigo++;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
    }

    public Jugador(int codigo, String nombre, String fechaNacimiento, String nacionalidad, String posicion) {//para consultar por codigo BD
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
    }
    
    

    // Metodos 
 
    public int getCodigo() {
        return codigo;
    }

    //Auto asignar codigo a jugador
    //hay que contar los jugadores que ya existen en la BD y empezar el codigo desde ahi

    public void setContadorCodigo(int contadorCodigo) {
        this.contadorCodigo = contadorCodigo;
    }
    
}
