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
public class SubMenuJugadores {
    public static void submenuJugadores() {//menu gestion jugadores
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {

            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar Jugador\n"
                    + "2. Actualizar Jugador\n"
                    + "3. Eliminar Jugador\n"
                    + "4. Consultar un Jugador\n"
                    + "5. Consultar Jugadores\n"
                    + "6. Exportar a TXT\n"
                    + "7. Exportar a CSV\n"
                    + "8. Exportar a Binario\n"
                    + "9. Exportar a JSON\n"
                    + "10. Importar desde TXT\n"
                    + "11. Importar desde CSV\n"
                    + "12. Importar desde Binario\n"
                    + "13. Importar desde JSON\n"
                    + "14. Ver datos insertados durante la ejecucion\n");

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

                case 10:

                    break;

                case 11:

                    break;
                case 12:

                    break;

                case 13:

                    break;
                case 14:

                    break;

                default:
                    System.out.println("No esta en el menu");
            }
        }
    }
}
