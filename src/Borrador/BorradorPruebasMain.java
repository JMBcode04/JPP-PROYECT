/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Borrador;

import Modelos.Jugador;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class BorradorPruebasMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce los datos del jugador;");
        System.out.println("Introduce el codigo:");
        String codigo = teclado.nextLine();
        System.out.println("Introduce el nombre:");
        String nombre = teclado.nextLine();
        System.out.println("Introduce el nacionalidad:");
        String nacionalidad = teclado.nextLine();
        System.out.println("Introduce la fecha:");
        int ano = teclado.nextInt();
        System.out.println("Introduce el posician:");
        String posicion = teclado.nextLine();
        //Crear Objeto
        //...
        Jugador j1 = new Jugador(ano, nombre, LocalDate.MIN, nacionalidad, posicion);
        //Insertar objeto a la BD
        //...
    }

}
