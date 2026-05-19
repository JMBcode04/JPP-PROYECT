/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.SeHaProducidoUnError;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class InformesService {

    private static final String RUTA_INFORMES = "informes/";

    // 1.Primera consulta Multitabla
    public void informeJugadoresActualesPorEquipo() throws SeHaProducidoUnError {
        String sql = "SELECT e.nombre AS equipo, e.estadio, "
                + "CONCAT(e.localidad, ', ', e.provincia) AS lugar_sede, "
                + "j.nombre AS jugador, j.posicion "
                + "FROM equipo e "
                + "JOIN jugador_equipo je ON e.codigo = je.codigo_equipo "
                + "JOIN jugador j ON je.codigo_jugador = j.codigo "
                + "WHERE je.año_salida IS NULL "
                + "ORDER BY e.nombre ASC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 1: JUGADORES ACTUALES POR EQUIPO");
        lineas.add(String.format("%-25s %-25s %-20s %-25s %-15s",
                "EQUIPO", "ESTADIO", "LUGAR SEDE", "JUGADOR", "POSICIÓN"));
        lineas.add("-".repeat(110));
        ejecutarYGuardar(sql, lineas, "informe1_jugadoresActuales.txt",
                rs -> String.format("%-25s %-25s %-20s %-25s %-15s",
                        rs.getString("equipo"),
                        rs.getString("estadio"),
                        rs.getString("lugar_sede"),
                        rs.getString("jugador"),
                        rs.getString("posicion")));
    }

    // 2.Segunda consulta Multitabla
    public void informePartidosConEquipos() throws SeHaProducidoUnError {
        String sql = "SELECT p.fecha, el.estadio, el.nombre AS local, ev.nombre AS visitante, "
                + "p.puntuacion_local, p.puntuacion_visitante "
                + "FROM partido p "
                + "JOIN equipo el ON p.codigo_equipo_local = el.codigo "
                + "JOIN equipo ev ON p.codigo_equipo_visitante = ev.codigo "
                + "ORDER BY p.fecha ASC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 2: PARTIDOS CON EQUIPOS");
        lineas.add(String.format("%-15s %-25s %-20s %-20s %10s %10s",
                "FECHA", "ESTADIO", "LOCAL", "VISITANTE", "PTS LOCAL", "PTS VISIT"));
        lineas.add("-".repeat(105));
        ejecutarYGuardar(sql, lineas, "informe2_partidos.txt",
                rs -> String.format("%-15s %-25s %-20s %-20s %10s %10s",
                        rs.getString("fecha"),
                        rs.getString("estadio"),
                        rs.getString("local"),
                        rs.getString("visitante"),
                        rs.getObject("puntuacion_local") != null ? rs.getInt("puntuacion_local") : "-",
                        rs.getObject("puntuacion_visitante") != null ? rs.getInt("puntuacion_visitante") : "-"));
    }

    // 3. Tercera consulta Multitabla
    public void informeHistorialJugador() throws SeHaProducidoUnError {
        String sql = "SELECT j.nombre, j.nacionalidad, j.fecha_nacimiento, "
                + "e.nombre AS equipo, je.año_entrada, je.año_salida, je.partidos_titular "
                + "FROM jugador j "
                + "JOIN jugador_equipo je ON j.codigo = je.codigo_jugador "
                + "JOIN equipo e ON je.codigo_equipo = e.codigo "
                + "ORDER BY je.año_entrada DESC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 3: HISTORIAL DE JUGADORES POR EQUIPOS");
        lineas.add(String.format("%-20s %-15s %-15s %-20s %8s %8s %10s",
                "JUGADOR", "NACIONALIDAD", "F.NACIMIENTO", "EQUIPO", "ENTRADA", "SALIDA", "TITULARES"));
        lineas.add("-".repeat(100));
        ejecutarYGuardar(sql, lineas, "informe3_historialJugadores.txt",
                rs -> String.format("%-20s %-15s %-15s %-20s %8s %8s %10s",
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("equipo"),
                        rs.getInt("año_entrada"),
                        rs.getObject("año_salida") != null ? rs.getInt("año_salida") : "-",
                        rs.getObject("partidos_titular") != null ? rs.getInt("partidos_titular") : "-"));
    }

    // 4. Cuarta consulta Multitabla
    public void informePuntosLocalPorTemporada(int añoTemporada) throws SeHaProducidoUnError {
        String sql = "SELECT e.nombre, e.estadio, "
                + "SUM(p.puntuacion_local) AS total_puntos, COUNT(*) AS partidos_casa "
                + "FROM equipo e "
                + "JOIN partido p ON e.codigo = p.codigo_equipo_local "
                + "WHERE p.año_temporada = ? "
                + "GROUP BY e.codigo, e.nombre, e.estadio "
                + "ORDER BY total_puntos DESC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 4: PUNTOS LOCAL EN TEMPORADA " + añoTemporada);
        lineas.add(String.format("%-25s %-25s %15s %15s",
                "EQUIPO", "ESTADIO", "TOTAL PUNTOS", "PARTIDOS CASA"));
        lineas.add("-".repeat(85));

        crearDirectorioInformes();
        String ruta = RUTA_INFORMES + "informe4_puntosLocal_" + añoTemporada + ".txt";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql); BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            ps.setInt(1, añoTemporada);
            try (ResultSet rs = ps.executeQuery()) {
                for (String linea : lineas) {
                    writer.write(linea);
                    writer.newLine();
                }
                while (rs.next()) {
                    String fila = String.format("%-25s %-25s %15s %15s",
                            rs.getString("nombre"),
                            rs.getString("estadio"),
                            rs.getObject("total_puntos") != null ? rs.getInt("total_puntos") : 0,
                            rs.getInt("partidos_casa"));
                    writer.write(fila);
                    writer.newLine();
                    System.out.println(fila);
                }
            }
            System.out.println("\nInforme guardado en: " + ruta);
        } catch (SQLException | IOException e) {
            throw new SeHaProducidoUnError("Error en informe 4: " + e.getMessage());
        }
    }

    // 5. Quinta consulta Multitabla
    public void informeResumenCarreraJugadores() throws SeHaProducidoUnError {
        String sql = "SELECT j.nombre, j.posicion, "
                + "COUNT(DISTINCT je.codigo_equipo) AS total_equipos, "
                + "SUM(IFNULL(je.partidos_titular, 0)) AS total_partidos "
                + "FROM jugador j "
                + "JOIN jugador_equipo je ON j.codigo = je.codigo_jugador "
                + "GROUP BY j.codigo, j.nombre, j.posicion "
                + "ORDER BY total_partidos DESC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 5: RESUMEN CARRERA DE JUGADORES");
        lineas.add(String.format("%-25s %-15s %15s %15s",
                "JUGADOR", "POSICIÓN", "TOTAL EQUIPOS", "TOTAL PARTIDOS"));
        lineas.add("-".repeat(75));
        ejecutarYGuardar(sql, lineas, "informe5_resumenCarrera.txt",
                rs -> String.format("%-25s %-15s %15s %15s",
                        rs.getString("nombre"),
                        rs.getString("posicion"),
                        rs.getInt("total_equipos"),
                        rs.getInt("total_partidos")));
    }

    // 6. Sexta consulta Multitabla
    public void informeEquiposConJugadoresFieles() throws SeHaProducidoUnError {
        String sql = "SELECT e.nombre, e.año_fundacion, "
                + "COUNT(je.codigo_jugador) AS jugadores_mas_5_años "
                + "FROM equipo e "
                + "JOIN jugador_equipo je ON e.codigo = je.codigo_equipo "
                + "WHERE (IFNULL(je.año_salida, YEAR(NOW())) - je.año_entrada) > 5 "
                + "GROUP BY e.codigo, e.nombre, e.año_fundacion "
                + "HAVING jugadores_mas_5_años > 3 "
                + "ORDER BY e.nombre ASC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 6: EQUIPOS CON MÁS DE 3 JUGADORES FIELES (>5 AÑOS)");
        lineas.add(String.format("%-25s %15s %25s",
                "EQUIPO", "AÑO FUNDACIÓN", "JUGADORES > 5 AÑOS"));
        lineas.add("-".repeat(70));
        ejecutarYGuardar(sql, lineas, "informe6_jugadoresFieles.txt",
                rs -> String.format("%-25s %15s %25s",
                        rs.getString("nombre"),
                        rs.getInt("año_fundacion"),
                        rs.getInt("jugadores_mas_5_años")));
    }

    // 7. Septima consulta Multitabla
    public void informeJugadoresLibres() throws SeHaProducidoUnError {
        String sql = "SELECT j.nombre, j.nacionalidad "
                + "FROM jugador j "
                + "LEFT JOIN jugador_equipo je ON j.codigo = je.codigo_jugador "
                + "WHERE je.codigo_jugador IS NULL "
                + "ORDER BY j.nombre ASC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 7: JUGADORES LIBRES (SIN EQUIPO)");
        lineas.add(String.format("%-25s %-20s", "JUGADOR", "NACIONALIDAD"));
        lineas.add("-".repeat(50));
        ejecutarYGuardar(sql, lineas, "informe7_jugadoresLibres.txt",
                rs -> String.format("%-25s %-20s",
                        rs.getString("nombre"),
                        rs.getString("nacionalidad")));
    }

    // 8. Octava consulta Multitabla
    public void informePartidosAltaPuntuacion(int valorMinimo) throws SeHaProducidoUnError {
        String sql = "SELECT p.fecha, p.año_temporada, el.nombre AS local, ev.nombre AS visitante, "
                + "(p.puntuacion_local + p.puntuacion_visitante) AS suma_puntos "
                + "FROM partido p "
                + "JOIN equipo el ON p.codigo_equipo_local = el.codigo "
                + "JOIN equipo ev ON p.codigo_equipo_visitante = ev.codigo "
                + "WHERE (p.puntuacion_local + p.puntuacion_visitante) > ? "
                + "ORDER BY suma_puntos DESC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 8: PARTIDOS CON PUNTUACIÓN TOTAL > " + valorMinimo);
        lineas.add(String.format("%-15s %8s %-20s %-20s %12s",
                "FECHA", "TEMPORADA", "LOCAL", "VISITANTE", "SUMA PUNTOS"));
        lineas.add("-".repeat(80));

        crearDirectorioInformes();
        String ruta = RUTA_INFORMES + "informe8_altaPuntuacion_" + valorMinimo + ".txt";
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql); BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            ps.setInt(1, valorMinimo);
            try (ResultSet rs = ps.executeQuery()) {
                for (String linea : lineas) {
                    writer.write(linea);
                    writer.newLine();
                }
                while (rs.next()) {
                    String fila = String.format("%-15s %8s %-20s %-20s %12s",
                            rs.getString("fecha"),
                            rs.getInt("año_temporada"),
                            rs.getString("local"),
                            rs.getString("visitante"),
                            rs.getInt("suma_puntos"));
                    writer.write(fila);
                    writer.newLine();
                    System.out.println(fila);
                }
            }
            System.out.println("\nInforme guardado en: " + ruta);
        } catch (SQLException | IOException e) {
            throw new SeHaProducidoUnError("Error en informe 8: " + e.getMessage());
        }
    }

    // 9. Novena consulta Multitabla
    public void informeEquiposPorSede() throws SeHaProducidoUnError {
        String sql = "SELECT CONCAT(e1.localidad, ', ', e1.provincia) AS lugar_sede, "
                + "GROUP_CONCAT(e1.nombre ORDER BY e1.nombre SEPARATOR ', ') AS equipos, "
                + "SUM(e1.socios_aficionados) AS total_socios "
                + "FROM equipo e1 "
                + "WHERE (e1.localidad, e1.provincia) IN ( "
                + "  SELECT localidad, provincia FROM equipo "
                + "  GROUP BY localidad, provincia HAVING COUNT(*) > 1 "
                + ") "
                + "GROUP BY e1.localidad, e1.provincia "
                + "ORDER BY e1.localidad ASC, e1.provincia ASC";
        List<String> lineas = new ArrayList<>();
        lineas.add("INFORME 9: EQUIPOS QUE COMPARTEN SEDE");
        lineas.add(String.format("%-25s %-40s %15s",
                "LUGAR SEDE", "EQUIPOS", "TOTAL SOCIOS"));
        lineas.add("-".repeat(85));
        ejecutarYGuardar(sql, lineas, "informe9_equiposPorSede.txt",
                rs -> String.format("%-25s %-40s %15s",
                        rs.getString("lugar_sede"),
                        rs.getString("equipos"),
                        rs.getLong("total_socios")));
    }

    private void ejecutarYGuardar(String sql, List<String> lineasCabecera,
            String nombreFichero, MapaFila mapeador) throws SeHaProducidoUnError {
        crearDirectorioInformes();
        String ruta = RUTA_INFORMES + nombreFichero;
        try (Connection con = MetodosBaseDeDatos.AccederBaseDeDatos(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery(); BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : lineasCabecera) {
                writer.write(linea);
                writer.newLine();
                System.out.println(linea);
            }
            while (rs.next()) {
                String fila = mapeador.mapear(rs);
                writer.write(fila);
                writer.newLine();
                System.out.println(fila);
            }
            System.out.println("\nInforme guardado en: " + ruta);
        } catch (SQLException | IOException e) {
            throw new SeHaProducidoUnError("Error al generar informe '" + nombreFichero + "': " + e.getMessage());
        }
    }

    // Crea el directorio de informes si no existe
    private void crearDirectorioInformes() {
        java.io.File dir = new java.io.File(RUTA_INFORMES);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    // Creamos una interfaz funcional para poder mapear una fila del ResultSet a una cadena de texto
    @FunctionalInterface
    private interface MapaFila {

        String mapear(ResultSet rs) throws SQLException;
    }

}
