/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Jugador;
import Utils.Constantes;
import Utils.Validadores;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR PAMELA Y JORGE
 * @author jorge
 */
public class SubMenuJugadores {

    private static Scanner scanner = new Scanner(System.in);

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
                    accionInsertarJugador();
                    break;
                case 2:
                    accionActualizarJugador();
                    break;
                case 3:
                    accionEliminarJugador();
                    break;
                case 4:
                    accionConsultarJugador();
                    break;
                case 5:
                    accionConsultarTodosJugadores();
                    break;
                case 6: {
                    try {
                        JPP_ProyectoFinal.jugadorService.exportarTxt();
                        System.out.println("Exportado correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 7: {
                    try {
                        JPP_ProyectoFinal.jugadorService.exportarCsv();
                        System.out.println("Exportado correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 8: {
                    try {
                        JPP_ProyectoFinal.jugadorService.exportarBinario();
                        System.out.println("Exportado correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;

                case 9: {
                    try {
                        JPP_ProyectoFinal.jugadorService.exportarJson();
                        System.out.println("Exportado correctamente");
                    } catch (SeHaProducidoUnError e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;

                case 10: {
                    try {
                        JPP_ProyectoFinal.jugadorService.importarTxt();
                        System.out.println("Importado correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;

                case 11: {
                    try {
                        JPP_ProyectoFinal.jugadorService.importarCsv();
                        System.out.println("Importado correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 12: {
                    try {
                        JPP_ProyectoFinal.jugadorService.importarBinario();
                        System.out.println("Importado correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SubMenuJugadores.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 13: {
                    try {
                        JPP_ProyectoFinal.jugadorService.importarJson();
                        System.out.println("Importado correctamente");
                    }  catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
                case 14:
                    JPP_ProyectoFinal.jugadorService.verDatosInsertadosSesion();
                    break;

                default:
                    System.out.println("No esta en el menu");
            }
        }
    }

    // Metodos
    private static void accionInsertarJugador() {

        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("INSERTAR JUGADOR");
            System.out.print("Código: ");
            int codigo = leerEntero();
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine().trim();
            while (!Validadores.validarNombre(nombre)) {
                System.out.print("Introduce el nombre correctamente: ");
                nombre = teclado.nextLine().trim();
            }

            System.out.print("Fecha de nacimiento (dd/mm/aaaa): ");
            String fechaNacimiento = teclado.nextLine().trim(); //Elimina espacios en blanco al principio y final

            System.out.print("Nacionalidad: ");
            String nacionalidad = teclado.nextLine().trim();
            while (!Validadores.validarNacionalidad(nacionalidad)) {
                System.out.print("Introduce la nacionalidad correctamente: ");
                nacionalidad = teclado.nextLine().trim();
            }

            System.out.print("Posición: ");
            String posicion = teclado.nextLine().trim();
            while (!Validadores.validarPosicion(posicion)) {
                System.out.print("Introduce la posicion correctamente: ");
                posicion = teclado.nextLine().trim();
            }

            Jugador jugador = new Jugador(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
            JPP_ProyectoFinal.jugadorService.insertar(jugador);
            System.out.println("Insertado Correctamente");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionActualizarJugador() {
        Scanner teclado = new Scanner(System.in);
        try {
            System.out.println("ACTUALIZAR JUGADOR");
            System.out.print("Código del jugador a actualizar: ");
            int codigo = leerEntero();
            System.out.print("Nuevo nombre: ");
            String nombre = teclado.nextLine().trim();
            while (Validadores.validarNombre(nombre)) {
                nombre = teclado.nextLine().trim();
            }
            System.out.print("Nueva fecha de nacimiento (dd/mm/aaaa): ");
            String fechaNacimiento = teclado.nextLine().trim();
            System.out.print("Nueva nacionalidad: ");
            String nacionalidad = teclado.nextLine().trim();
            while (Validadores.validarNacionalidad(nacionalidad)) {
                nacionalidad = teclado.nextLine().trim();
            }
            System.out.print("Nueva posición: ");
            String posicion = teclado.nextLine().trim();
            while (Validadores.validarPosicion(posicion)) {
                posicion = teclado.nextLine().trim();
            }

            Jugador jugador = new Jugador(codigo, nombre, fechaNacimiento, nacionalidad, posicion);
            JPP_ProyectoFinal.jugadorService.actualizar(jugador);
            System.out.println("Actualizado correctamente");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionEliminarJugador() {
        try {
            System.out.println("ELIMINAR JUGADOR");
            System.out.print("Código del jugador a eliminar: ");
            int codigo = leerEntero();
            JPP_ProyectoFinal.jugadorService.eliminar(codigo);
            System.out.println("Eliminado con Exito");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarJugador() {
        try {
            System.out.println("CONSULTAR JUGADOR");
            System.out.print("Código del jugador: ");
            int codigo = leerEntero();
            Jugador jugador = JPP_ProyectoFinal.jugadorService.consultar(codigo);
            System.out.println("  Código:          " + jugador.getCodigo());
            System.out.println("  Nombre:          " + jugador.getNombre());
            System.out.println("  F. Nacimiento:   " + jugador.getFechaNacimiento());
            System.out.println("  Nacionalidad:    " + jugador.getNacionalidad());
            System.out.println("  Posición:        " + jugador.getPosicion());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarTodosJugadores() {
        try {
            System.out.println("TODOS LOS JUGADORES (orden ascendente por nombre)");
            var lista = JPP_ProyectoFinal.jugadorService.consultarTodos();
            if (lista.isEmpty()) {
                System.out.println("  No hay jugadores registrados.");
            } else {
                System.out.printf("  %-6s %-25s %-15s %-15s %-15s%n",
                        "COD", "NOMBRE", "F.NACIMIENTO", "NACIONALIDAD", "POSICIÓN");
                System.out.println("  " + "-".repeat(80));
                for (Jugador j : lista) {
                    System.out.printf("  %-6d %-25s %-15s %-15s %-15s%n",
                            j.getCodigo(), j.getNombre(), j.getFechaNacimiento(),
                            j.getNacionalidad(), j.getPosicion());
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
                if (linea.isEmpty()) {
                    System.out.print("Error: el campo no puede estar vacio. Introduce un numero entero: ");
                    continue;
                }
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Introduce un numero entero");
            }
        }
    }

    private static String leerTextoNoVacio(Scanner teclado, String campo) {
        while (true) {
            String valor = teclado.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.print("Error: el campo '" + campo + "' no puede estar vacio. Intentalo de nuevo: ");
        }
    }

    private static String leerFecha(Scanner teclado) {
        while (true) {
            String fecha = teclado.nextLine().trim();
            if (fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return fecha;
            }
            System.out.print("Error: la fecha debe tener el formato dd/mm/aaaa (ej: 15/06/1995). Intentalo de nuevo: ");
        }
    }

}
