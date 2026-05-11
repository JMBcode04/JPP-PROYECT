/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Modelos.ContenedorEquipos;
import Modelos.ContenedorJugador;
import Modelos.ContenedorPartidos;
import Modelos.Jugador;
import Servicios.EquipoService;
import Servicios.InformesService;
import Servicios.JugadorEquipoService;
import Servicios.JugadorService;
import Servicios.MetodosBaseDeDatos;
import Servicios.PartidoService;
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
    // CONTENEDORES
    private static ContenedorEquipos contenedorEquipos = new ContenedorEquipos();
    private static ContenedorJugador contenedorJugador = new ContenedorJugador();
    private static ContenedorPartidos contenedorPartidos = new ContenedorPartidos();

    //SERVICIOS
    private static EquipoService equipoService = new EquipoService(contenedorEquipos);
    private static JugadorService jugadorService = new JugadorService(contenedorJugador);
    private static JugadorEquipoService jugadorEquipoService = new JugadorEquipoService();
    private static PartidoService partidoService = new PartidoService(contenedorPartidos);
    private static InformesService informesService = new InformesService();

    // SACNNER 
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Ajustar Codigo jugador Contador desde el ultimo en la BD (JugadorService)
        try {
            int ultimoCodigo = JugadorService.obtenerUltimoCodigo();
            Jugador.inicializarContador(ultimoCodigo);
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error al inicializar: " + e.getMessage());
        }

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

    public static void submenuEquipos() {//menu gestion equipos
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar Equipo\n"
                    + "2. Actualizar Equipo\n"
                    + "3. Eliminar Equipo\n"
                    + "4. Consultar un Equipo por codigo\n"
                    + "5. Consultar todos los Equipos\n"
                    + "6. Exportar a TXT\n"
                    + "7. Exportar a CSV\n"
                    + "8. Exportar a Binario\n"
                    + "9. Exportar a JSON\n"
                    + "10. Importar desde TXT\n"
                    + "11. Importar desde CSV\n"
                    + "12. Importar desde Binario\n"
                    + "13. Importar desde JSON\n"
                    + "14. Ver datos insertados durante la ejecucion\n");
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

    public static void submenuPartidos() {//menu gestion partidos
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar Partido\n"
                    + "2. Actualizar Partido\n"
                    + "3. Eliminar Partido\n"
                    + "4. Consultar un Partido por codigo\n"
                    + "5. Consultar todos los Partidos\n"
                    + "6. Exportar a TXT\n"
                    + "7. Exportar a CSV\n"
                    + "8. Exportar a Binario\n"
                    + "9. Exportar a JSON\n"
                    + "10. Importar desde TXT\n"
                    + "11. Importar desde CSV\n"
                    + "12. Importar desde Binario\n"
                    + "13. Importar desde JSON\n"
                    + "14. Ver datos insertados durante la ejecucion\n");
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

    public static void submenuJugadorEquipo() {//menu gestion jugador-equipo
        Scanner teclado = new Scanner(System.in);
        int menu = 1;

        while (menu != 0) {//no se añade eliminar porque es algo del historial (3. se consultaria cuando se da de baja)
            System.out.println("Introduce un numero del menu");
            System.out.println("0. Salir\n"
                    + "1. Insertar relacion jugador-equipo\n"//el jugador 10 entra en el equipo 2 el 2024
                    + "2. Actualizar relacion jugador-equipo\n"
                    + "3. Eliminar relación jugador-equipo"
                    + "4. Consultar una relacion jugador-equipo\n"
                    + "5. Consultar todas las relaciones jugador-equipo\n"
                    + "6. Exportar a TXT\n"
                    + "7. Exportar a CSV\n"
                    + "8. Exportar a Binario\n"
                    + "9. Exportar a JSON\n"
                    + "10. Importar desde TXT\n"
                    + "11. Importar desde CSV\n"
                    + "12. Importar desde Binario\n"
                    + "13. Importar desde JSON\n"
                    + "14. Ver datos insertados durante la ejecucion\n");
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
