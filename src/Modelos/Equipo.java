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
/**
 * REALIZADO POR JORGE
 * @author jorge
 */
public class Equipo implements Serializable {

    // Atributos
    private int contadorCodigo;
    private int codigo;
    private String nombre;
    private int añoFundacion;
    private String lugarSede;
    private String localidad;
    private String provincia;
    private String estadio;
    private int sociosAficionados;

    // Constructor usado desde consola: recibe lugarSede como cadena "localidad, provincia"
    public Equipo(int codigo, String nombre, int añoFundacion, String lugarSede, String estadio, int sociosAficionados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        setLugarSede(lugarSede);
        this.estadio = estadio;
        this.sociosAficionados = sociosAficionados;
    }

    // Constructor usado al recuperar datos de la BD: recibe localidad y provincia por separado
    public Equipo(int codigo, String nombre, int añoFundacion,
            String localidad, String provincia, String estadio, int sociosAficionados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.localidad = localidad != null ? localidad.trim() : "";
        this.provincia = provincia != null ? provincia.trim() : "";
        // reconstruimos lugarSede para que el Swing lo muestre correctamente
        if (this.provincia.isBlank()) {
            this.lugarSede = this.localidad;
        } else {
            this.lugarSede = this.localidad + ", " + this.provincia;
        }
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

    // Al asignar lugarSede, divide por la coma para extraer localidad y provincia
    public void setLugarSede(String lugarSede) {
        this.lugarSede = lugarSede != null ? lugarSede.trim() : "";
        int coma = this.lugarSede.indexOf(","); //Zaragoza, Aragón
        if (coma >= 0) {
            this.localidad = this.lugarSede.substring(0, coma).trim(); //Zaragoza
            this.provincia = this.lugarSede.substring(coma + 1).trim();
            System.out.println("Localidad: " + this.localidad);
            System.out.println("Provincia: " + this.provincia);
        } else {
            this.localidad = this.lugarSede;
            this.provincia = "";
        }
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

    public String getLocalidad() {
        return localidad;
    }

    // Al cambiar localidad, sincroniza lugarSede
    public void setLocalidad(String localidad) {
        this.localidad = localidad != null ? localidad.trim() : "";
        // mantener lugarSede sincronizado
        if (this.provincia == null || this.provincia.isBlank()) {
            this.lugarSede = this.localidad;
        } else {
            this.lugarSede = this.localidad + ", " + this.provincia;
        }
    }

    public String getProvincia() {
        return provincia;
    }
    
    // Al cambiar provincia, sincroniza lugarSede
    public void setProvincia(String provincia) {
        this.provincia = provincia != null ? provincia.trim() : "";
        // mantener lugarSede sincronizado
        if (this.provincia.isBlank()) {
            this.lugarSede = this.localidad;
        } else {
            this.lugarSede = this.localidad + ", " + this.provincia;
        }
    }

    @Override
    public String toString() {
        return "Equipo{" + "contadorCodigo=" + contadorCodigo
                + ", codigo=" + codigo + ", nombre=" + nombre
                + ", a\u00f1oFundacion=" + añoFundacion
                + ", lugarSede=" + lugarSede + ", estadio=" + estadio + ", "
                + "sociosAficionados=" + sociosAficionados + '}';
    }

}
