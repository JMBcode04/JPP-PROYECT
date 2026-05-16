package Swing;

/**
 * Utilidades de validación compartidas por todos los diálogos Swing.
 * Centraliza la lógica de validación para evitar duplicación y garantizar
 * consistencia en todos los formularios.
 */
public class BaseDialogHelper {

    /**
     * Valida que una cadena sea un entero válido.
     * @return true si es parseable como int, false si no.
     */
    public static boolean esEnteroValido(String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        try {
            Integer.parseInt(valor.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida formato de fecha dd/mm/aaaa.
     */
    public static boolean esFechaValida(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return false;
        return fecha.trim().matches("\\d{2}/\\d{2}/\\d{4}");
    }

    /**
     * Valida que el texto no esté vacío.
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Convierte String a int. Llamar solo si esEnteroValido() ya devolvió true.
     */
    public static int toInt(String valor) {
        return Integer.parseInt(valor.trim());
    }

    /**
     * Convierte String a Integer nullable: vacío → null, número → Integer.
     * Si el valor no es un número válido lanza NumberFormatException.
     */
    public static Integer toIntOpcional(String valor) throws NumberFormatException {
        if (valor == null || valor.trim().isEmpty()) return null;
        int v = Integer.parseInt(valor.trim());
        return v == 0 ? null : v;
    }
}
