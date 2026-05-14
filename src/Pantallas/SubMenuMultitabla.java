/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Excepciones.SeHaProducidoUnError;
import Main.JPP_ProyectoFinal;
import Utils.Constantes;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class SubMenuMultitabla {

    private static Scanner scanner = new Scanner(System.in);

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
                case 1: {
                    try {
                        JPP_ProyectoFinal.informesService.informeJugadoresActualesPorEquipo();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 2: {
                    try {
                        JPP_ProyectoFinal.informesService.informePartidosConEquipos();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 3: {
                    try {
                        JPP_ProyectoFinal.informesService.informeHistorialJugador();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 4:
                    System.out.print("  Introduce el año de temporada: ");
                    int año = leerEntero();
                     {
                        try {
                            JPP_ProyectoFinal.informesService.informePuntosLocalPorTemporada(año);
                        } catch (SeHaProducidoUnError ex) {
                            Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case 5: {
                    try {
                        JPP_ProyectoFinal.informesService.informeResumenCarreraJugadores();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 6: {
                    try {
                        JPP_ProyectoFinal.informesService.informeEquiposConJugadoresFieles();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 7: {
                    try {
                        JPP_ProyectoFinal.informesService.informeJugadoresLibres();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 8:
                    System.out.print("  Introduce el valor mínimo de puntuación total: ");
                    int valor = leerEntero();
                     {
                        try {
                            JPP_ProyectoFinal.informesService.informePartidosAltaPuntuacion(valor);
                        } catch (SeHaProducidoUnError ex) {
                            Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case 9: {
                    try {
                        JPP_ProyectoFinal.informesService.informeEquiposPorSede();
                    } catch (SeHaProducidoUnError ex) {
                        Logger.getLogger(SubMenuMultitabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                default:
                    System.out.println("No esta en el menu");
            }
        }
    }

    // Metodos
    
    private static int leerEntero() {
        while (true) {
            try {
                String linea = scanner.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("  " + "Introduce un entero" + " > ");
            }
        }
    }

}
