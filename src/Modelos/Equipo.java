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
public class Equipo implements Serializable {

    // Atributos
    private int contadorCodigo = 0;
    private int codigo;
    private String nombre;
    private int añoFundacion;
    private String lugarSede;
    private String estadio;
    private int sociosAficionados;

    // Constructor
    public Equipo(int codigo, String nombre, int añoFundacion, String lugarSede, String estadio, int sociosAficionados) {
        this.codigo = contadorCodigo++;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.lugarSede = lugarSede;
        this.estadio = estadio;
        this.sociosAficionados = sociosAficionados;
    }

    public Equipo() {
    }
    

    // Metodos
    
    
    // Getters Y Setters
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

    public int getañoFundacion() {
        return añoFundacion;
    }

    public void setañoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    public String getLugarSede() {
        return lugarSede;
    }

    public void setLugarSede(String lugarSede) {
        this.lugarSede = lugarSede;
    }
    
    public String getEstadio() {
        return estadio;
    }
    
     public void setEstadio(String estadio) {
        this.estadio = estadio;
    }
    
    public int getSociosAficionados() {
        return sociosAficionados;
    }
    
    public void setSociosAficionados(int sociosAficionados) {
        this.sociosAficionados = sociosAficionados;
    }

    @Override
    public String toString() {
        return "Equipo{" + "contadorCodigo=" + contadorCodigo +
                ", codigo=" + codigo + ", nombre=" + nombre + 
                ", a\u00f1oFundacion=" + añoFundacion +
                ", lugarSede=" + lugarSede + ", estadio=" + estadio + ", "
                + "sociosAficionados=" + sociosAficionados + '}';
    }
    
    
}
