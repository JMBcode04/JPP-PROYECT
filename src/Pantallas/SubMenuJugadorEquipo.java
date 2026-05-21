/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Jugador_equipo;
import Utils.Constantes;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR PAMELA Y JORGE
 * @author jorge
 */
public class SubMenuJugadorEquipo {

    private static Scanner scanner = new Scanner(System.in);

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
                    accionInsertarJugadorEquipo();
                    break;
                case 2:
                    accionActualizarJugadorEquipo();
                    break;
                case 3:
                    accionEliminarJugadorEquipo();
                    break;
                case 4:
                    accionConsultarJugadorEquipo();
                    break;
                case 5:
                    accionConsultarTodosJugadorEquipo();
                    break;
                case 6: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.exportarTXT();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 7: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.exportarCSv();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 8: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.exportarBinario();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 9: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.exportarJson();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 10: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.importarTxt();
                        System.out.println("Importado Correctamente");
                    } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 11: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.importarCsv();
                        System.out.println("Importado Correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 12: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.importarBinario();
                        System.out.println("Importado Correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 13: {
                    try {
                        JPP_ProyectoFinal.jugadorEquipoService.importarJson();
                        System.out.println("Importado Correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 14:
                    JPP_ProyectoFinal.jugadorEquipoService.verDatosInsertadosSesion();
                    break;

                default:
                    System.out.println("No esta en el menu");
            }
        }
    }

    // Metodos
    private static void accionInsertarJugadorEquipo() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("INSERTAR RELACIÓN JUGADOR-EQUIPO");
            System.out.print("Código equipo: ");
            int codigoEquipo = leerEntero();
            System.out.print("Código jugador: ");
            int codigoJugador = leerEntero();
            System.out.print("Año de entrada: ");
            int añoEntrada = leerEntero();
            System.out.print("Año de salida (0 si sigue activo): ");
            int añoSalidaInput = leerEntero();
            Integer añoSalida = añoSalidaInput == 0 ? null : añoSalidaInput;
            System.out.print("Partidos como titular (0 si no aplica): ");
            int partidosInput = leerEntero();
            Integer partidosTitular = partidosInput == 0 ? null : partidosInput;

            Jugador_equipo je = new Jugador_equipo(codigoEquipo, codigoJugador, añoEntrada, añoSalida, partidosTitular);
            JPP_ProyectoFinal.jugadorEquipoService.insertar(je);
            System.out.println("Insertado Correctamente");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionActualizarJugadorEquipo() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("ACTUALIZAR RELACIÓN JUGADOR-EQUIPO");
            System.out.print("Código equipo: ");
            int codigoEquipo = leerEntero();
            System.out.print("Código jugador: ");
            int codigoJugador = leerEntero();
            System.out.print("Año de entrada (clave): ");
            int añoEntrada = leerEntero();
            System.out.print("Nuevo año de salida (0 si sigue activo): ");
            int añoSalidaInput = leerEntero();
            Integer añoSalida = añoSalidaInput == 0 ? null : añoSalidaInput;
            System.out.print("Nuevo número de partidos como titular: ");
            int partidosInput = leerEntero();
            Integer partidosTitular = partidosInput == 0 ? null : partidosInput;

            Jugador_equipo je = new Jugador_equipo(codigoEquipo, codigoJugador, añoEntrada, añoSalida, partidosTitular);
            JPP_ProyectoFinal.jugadorEquipoService.actualizar(je);
            System.out.println("Actulaizacion Completada con exito");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionEliminarJugadorEquipo() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("ELIMINAR RELACIÓN JUGADOR-EQUIPO ");
            System.out.print("Código equipo: ");
            int codigoEquipo = leerEntero();
            System.out.print("Código jugador: ");
            int codigoJugador = leerEntero();
            System.out.print("Año de entrada: ");
            int añoEntrada = leerEntero();
            JPP_ProyectoFinal.jugadorEquipoService.eliminar(codigoJugador);
            System.out.println("Eliminado Correctamente");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarJugadorEquipo() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("CONSULTAR RELACIÓN JUGADOR-EQUIPO");
            System.out.print("Código equipo: ");
            int codigoEquipo = leerEntero();
            System.out.print("Código jugador: ");
            int codigoJugador = leerEntero();
            System.out.print("Año de entrada: ");
            int añoEntrada = leerEntero();
            Jugador_equipo je = JPP_ProyectoFinal.jugadorEquipoService.consultar(codigoJugador);
            System.out.println("  Código equipo:    " + je.getCodigoEquipo());
            System.out.println("  Código jugador:   " + je.getCodigoJugador());
            System.out.println("  Año entrada:      " + je.getAñoEntrada());
            System.out.println("  Año salida:       " + (je.getAñoSalida() != null ? je.getAñoSalida() : "Activo"));
            System.out.println("  Partidos titular: " + (je.getPartidosTitular() != null ? je.getPartidosTitular() : "-"));
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarTodosJugadorEquipo() {
        try {
            System.out.println("TODAS LAS RELACIONES JUGADOR-EQUIPO");
            var lista = JPP_ProyectoFinal.jugadorEquipoService.consultarTodos();
            if (lista.isEmpty()) {
                System.out.println("  No hay relaciones registradas.");
            } else {
                System.out.printf("  %-10s %-10s %-10s %-10s %-12s%n",
                        "CÓD.EQUIP", "CÓD.JUG", "AÑO ENT.", "AÑO SAL.", "PARTIDOS");
                System.out.println("  " + "-".repeat(60));
                for (Jugador_equipo je : lista) {
                    System.out.printf("  %-10d %-10d %-10d %-10s %-12s%n",
                            je.getCodigoEquipo(), je.getCodigoJugador(), je.getAñoEntrada(),
                            je.getAñoSalida() != null ? je.getAñoSalida() : "Activo",
                            je.getPartidosTitular() != null ? je.getPartidosTitular() : "-");
                }
            }
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                String linea = scanner.nextLine().trim();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Introduce un numero entero");
            }
        }
    }

}
