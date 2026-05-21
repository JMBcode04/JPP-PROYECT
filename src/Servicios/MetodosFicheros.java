/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;

import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
/**
 * REALIZADO POR PAMELA Y PAULA
 * @author jorge
 */
public class MetodosFicheros {

    // Escribe una lista de lineas en un fichero .txt, creando el directorio si no existe
    public static void exportarTxt(String nombreFichero, List<String> lineas) throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_TXT;
        crearDirectorioSiNoExiste();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al exportar fichero TXT: " + e.getMessage());
        }
    }

    // Lee todas las lineas de un fichero .txt y las devuelve como lista de Strings
    public static List<String> importarTxt(String nombreFichero)
            throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_TXT;
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al importar fichero TXT: " + e.getMessage());
        }
        return lineas;
    }

    // Escribe lineas en formato CSV (misma logica que TXT, distinta extension y separador)
    public static void exportarCsv(String nombreFichero, List<String> lineas) throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_CSV;
        crearDirectorioSiNoExiste();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (String linea : lineas) {
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al exportar fichero CSV: " + e.getMessage());
        }
    }

    // Lee un fichero CSV y devuelve sus lineas como lista
    public static List<String> importarCsv(String nombreFichero)
            throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_CSV;
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al importar fichero CSV: " + e.getMessage());
        }
        return lineas;
    }

    // Serializa la lista de objetos en binario usando ObjectOutputStream
    public static void exportarBinario(String nombreFichero, List<?> lista) throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_BIN;
        crearDirectorioSiNoExiste();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al exportar fichero binario: " + e.getMessage());
        }
    }

    // Deserializa una lista de objetos desde un fichero binario; requiere que la clase sea Serializable
    @SuppressWarnings("unchecked")
    public static <T> List<T> importarBinario(String nombreFichero)
            throws SeHaProducidoUnError, ClassNotFoundException {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_BIN;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<T>) ois.readObject();
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al importar fichero binario: " + e.getMessage());
        }
    }

    // Convierte la lista a JSON usando Gson con formato legible (pretty printing)
    public static void exportarJson(String nombreFichero, List<?> lista) throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_JSON;
        crearDirectorioSiNoExiste();
        try (Writer writer = new FileWriter(ruta)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(lista, writer);
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al exportar fichero JSON: " + e.getMessage());
        }
    }

    // Parsea un fichero JSON a lista de objetos del tipo indicado mediante reflection (TypeToken)
    public static <T> List<T> importarJson(String nombreFichero, Type tipo)
            throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_JSON;
        try (Reader reader = new FileReader(ruta)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, tipo);
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al importar fichero JSON: " + e.getMessage());
        }
    }

    // Crea el directorio de ficheros si aun no existe en el sistema de archivos
    private static void crearDirectorioSiNoExiste() {
        File directorio = new File(Constantes.RUTA_FICHEROS);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

    }

}
