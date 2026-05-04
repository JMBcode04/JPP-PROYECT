/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author jorge
 */
public class Constantes {

    // CONSTANTES BASE DE DATOS
    public static final String URL = "jdbc:mysql://localhost:3306/campeonato_deporte";
    public static final String USER = "root";
    public static final String PASSWORD = "Casita11";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // CONSATNTES FICHEROS
    // Es dodne se guardaran los ficheros exportados
    public static final String RUTA_FICHEROS = "ficheros/";

    // Extension TXT
    public static final String EXT_TXT = ".txt";

    // Extension CSV
    public static final String EXT_CSV = ".csv";

    // Extension BIN
    public static final String EXT_BIN = ".bin";

    // Extension JSON
    public static final String EXT_JSON = ".json";

    // Separador de campos del fichero TXT
    public static final String SEPARADOR_TXT = ";";

    // Separador de campos del fichero CSV
    public static final String SEPARADOR_CSV = ":";

    // CONSTANTES TABLAS
    // Nombre del fichero para la tabla equipo
    public static final String FICHERO_EQUIPO = "equipo";

    // Nombre del fichero para la tabla jugador
    public static final String FICHERO_JUGADOR = "jugador";
    
    // Nombre del fichero para la tabla jugador_equipo
    public static final String FICHERO_JUGADOR_EQUIPO = "jugador_equipo";
    
    // Nombre del fichero para la tabla partido
    public static final String FICHERO_PARTIDO = "partido"; 

}
