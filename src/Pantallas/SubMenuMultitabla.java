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
public class SubMenuMultitabla {

    public static void submenuMultitabla() {//menu consulta multitablas
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Equipos y jugadores actuales\n"
                    + "2. Partidos y puntuaciones\n"
                    + "3. Historial de equipos de jugador \n"
                    + "4. Total puntos local por temporada\n"
                    + "5. Total equipos y partidos como titular por jugador\n"
                    + "6. Jugadores mas de 5 anios en equipo\n"
                    + "7. Jugadores libres\n"
                    + "8. Partidos con suma de puntuacion mayor a X\n"
                    + "9. Equipos por ubicacion y sus socios\n");
            menu = teclado.nextInt();

            switch (menu) {
                case 0:
                    System.out.println("Salir...");
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                default:
                    System.out.println("No esta en el menu");
            }
        }

    }
}
