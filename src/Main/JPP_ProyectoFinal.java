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
import Pantallas.MenuPrincipal;
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
    public static EquipoService equipoService = new EquipoService(contenedorEquipos);
    public static JugadorService jugadorService = new JugadorService(contenedorJugador);
    public static JugadorEquipoService jugadorEquipoService = new JugadorEquipoService();
    public static PartidoService partidoService = new PartidoService(contenedorPartidos);
    public static InformesService informesService = new InformesService();

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
        MenuPrincipal.menuPrincipal();
    }
}