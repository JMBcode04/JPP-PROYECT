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
    // CONTENEDORES en memoria (guardan los datos insertados durante la sesion)
    private static ContenedorEquipos contenedorEquipos = new ContenedorEquipos();
    private static ContenedorJugador contenedorJugador = new ContenedorJugador();
    private static ContenedorPartidos contenedorPartidos = new ContenedorPartidos();

    //SERVICIOS: gestionan CRUD y ficheros para cada entidad
    public static EquipoService equipoService = new EquipoService(contenedorEquipos);
    public static JugadorService jugadorService = new JugadorService(contenedorJugador);
    public static JugadorEquipoService jugadorEquipoService = new JugadorEquipoService();
    public static PartidoService partidoService = new PartidoService(contenedorPartidos);
    public static InformesService informesService = new InformesService();

    // SACNNER 
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sincroniza el contador de codigos de Jugador con el ultimo valor en la BD
        // para que los nuevos jugadores no colisionen con los ya existentes
        try {
            int ultimoCodigo = JugadorService.obtenerUltimoCodigo();
            Jugador.inicializarContador(ultimoCodigo);
        } catch (SeHaProducidoUnError e) {
            System.out.println("Error al inicializar: " + e.getMessage());
        }
        // Lanza el menu principal por consola
        MenuPrincipal.menuPrincipal();
    }
}
