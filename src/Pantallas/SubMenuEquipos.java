/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pantallas;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import static Main.JPP_ProyectoFinal.equipoService;
import Modelos.Equipo;
import Servicios.EquipoService;
import Utils.Constantes;
import Utils.Validadores;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class SubMenuEquipos {

    private static Scanner scanner = new Scanner(System.in);

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
                    accionInsertarEquipo();
                    break;
                case 2:
                    accionActualizarEquipo();
                    break;
                case 3:
                    accionEliminarEquipo();
                    break;
                case 4:
                    accionConsultarEquipo();
                    break;
                case 5:
                    accionConsultarTodosEquipos();
                    break;
                case 6:
                    accionExportarEquipoTxt();
                    break;
                case 7:
                    accionExportarEquipoCsv();
                    break;
                case 8:
                    accionImportarEquipoBinario();
                    break;

                case 9:
                    accionExportarEquipoJson();
                    break;

                case 10:
                    accionImportarEquipoTxt();
                    break;

                case 11:
                    accionImportarEquipoCsv();
                    break;
                case 12:
                    accionImportarEquipoBinario();
                    break;

                case 13:
                    accionImportarEquipoJson();
                    break;
                case 14:

                    break;

                default:
                    System.out.println("No esta en el menu");
            }
        }

    }

    // Metodos
    private static void accionInsertarEquipo() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("INSERTAR EQUIPO");
            System.out.print("Código: ");
            int codigo = leerEntero("Codigo");
            
            System.out.print("Nombre: ");
            String nombre = teclado.nextLine().trim();
            while (!Validadores.validarNombre(nombre)) {
                nombre = teclado.nextLine().trim();
            }
            System.out.print("Año de fundación: ");
            int añoFundacion = teclado.nextInt();
            while (!Validadores.añoFundacion(añoFundacion)) {
                añoFundacion = teclado.nextInt();
            }
            System.out.print("Lugar de sede (localidad y provincia): ");
            String lugarSede = teclado.nextLine().trim();
            while (!Validadores.lugarSede(lugarSede)) {
                lugarSede = teclado.nextLine().trim();
            }
            System.out.print("Estadio: ");
            String estadio = teclado.nextLine().trim();
            while (!Validadores.estadio(estadio)) {
                estadio = teclado.nextLine().trim();
            }

            System.out.print("Número de socios aficionados: ");
            int sociosAficionados = teclado.nextInt();
            while (!Validadores.numeroSocios(sociosAficionados)) {
                sociosAficionados = teclado.nextInt();
            }

            Equipo equipo = new Equipo(codigo, nombre, añoFundacion, lugarSede, estadio, sociosAficionados);
            JPP_ProyectoFinal.equipoService.insertar(equipo);
            System.out.println("Introducido Correctamente");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionActualizarEquipo() {
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("ACTUALIZAR EQUIPO");
            System.out.print("Código del equipo a actualizar: ");
            int codigo = leerEntero("Codigo");
            System.out.print("Nuevo nombre: ");
            String nombre = teclado.nextLine().trim();
            while (Validadores.validarNombre(nombre)) {
                nombre = teclado.nextLine().trim();
            }
            System.out.print("Nuevo año de fundación: ");
            int añoFundacion = teclado.nextInt();
            while (Validadores.añoFundacion(añoFundacion)) {
                añoFundacion = teclado.nextInt();
            }
            System.out.print("Nuevo lugar de sede: ");
            String lugarSede = teclado.nextLine().trim();
            while (Validadores.lugarSede(lugarSede)) {
                lugarSede = teclado.nextLine().trim();
            }
            System.out.print("Nuevo estadio: ");
            String estadio = teclado.nextLine().trim();
            while (Validadores.estadio(estadio)) {
                estadio = teclado.nextLine().trim();
            }

            System.out.print("Nuevo número de socios: ");
            int sociosAficionados = teclado.nextInt();
            while (Validadores.numeroSocios(sociosAficionados)) {
                sociosAficionados = teclado.nextInt();
            }

            Equipo equipo = new Equipo(codigo, nombre, añoFundacion, lugarSede, estadio, sociosAficionados);
            JPP_ProyectoFinal.equipoService.actualizar(equipo);
            System.out.println("Actualizacion realizada");
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionEliminarEquipo() {
        try {
            System.out.println("ELIMINAR EQUIPO");
            System.out.print("Código del equipo a eliminar: ");
            int codigo = leerEntero("Codigo");
            JPP_ProyectoFinal.equipoService.eliminar(codigo);
            System.out.println("Eliminado con Exito");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarEquipo() {
        try {
            System.out.println("CONSULTAR EQUIPO");
            System.out.print("Código del equipo: ");
            int codigo = leerEntero("Codigo");
            Equipo equipo = equipoService.consultar(codigo);
            System.out.println("Código: " + equipo.getCodigo());
            System.out.println("Nombre: " + equipo.getNombre());
            System.out.println("Fundación: " + equipo.getañoFundacion());
            System.out.println("Sede: " + equipo.getLugarSede());
            System.out.println("Estadio: " + equipo.getEstadio());
            System.out.println("Socios: " + equipo.getSociosAficionados());
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionConsultarTodosEquipos() {
        try {
            System.out.println("TODOS LOS EQUIPOS (orden ascendente por nombre)");
            var lista = equipoService.consultarTodos();
            if (lista.isEmpty()) {
                System.out.println("  No hay equipos registrados.");
            } else {
                System.out.printf("  %-6s %-25s %-8s %-20s %-20s %-8s%n",
                        "COD", "NOMBRE", "FUNDAC.", "SEDE", "ESTADIO", "SOCIOS");
                System.out.println("  " + "-".repeat(95));
                for (Equipo e : lista) {
                    System.out.printf("  %-6d %-25s %-8d %-20s %-20s %-8d%n",
                            e.getCodigo(), e.getNombre(), e.getañoFundacion(),
                            e.getLugarSede(), e.getEstadio(), e.getSociosAficionados());
                }
            }
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void accionExportarEquipoTxt() {
        try {
            equipoService.exportarTxt();
            System.out.println("Equipos exportados a TXT. ");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    private static void accionExportarEquipoCsv() {
        try {
            equipoService.exportarCsv();
            System.out.println("Equipos exportados a CSV. ");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    private static void accionExportarEquipoBinario() {
        try {
            equipoService.exportarBinario();
            System.out.println("Equipos exportados a Binario. ");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    private static void accionExportarEquipoJson() {
        try {
            equipoService.exportarJson();
            System.out.println("Equipos exportados a JSON. ");
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    private static void accionImportarEquipoTxt() {
        try {
            equipoService.importarTxt();
            System.out.println("Equipos importados desde TXT. ");
        } catch (YaImportadoException e) {
            System.out.println(e.getMessage());
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    private static void accionImportarEquipoCsv() {
        try {
            equipoService.importarCsv();
            System.out.println("Equipos importados desde CSV. ");
        } catch (YaImportadoException e) {
            System.out.println(e.getMessage());
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    private static void accionImportarEquipoBinario() {
        try {
            equipoService.importarBinario();
            System.out.println("Equipos importados desde Binario.");
        } catch (YaImportadoException e) {
            System.out.println(e.getMessage());
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error al importar: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SubMenuEquipos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void accionImportarEquipoJson() {
        try {
            equipoService.importarJson();
            System.out.println("Equipos importados desde JSON.");
        } catch (YaImportadoException e) {
            System.out.println(e.getMessage());
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    private static int leerEntero(String campo) {
        while (true) {
            try {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) {
                    System.out.print("Error: el campo '" + campo + "' no puede estar vacio. Intentalo de nuevo: ");
                    continue;
                }
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Error: '" + campo + "' debe ser un numero entero. Intentalo de nuevo: ");
            }
        }
    }

    private static int leerEnteroNoNegativo(String campo) {
        while (true) {
            int valor = leerEntero(campo);
            if (valor >= 0) {
                return valor;
            }
            System.out.print("Error: '" + campo + "' no puede ser negativo. Intentalo de nuevo: ");
        }
    }

    private static String leerTextoNoVacio(String campo) {
        while (true) {
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.print("Error: el campo '" + campo + "' no puede estar vacio. Intentalo de nuevo: ");
        }
    }

}
