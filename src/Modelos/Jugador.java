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
public class Jugador implements Serializable {

    // Atributos
    private int contadorCodigo = 0;
    protected int codigo;
    protected String nombre;
    protected String fechaNacimiento;
    protected String nacionalidad;
    protected String posicion;

    // Constructor
    public Jugador(String nombre, String fechaNacimiento, String nacionalidad, String posicion) {//para el main
        this.codigo = codigo;
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

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Jugador{" + "contadorCodigo=" + contadorCodigo + 
                ", codigo=" + codigo + ", "
                + "nombre=" + nombre + ", "
                + "fechaNacimiento=" + fechaNacimiento + 
                ", nacionalidad=" + nacionalidad + ", "
                + "posicion=" + posicion + '}';
    }
    
    

}
