/*
 * Dialogo Swing para Gestion de Partidos
 */
package Swing;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Partido;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Submenu Swing para la Gestion de Partidos.
 *
 * @author jorge
 */
public class SubmenuGestionPartidos extends javax.swing.JDialog {

    public SubmenuGestionPartidos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTablePartidos.getModel();
        jTablePartidos.getSelectionModel().clearSelection();
        modelo.setRowCount(0);
        try {
            List<Partido> lista = JPP_ProyectoFinal.partidoService.consultarTodos();
            for (Partido p : lista) {
                modelo.addRow(new Object[]{
                    p.getCodigoEquipoLocal(), p.getCodigoEquipoVisitante(),
                    p.getAñoTemporada(), p.getFecha(),
                    p.getPuntuacionLocal() != null ? p.getPuntuacionLocal() : "-",
                    p.getPuntuacionVisitante() != null ? p.getPuntuacionVisitante() : "-"
                });
            }
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error al cargar partidos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigoLocal.setText("");
        txtCodigoVisitante.setText("");
        txtAñoTemporada.setText("");
        txtFecha.setText("");
        txtPuntuacionLocal.setText("");
        txtPuntuacionVisitante.setText("");
    }

    /**
     * Valida los campos y construye un Partido.
     * Campos numéricos: codigoLocal, codigoVisitante, añoTemporada, puntuaciones (opcionales).
     * Fecha en formato dd/mm/aaaa.
     * Devuelve null si hay error.
     */
    private Partido validarYConstruirPartido() {
        String codLocalStr = txtCodigoLocal.getText().trim();
        String codVisitanteStr = txtCodigoVisitante.getText().trim();
        String añoStr = txtAñoTemporada.getText().trim();
        String fecha = txtFecha.getText().trim();
        String puntLocalStr = txtPuntuacionLocal.getText().trim();
        String puntVisitanteStr = txtPuntuacionVisitante.getText().trim();

        if (codLocalStr.isEmpty() || codVisitanteStr.isEmpty() || añoStr.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Los campos Código Local, Código Visitante, Año Temporada y Fecha son obligatorios.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        int codigoLocal, codigoVisitante, añoTemporada;
        try {
            codigoLocal = Integer.parseInt(codLocalStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Código equipo local' debe ser un número entero.\nValor: \"" + codLocalStr + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtCodigoLocal.requestFocus(); txtCodigoLocal.selectAll(); return null;
        }
        try {
            codigoVisitante = Integer.parseInt(codVisitanteStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Código equipo visitante' debe ser un número entero.\nValor: \"" + codVisitanteStr + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtCodigoVisitante.requestFocus(); txtCodigoVisitante.selectAll(); return null;
        }
        try {
            añoTemporada = Integer.parseInt(añoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Año de temporada' debe ser un número entero.\nValor: \"" + añoStr + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtAñoTemporada.requestFocus(); txtAñoTemporada.selectAll(); return null;
        }
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/mm/aaaa.\nValor: \"" + fecha + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtFecha.requestFocus(); txtFecha.selectAll(); return null;
        }

        // Puntuaciones son opcionales: si vacío o "0" → null
        Integer puntuacionLocal = null;
        if (!puntLocalStr.isEmpty()) {
            try {
                int v = Integer.parseInt(puntLocalStr);
                puntuacionLocal = v == 0 ? null : v;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La puntuación local debe ser un número entero (o dejar vacío).\nValor: \"" + puntLocalStr + "\"",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                txtPuntuacionLocal.requestFocus(); txtPuntuacionLocal.selectAll(); return null;
            }
        }
        Integer puntuacionVisitante = null;
        if (!puntVisitanteStr.isEmpty()) {
            try {
                int v = Integer.parseInt(puntVisitanteStr);
                puntuacionVisitante = v == 0 ? null : v;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La puntuación visitante debe ser un número entero (o dejar vacío).\nValor: \"" + puntVisitanteStr + "\"",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                txtPuntuacionVisitante.requestFocus(); txtPuntuacionVisitante.selectAll(); return null;
            }
        }

        return new Partido(codigoLocal, codigoVisitante, añoTemporada, fecha, puntuacionLocal, puntuacionVisitante);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanelFormulario = new javax.swing.JPanel();
        jLabelCodigoLocal = new javax.swing.JLabel();
        txtCodigoLocal = new javax.swing.JTextField();
        jLabelCodigoVisitante = new javax.swing.JLabel();
        txtCodigoVisitante = new javax.swing.JTextField();
        jLabelAñoTemporada = new javax.swing.JLabel();
        txtAñoTemporada = new javax.swing.JTextField();
        jLabelFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabelPuntuacionLocal = new javax.swing.JLabel();
        txtPuntuacionLocal = new javax.swing.JTextField();
        jLabelPuntuacionVisitante = new javax.swing.JLabel();
        txtPuntuacionVisitante = new javax.swing.JTextField();

        jButtonInsertar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonConsultar1 = new javax.swing.JButton();
        jButtonConsultarTodos = new javax.swing.JButton();
        jButtonExTXT = new javax.swing.JButton();
        jButtonExCSV = new javax.swing.JButton();
        jButtonExBIN = new javax.swing.JButton();
        jButtonExJSON = new javax.swing.JButton();
        jButtonImpTXT = new javax.swing.JButton();
        jButtonImpCSV = new javax.swing.JButton();
        jButtoniImpBIN = new javax.swing.JButton();
        jButtonImpJSON = new javax.swing.JButton();
        Volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePartidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Partidos");

        jPanelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Partido"));
        jLabelCodigoLocal.setText("Cód. Equipo Local:");
        jLabelCodigoVisitante.setText("Cód. Equipo Visitante:");
        jLabelAñoTemporada.setText("Año Temporada:");
        jLabelFecha.setText("Fecha (dd/mm/aaaa):");
        jLabelPuntuacionLocal.setText("Punt. Local (vacío=sin dato):");
        jLabelPuntuacionVisitante.setText("Punt. Visitante (vacío=sin dato):");
        txtFecha.setToolTipText("Formato: dd/mm/aaaa");

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(formLayout);
        formLayout.setAutoCreateGaps(true);
        formLayout.setAutoCreateContainerGaps(true);
        formLayout.setHorizontalGroup(
            formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCodigoLocal).addComponent(jLabelCodigoVisitante)
                    .addComponent(jLabelAñoTemporada).addComponent(jLabelFecha)
                    .addComponent(jLabelPuntuacionLocal).addComponent(jLabelPuntuacionVisitante))
                .addGroup(formLayout.createParallelGroup()
                    .addComponent(txtCodigoLocal, 180, 180, 180)
                    .addComponent(txtCodigoVisitante, 180, 180, 180)
                    .addComponent(txtAñoTemporada, 180, 180, 180)
                    .addComponent(txtFecha, 180, 180, 180)
                    .addComponent(txtPuntuacionLocal, 180, 180, 180)
                    .addComponent(txtPuntuacionVisitante, 180, 180, 180))
        );
        formLayout.setVerticalGroup(
            formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelCodigoLocal).addComponent(txtCodigoLocal))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelCodigoVisitante).addComponent(txtCodigoVisitante))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelAñoTemporada).addComponent(txtAñoTemporada))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelFecha).addComponent(txtFecha))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelPuntuacionLocal).addComponent(txtPuntuacionLocal))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelPuntuacionVisitante).addComponent(txtPuntuacionVisitante))
        );

        jTablePartidos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Local", "Visitante", "Temporada", "Fecha", "Pts Local", "Pts Visit."}
        ) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        jTablePartidos.getSelectionModel().addListSelectionListener(e -> { if (e.getValueIsAdjusting()) return;
            int fila = jTablePartidos.getSelectedRow();
            if (fila >= 0) {
                txtCodigoLocal.setText(jTablePartidos.getValueAt(fila, 0).toString());
                txtCodigoVisitante.setText(jTablePartidos.getValueAt(fila, 1).toString());
                txtAñoTemporada.setText(jTablePartidos.getValueAt(fila, 2).toString());
                txtFecha.setText(jTablePartidos.getValueAt(fila, 3).toString());
                String pL = jTablePartidos.getValueAt(fila, 4).toString();
                String pV = jTablePartidos.getValueAt(fila, 5).toString();
                txtPuntuacionLocal.setText(pL.equals("-") ? "" : pL);
                txtPuntuacionVisitante.setText(pV.equals("-") ? "" : pV);
            }
        });
        jScrollPane1.setViewportView(jTablePartidos);

        jButtonInsertar.setText("Insertar"); jButtonInsertar.addActionListener(evt -> jButtonInsertarActionPerformed(evt));
        jButtonActualizar.setText("Actualizar"); jButtonActualizar.addActionListener(evt -> jButtonActualizarActionPerformed(evt));
        jButtonEliminar.setText("Eliminar"); jButtonEliminar.addActionListener(evt -> jButtonEliminarActionPerformed(evt));
        jButtonConsultar1.setText("Consultar uno"); jButtonConsultar1.addActionListener(evt -> jButtonConsultar1ActionPerformed(evt));
        jButtonConsultarTodos.setText("Consultar todos"); jButtonConsultarTodos.addActionListener(evt -> cargarTabla());
        jButtonExTXT.setText("Exportar TXT"); jButtonExTXT.addActionListener(evt -> jButtonExTXTActionPerformed(evt));
        jButtonExCSV.setText("Exportar CSV"); jButtonExCSV.addActionListener(evt -> jButtonExCSVActionPerformed(evt));
        jButtonExBIN.setText("Exportar BIN"); jButtonExBIN.addActionListener(evt -> jButtonExBINActionPerformed(evt));
        jButtonExJSON.setText("Exportar JSON"); jButtonExJSON.addActionListener(evt -> jButtonExJSONActionPerformed(evt));
        jButtonImpTXT.setText("Importar TXT"); jButtonImpTXT.addActionListener(evt -> jButtonImpTXTActionPerformed(evt));
        jButtonImpCSV.setText("Importar CSV"); jButtonImpCSV.addActionListener(evt -> jButtonImpCSVActionPerformed(evt));
        jButtoniImpBIN.setText("Importar BIN"); jButtoniImpBIN.addActionListener(evt -> jButtoniImpBINActionPerformed(evt));
        jButtonImpJSON.setText("Importar JSON"); jButtonImpJSON.addActionListener(evt -> jButtonImpJSONActionPerformed(evt));
        Volver.setText("Volver"); Volver.addActionListener(evt -> setVisible(false));

        javax.swing.JPanel jPanelBotones = new javax.swing.JPanel(new java.awt.GridLayout(0, 2, 5, 5));
        jPanelBotones.add(jButtonInsertar); jPanelBotones.add(jButtonActualizar);
        jPanelBotones.add(jButtonEliminar); jPanelBotones.add(jButtonConsultar1);
        jPanelBotones.add(jButtonConsultarTodos); jPanelBotones.add(new javax.swing.JLabel());
        jPanelBotones.add(jButtonExTXT); jPanelBotones.add(jButtonImpTXT);
        jPanelBotones.add(jButtonExCSV); jPanelBotones.add(jButtonImpCSV);
        jPanelBotones.add(jButtonExBIN); jPanelBotones.add(jButtoniImpBIN);
        jPanelBotones.add(jButtonExJSON); jPanelBotones.add(jButtonImpJSON);
        jPanelBotones.add(Volver);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup().addComponent(jPanelFormulario).addComponent(jPanelBotones))
            .addComponent(jScrollPane1, 420, 420, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup().addComponent(jPanelFormulario).addComponent(jPanelBotones))
            .addComponent(jScrollPane1));

        pack(); setSize(850, 520); setLocationRelativeTo(null);
    }

    private void jButtonInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        Partido partido = validarYConstruirPartido();
        if (partido == null) return;
        try {
            JPP_ProyectoFinal.partidoService.insertar(partido);
            JOptionPane.showMessageDialog(this, "Partido insertado correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Partido partido = validarYConstruirPartido();
        if (partido == null) return;
        try {
            JPP_ProyectoFinal.partidoService.actualizar(partido);
            JOptionPane.showMessageDialog(this, "Partido actualizado correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        String cL = txtCodigoLocal.getText().trim(), cV = txtCodigoVisitante.getText().trim(), aT = txtAñoTemporada.getText().trim();
        if (cL.isEmpty() || cV.isEmpty() || aT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce los tres campos clave para eliminar.", "Error", JOptionPane.WARNING_MESSAGE); return;
        }
        try {
            int codigoLocal = Integer.parseInt(cL);
            int codigoVisitante = Integer.parseInt(cV);
            int añoTemporada = Integer.parseInt(aT);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar este partido?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            JPP_ProyectoFinal.partidoService.eliminar(codigoLocal, codigoVisitante, añoTemporada);
            JOptionPane.showMessageDialog(this, "Partido eliminado correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los códigos y el año deben ser números enteros.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonConsultar1ActionPerformed(java.awt.event.ActionEvent evt) {
        String cL = txtCodigoLocal.getText().trim(), cV = txtCodigoVisitante.getText().trim(), aT = txtAñoTemporada.getText().trim();
        if (cL.isEmpty() || cV.isEmpty() || aT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce los tres campos clave para consultar.", "Error", JOptionPane.WARNING_MESSAGE); return;
        }
        try {
            int codigoLocal = Integer.parseInt(cL);
            int codigoVisitante = Integer.parseInt(cV);
            int añoTemporada = Integer.parseInt(aT);
            Partido p = JPP_ProyectoFinal.partidoService.consultar(codigoLocal, codigoVisitante, añoTemporada);
            txtFecha.setText(p.getFecha());
            txtPuntuacionLocal.setText(p.getPuntuacionLocal() != null ? p.getPuntuacionLocal().toString() : "");
            txtPuntuacionVisitante.setText(p.getPuntuacionVisitante() != null ? p.getPuntuacionVisitante().toString() : "");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los códigos y el año deben ser números enteros.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonExTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.exportarTxt(); JOptionPane.showMessageDialog(this, "Exportado a TXT."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonExCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.exportarCsv(); JOptionPane.showMessageDialog(this, "Exportado a CSV."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonExBINActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.exportarBinario(); JOptionPane.showMessageDialog(this, "Exportado a Binario."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonExJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.exportarJson(); JOptionPane.showMessageDialog(this, "Exportado a JSON."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.importarTxt(); JOptionPane.showMessageDialog(this, "Importado desde TXT."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.importarCsv(); JOptionPane.showMessageDialog(this, "Importado desde CSV."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtoniImpBINActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.importarBinario(); JOptionPane.showMessageDialog(this, "Importado desde Binario."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        catch (ClassNotFoundException e) { JOptionPane.showMessageDialog(this, "Error de clase: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.partidoService.importarJson(); JOptionPane.showMessageDialog(this, "Importado desde JSON."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    // Variables declaration
    private javax.swing.JButton Volver;
    private javax.swing.JButton jButtonActualizar, jButtonConsultar1, jButtonConsultarTodos;
    private javax.swing.JButton jButtonEliminar, jButtonExBIN, jButtonExCSV, jButtonExJSON, jButtonExTXT;
    private javax.swing.JButton jButtonImpCSV, jButtonImpJSON, jButtonImpTXT, jButtonInsertar, jButtoniImpBIN;
    private javax.swing.JLabel jLabelCodigoLocal, jLabelCodigoVisitante, jLabelAñoTemporada;
    private javax.swing.JLabel jLabelFecha, jLabelPuntuacionLocal, jLabelPuntuacionVisitante;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePartidos;
    private javax.swing.JTextField txtCodigoLocal, txtCodigoVisitante, txtAñoTemporada;
    private javax.swing.JTextField txtFecha, txtPuntuacionLocal, txtPuntuacionVisitante;
    // End of variables declaration
}
