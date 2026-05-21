/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;


import java.util.Scanner;

/**
 *
 * @author jorge
 */

/**
 * REALIZADO POR PAMELA Y JORGE
 * @author jorge
 */
public class MenuPrincipal {

    public static void menuPrincipal() {

        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        //MENU PRINCIPAL
        while (menu != 0) {
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Gestion de Jugadores\n"
                    + "2. Gestion de Equipo\n"
                    + "3. Gestion de Partidos\n"
                    + "4. Gestion de Jugador-Equipos\n"
                    + "5. Consultar multitabla\n");

            while (!teclado.hasNextInt()) {
                System.err.println("Error: debes introducir un numero valido.");
                teclado.next(); // limpia el dato incorrecto
            }

            menu = teclado.nextInt();

            switch (menu) {
                case 0:
                    System.out.println("Salir...");
                    break;
                case 1:
                    SubMenuJugadores.submenuJugadores();
                    break;
                case 2:
                    SubMenuEquipos.submenuEquipos();
                    break;
                case 3:
                    SubMenuPartidos.submenuPartidos();
                    break;
                case 4:
                    SubMenuJugadorEquipo.submenuJugadorEquipo();
                    break;
                case 5:
                    SubMenuMultitabla.submenuMultitabla();
                    break;
                default:
                    System.out.println("No esta en el menu");
            }
        }
    }

}
