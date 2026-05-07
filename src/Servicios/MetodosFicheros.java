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
public class MetodosFicheros {
 
 
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

    
    public static List<String> importarTxt(String nombreFichero, boolean yaImportado)
            throws SeHaProducidoUnError, YaImportadoException {
        if (yaImportado) {
            throw new YaImportadoException();
        }
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

   
    public static List<String> importarCsv(String nombreFichero, boolean yaImportado)
            throws SeHaProducidoUnError, YaImportadoException {
        if (yaImportado) {
            throw new YaImportadoException();
        }
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

   
    public static void exportarBinario(String nombreFichero, List<?> lista) throws SeHaProducidoUnError {
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_BIN;
        crearDirectorioSiNoExiste();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al exportar fichero binario: " + e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    public static <T> List<T> importarBinario(String nombreFichero, boolean yaImportado)
            throws SeHaProducidoUnError, YaImportadoException, ClassNotFoundException {
        if (yaImportado) {
            throw new YaImportadoException();
        }
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_BIN;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (List<T>) ois.readObject();
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al importar fichero binario: " + e.getMessage());
        }
    }

    
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

  
    public static <T> List<T> importarJson(String nombreFichero, boolean yaImportado, Type tipo)
            throws SeHaProducidoUnError, YaImportadoException {
        if (yaImportado) {
            throw new YaImportadoException();
        }
        String ruta = Constantes.RUTA_FICHEROS + nombreFichero + Constantes.EXT_JSON;
        try (Reader reader = new FileReader(ruta)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, tipo);
        } catch (IOException e) {
            throw new SeHaProducidoUnError("Error al importar fichero JSON: " + e.getMessage());
        }
    }

   //creaun directorio si noexiste
    private static void crearDirectorioSiNoExiste() {
        File directorio = new File(Constantes.RUTA_FICHEROS);
        if (!directorio.exists()) {
            directorio.mkdirs();
    }
    
    }
 
}
