/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Partido;
import Utils.Constantes;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class SubMenuPartidos {

    private static Scanner scanner = new Scanner(System.in);

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
                    accionInsertarPartido();
                    break;
                case 2:
                    accionActualizarPartido();
                    break;
                case 3:
                    accionEliminarPartido();
                    break;
                case 4:
                    accionConsultarPartido();
                    break;
                case 5:
                    accionConsultarTodosPartidos();
                    break;
                case 6: {
                    try {
                        JPP_ProyectoFinal.partidoService.exportarTxt();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 7: {
                    try {
                        JPP_ProyectoFinal.partidoService.exportarCsv();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 8: {
                    try {
                        JPP_ProyectoFinal.partidoService.exportarBinario();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 9: {
                    try {
                        JPP_ProyectoFinal.partidoService.exportarJson();
                        System.out.println("Exportado Correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 10: {
                    try {
                        JPP_ProyectoFinal.partidoService.importarTxt();
                        System.out.println("Importado Correctamente");
                    } catch (YaImportadoException e) {
                        System.out.println(e.getMessage());
                    } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 11: {
                    try {
                        JPP_ProyectoFinal.partidoService.importarCsv();
                        System.out.println("Importado Correctamente");
                    } catch (YaImportadoException e) {
                        System.out.println(e.getMessage());
                    } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 12: {
                    try {
                        JPP_ProyectoFinal.partidoService.importarBinario();
                        System.out.println("Importado Correctamente");
                    } catch (YaImportadoException e) {
                        System.out.println(e.getMessage());
                    } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SubMenuPartidos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case 13: {
                    try {
                        JPP_ProyectoFinal.partidoService.importarJson();
                        System.out.println("Importado Correctamente");
                    } catch (YaImportadoException e) {
                        System.out.println(e.getMessage());
                    } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 14:
                    JPP_ProyectoFinal.partidoService.verDatosInsertadosSesion();
                    break;

                default:
                    System.out.println("No esta en el menu");
            }
        }
    }

    private static void accionInsertarPartido() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("INSERTAR PARTIDO");
            System.out.print("Código equipo local: ");
            int codigoLocal = leerEntero();
            System.out.print("Código equipo visitante: ");
            int codigoVisitante = leerEntero();
            System.out.print("Año de temporada: ");
            int añoTemporada = leerEntero();
            System.out.print("Fecha (dd/mm/aaaa): ");
            String fecha = leerFecha(teclado);
            System.out.print("Puntuación local (0 si no hay): ");
            int puntuacionLocalInput = leerEntero();
            Integer puntuacionLocal = puntuacionLocalInput == 0 ? null : puntuacionLocalInput;
            System.out.print("Puntuación visitante (0 si no hay): ");
            int puntuacionVisitanteInput = leerEntero();
            Integer puntuacionVisitante = puntuacionVisitanteInput == 0 ? null : puntuacionVisitanteInput;

            Partido partido = new Partido(codigoLocal, codigoVisitante, añoTemporada, fecha, puntuacionLocal, puntuacionVisitante);
            JPP_ProyectoFinal.partidoService.insertar(partido);
            System.out.println("Insertado Correctamente");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionActualizarPartido() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("ACTUALIZAR PARTIDO");
            System.out.print("Código equipo local (clave): ");
            int codigoLocal = leerEntero();
            System.out.print("Código equipo visitante (clave): ");
            int codigoVisitante = leerEntero();
            System.out.print("Año de temporada (clave): ");
            int añoTemporada = leerEntero();
            System.out.print("Nueva fecha (dd/mm/aaaa): ");
            String fecha = leerFecha(teclado);
            System.out.print("Nueva puntuación local: ");
            int puntuacionLocalInput = leerEntero();
            Integer puntuacionLocal = puntuacionLocalInput == 0 ? null : puntuacionLocalInput;
            System.out.print("Nueva puntuación visitante: ");
            int puntuacionVisitanteInput = leerEntero();
            Integer puntuacionVisitante = puntuacionVisitanteInput == 0 ? null : puntuacionVisitanteInput;

            Partido partido = new Partido(codigoLocal, codigoVisitante, añoTemporada, fecha, puntuacionLocal, puntuacionVisitante);
            JPP_ProyectoFinal.partidoService.actualizar(partido);
            System.out.println("Actulaizado Corrrectamente");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionEliminarPartido() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("ELIMINAR PARTIDO");
            System.out.print("Código equipo local: ");
            int codigoLocal = leerEntero();
            System.out.print("Código equipo visitante: ");
            int codigoVisitante = leerEntero();
            System.out.print("Año de temporada: ");
            int añoTemporada = leerEntero();
            JPP_ProyectoFinal.partidoService.eliminar(codigoLocal, codigoVisitante, añoTemporada);
            System.out.println("Eliminado con exito");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarPartido() {

        try {
            System.out.println("CONSULTAR PARTIDO");
            System.out.print("Código equipo local: ");
            int codigoLocal = leerEntero();
            System.out.print("Código equipo visitante: ");
            int codigoVisitante = leerEntero();
            System.out.print("Año de temporada: ");
            int añoTemporada = leerEntero();
            Partido partido = JPP_ProyectoFinal.partidoService.consultar(codigoLocal, codigoVisitante, añoTemporada);
            System.out.println("  Equipo local:      " + partido.getCodigoEquipoLocal());
            System.out.println("  Equipo visitante:  " + partido.getCodigoEquipoVisitante());
            System.out.println("  Temporada:         " + partido.getAñoTemporada());
            System.out.println("  Fecha:             " + partido.getFecha());
            System.out.println("  Puntuación local:  " + (partido.getPuntuacionLocal() != null ? partido.getPuntuacionLocal() : "-"));
            System.out.println("  Punt. visitante:   " + (partido.getPuntuacionVisitante() != null ? partido.getPuntuacionVisitante() : "-"));
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarTodosPartidos() {

        try {
            System.out.println("TODOS LOS PARTIDOS (orden ascendente por fecha)");
            var lista = JPP_ProyectoFinal.partidoService.consultarTodos();
            if (lista.isEmpty()) {
                System.out.println("  No hay partidos registrados.");
            } else {
                System.out.printf("  %-10s %-12s %-10s %-15s %-10s %-10s%n",
                        "LOCAL", "VISITANTE", "TEMPORADA", "FECHA", "PTS LOCAL", "PTS VISIT");
                System.out.println("  " + "-".repeat(75));
                for (Partido p : lista) {
                    System.out.printf("  %-10d %-12d %-10d %-15s %-10s %-10s%n",
                            p.getCodigoEquipoLocal(), p.getCodigoEquipoVisitante(),
                            p.getAñoTemporada(), p.getFecha(),
                            p.getPuntuacionLocal() != null ? p.getPuntuacionLocal() : "-",
                            p.getPuntuacionVisitante() != null ? p.getPuntuacionVisitante() : "-");
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

    private static String leerFecha(Scanner teclado) {
        while (true) {
            String fecha = teclado.nextLine().trim();
            if (fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return fecha;
            }
            System.out.print("Error: la fecha debe tener el formato dd/mm/aaaa. Intentalo de nuevo: ");
        }
    }

}
