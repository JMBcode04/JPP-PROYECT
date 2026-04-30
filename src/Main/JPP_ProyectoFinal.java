/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Modelos.Jugador;
import Servicios.MetodosBaseDeDatos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class JPP_ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        menuPrincipal();

    }

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
            menu = teclado.nextInt();

            switch (menu) {
                case 0:
                    System.out.println("Salir...");
                    break;
                case 1:
                    submenuJugadores();
                    break;
                case 2:
                    submenuEquipos();
                    break;
                case 3:
                    submenuPartidos();
                    break;
                case 4:
                    submenuJugadorEquipo();
                    break;
                case 5:
                    submenuMultitabla();
                    break;
                default:
                    System.out.println("No esta en el menu");
            }
        }
    }

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
                    + "6. Exportar la tabla\n"
                    + "7. Importar la tabla\n"
                    + "8. Ver datos insertados durante la ejecucion\n");
            menu = teclado.nextInt();

            switch (menu) {
                case 0:
                    System.out.println("Salir...");
                    break;
                case 1:

                    System.err.println("Inserta el codigo del jugador: ");
                    int codigo = teclado.nextInt();
                    System.out.println("Inserta el nombre del jugador: ");
                    String nombre = teclado.nextLine();
                    System.out.println("Inserta la fecha de nacimiento del jugador: (yyyy/MM/DD) ");
                    String fechaNacimiento = teclado.nextLine();
                    // Definir el formato que estás usando
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    // Convertir String a LocalDate
                    LocalDate fecha = LocalDate.parse(fechaNacimiento, formatter);

                    System.out.println("Inserta la nacionalidad del jugador: ");
                    String nacionalidad = teclado.nextLine();
                    System.out.println("Inserta la posicion del jugador: ");
                    String posicion = teclado.nextLine();

                    Jugador j1 = new Jugador(codigo, nombre, LocalDate.MIN, nacionalidad, posicion);
                    
                    
                            

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

                default:
                    System.out.println("No esta en el menu");
            }
        }

    }

    public static void submenuEquipos() {//menu gestion equipos
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar Equipo\n"
                    + "2. Actualizar Equipo\n"
                    + "3. Eliminar Equipo\n"
                    + "4. Consultar un Equipo\n"
                    + "5. Consultar Equipos\n"
                    + "6. Exportar la tabla\n"
                    + "7. Importar la tabla\n"
                    + "8. Ver datos insertados durante la ejecucion\n");
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

                default:
                    System.out.println("No esta en el menu");
            }
        }

    }

    public static void submenuPartidos() {//menu gestion partidos
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar Partido\n"
                    + "2. Actualizar Partido\n"
                    + "3. Eliminar Partido\n"
                    + "4. Consultar un Partido\n"
                    + "5. Consultar Partidos\n"
                    + "6. Exportar la tabla\n"
                    + "7. Importar la tabla\n"
                    + "8. Ver datos insertados durante la ejecucion\n");
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

                default:
                    System.out.println("No esta en el menu");
            }
        }

    }

    public static void submenuJugadorEquipo() {//menu gestion jugador-equipo
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {//no se añade eliminar porque es algo del historial (3. se consultaria cuando se da de baja)
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar relacion\n"//el jugador 10 entra en el equipo 2 el 2024
                    + "2. Actualizar relacion\n"
                    + "3. Consultar una relacion\n"
                    + "4. Consultar relaciones\n"
                    + "5. Exportar la tabla\n"
                    + "6. Importar la tabla\n"
                    + "7. Ver datos insertados durante la ejecucion\n");
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
                default:
                    System.out.println("No esta en el menu");
            }
        }

    }

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
