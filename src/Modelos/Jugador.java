/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR JORGE
 * @author jorge
 */
// Entidad que representa a un jugador; implementa Serializable para exportacion binaria
public class Jugador implements Serializable {

    // Atributos
    private static int contadorCodigo = 0;

    //Ajustar Codigo Contador desde el ultimo en la BD (JugadorService,main)
    public static void inicializarContador(int valorInicial) {
        contadorCodigo = valorInicial + 1;
    }
    protected int codigo;
    protected String nombre;
    protected String fechaNacimiento; //dd/MM/YYYY
    protected String nacionalidad;
    protected String posicion;

    // Constructor para crear jugadores desde la aplicacion: asigna codigo automaticamente
    public Jugador(String nombre, String fechaNacimiento, String nacionalidad, String posicion) {//para el main
        this.codigo = contadorCodigo++;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
    }

    // Constructor para reconstruir jugadores leidos de la BD (el codigo viene dado)
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

    // Convierte la fecha en String a LocalDate; soporta tanto "YYYY-MM-DD" como "dd/MM/YYYY"
    public LocalDate getFechaNacimientoEnDate() {
        //YYYY-MM-DD
        if (Character.isDigit(fechaNacimiento.charAt(2))) { //YYYY-MM-DD
            int ano = Integer.parseInt(fechaNacimiento.substring(0, 4));
            int mes = Integer.parseInt(fechaNacimiento.substring(5, 7));
            int dia = Integer.parseInt(fechaNacimiento.substring(8, 10));
            return LocalDate.of(ano, mes, dia);
        }
        //dd/MM/YYYY 
        return LocalDate.of(Integer.parseInt(fechaNacimiento.substring(6, 10)), Integer.parseInt(fechaNacimiento.substring(3, 5)), Integer.parseInt(fechaNacimiento.substring(0, 2)));
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
        return "Jugador{"
                + /*"contadorCodigo=" + contadorCodigo + */ "codigo=" + codigo + ", "
                + "nombre=" + nombre + ", "
                + "fechaNacimiento=" + fechaNacimiento + ", "
                + "nacionalidad=" + nacionalidad + ", "
                + "posicion=" + posicion + '}';
    }

}
