/*
 * Dialogo Swing para Gestion de Jugador-Equipo
 */
package Swing;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Jugador_equipo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Submenu Swing para la Gestion de Jugador-Equipo.
 *
 * @author jorge
 */
public class SubmenuGestionJugadorEquipo extends javax.swing.JDialog {

    public SubmenuGestionJugadorEquipo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarTabla();
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTableJugadorEquipo.getModel();
        jTableJugadorEquipo.getSelectionModel().clearSelection();
        modelo.setRowCount(0);
        try {
            List<Jugador_equipo> lista = JPP_ProyectoFinal.jugadorEquipoService.consultarTodos();
            for (Jugador_equipo je : lista) {
                modelo.addRow(new Object[]{
                    je.getCodigoEquipo(), je.getCodigoJugador(), je.getAñoEntrada(),
                    je.getAñoSalida() != null ? je.getAñoSalida() : "Activo",
                    je.getPartidosTitular() != null ? je.getPartidosTitular() : "-"
                });
            }
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigoEquipo.setText("");
        txtCodigoJugador.setText("");
        txtAñoEntrada.setText("");
        txtAñoSalida.setText("");
        txtPartidosTitular.setText("");
    }

    /**
     * Valida y construye un Jugador_equipo.
     * Campos obligatorios numéricos: codigoEquipo, codigoJugador, añoEntrada.
     * Opcionales: añoSalida (vacío = activo), partidosTitular (vacío = null).
     */
    private Jugador_equipo validarYConstruirJugadorEquipo() {
        String codEquipoStr = txtCodigoEquipo.getText().trim();
        String codJugadorStr = txtCodigoJugador.getText().trim();
        String añoEntradaStr = txtAñoEntrada.getText().trim();
        String añoSalidaStr = txtAñoSalida.getText().trim();
        String partidosStr = txtPartidosTitular.getText().trim();

        if (codEquipoStr.isEmpty() || codJugadorStr.isEmpty() || añoEntradaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos Código Equipo, Código Jugador y Año de Entrada son obligatorios.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE); return null;
        }

        int codigoEquipo, codigoJugador, añoEntrada;
        try {
            codigoEquipo = Integer.parseInt(codEquipoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El 'Código Equipo' debe ser un número entero.\nValor: \"" + codEquipoStr + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtCodigoEquipo.requestFocus(); txtCodigoEquipo.selectAll(); return null;
        }
        try {
            codigoJugador = Integer.parseInt(codJugadorStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El 'Código Jugador' debe ser un número entero.\nValor: \"" + codJugadorStr + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtCodigoJugador.requestFocus(); txtCodigoJugador.selectAll(); return null;
        }
        try {
            añoEntrada = Integer.parseInt(añoEntradaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El 'Año de entrada' debe ser un número entero.\nValor: \"" + añoEntradaStr + "\"",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtAñoEntrada.requestFocus(); txtAñoEntrada.selectAll(); return null;
        }

        Integer añoSalida = null;
        if (!añoSalidaStr.isEmpty()) {
            try {
                int v = Integer.parseInt(añoSalidaStr);
                añoSalida = v == 0 ? null : v;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El 'Año de salida' debe ser un número entero (o dejar vacío si sigue activo).\nValor: \"" + añoSalidaStr + "\"",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                txtAñoSalida.requestFocus(); txtAñoSalida.selectAll(); return null;
            }
        }

        Integer partidosTitular = null;
        if (!partidosStr.isEmpty()) {
            try {
                int v = Integer.parseInt(partidosStr);
                partidosTitular = v == 0 ? null : v;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Los 'Partidos como titular' deben ser un número entero (o dejar vacío).\nValor: \"" + partidosStr + "\"",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                txtPartidosTitular.requestFocus(); txtPartidosTitular.selectAll(); return null;
            }
        }

        return new Jugador_equipo(codigoEquipo, codigoJugador, añoEntrada, añoSalida, partidosTitular);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanelFormulario = new javax.swing.JPanel();
        jLabelCodigoEquipo = new javax.swing.JLabel();
        txtCodigoEquipo = new javax.swing.JTextField();
        jLabelCodigoJugador = new javax.swing.JLabel();
        txtCodigoJugador = new javax.swing.JTextField();
        jLabelAñoEntrada = new javax.swing.JLabel();
        txtAñoEntrada = new javax.swing.JTextField();
        jLabelAñoSalida = new javax.swing.JLabel();
        txtAñoSalida = new javax.swing.JTextField();
        jLabelPartidosTitular = new javax.swing.JLabel();
        txtPartidosTitular = new javax.swing.JTextField();

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
        jTableJugadorEquipo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Jugador-Equipo");

        jPanelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Jugador-Equipo"));
        jLabelCodigoEquipo.setText("Código Equipo:");
        jLabelCodigoJugador.setText("Código Jugador:");
        jLabelAñoEntrada.setText("Año de Entrada:");
        jLabelAñoSalida.setText("Año de Salida (vacío=activo):");
        jLabelPartidosTitular.setText("Partidos Titular (vacío=sin dato):");

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(formLayout);
        formLayout.setAutoCreateGaps(true);
        formLayout.setAutoCreateContainerGaps(true);
        formLayout.setHorizontalGroup(
            formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCodigoEquipo).addComponent(jLabelCodigoJugador)
                    .addComponent(jLabelAñoEntrada).addComponent(jLabelAñoSalida)
                    .addComponent(jLabelPartidosTitular))
                .addGroup(formLayout.createParallelGroup()
                    .addComponent(txtCodigoEquipo, 180, 180, 180)
                    .addComponent(txtCodigoJugador, 180, 180, 180)
                    .addComponent(txtAñoEntrada, 180, 180, 180)
                    .addComponent(txtAñoSalida, 180, 180, 180)
                    .addComponent(txtPartidosTitular, 180, 180, 180))
        );
        formLayout.setVerticalGroup(
            formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelCodigoEquipo).addComponent(txtCodigoEquipo))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelCodigoJugador).addComponent(txtCodigoJugador))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelAñoEntrada).addComponent(txtAñoEntrada))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelAñoSalida).addComponent(txtAñoSalida))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelPartidosTitular).addComponent(txtPartidosTitular))
        );

        jTableJugadorEquipo.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Cód. Equipo", "Cód. Jugador", "Año Entrada", "Año Salida", "Partidos Titular"}
        ) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        jTableJugadorEquipo.getSelectionModel().addListSelectionListener(e -> { if (e.getValueIsAdjusting()) return;
            int fila = jTableJugadorEquipo.getSelectedRow();
            if (fila >= 0) {
                txtCodigoEquipo.setText(jTableJugadorEquipo.getValueAt(fila, 0).toString());
                txtCodigoJugador.setText(jTableJugadorEquipo.getValueAt(fila, 1).toString());
                txtAñoEntrada.setText(jTableJugadorEquipo.getValueAt(fila, 2).toString());
                String as = jTableJugadorEquipo.getValueAt(fila, 3).toString();
                txtAñoSalida.setText(as.equals("Activo") ? "" : as);
                String pt = jTableJugadorEquipo.getValueAt(fila, 4).toString();
                txtPartidosTitular.setText(pt.equals("-") ? "" : pt);
            }
        });
        jScrollPane1.setViewportView(jTableJugadorEquipo);

        jButtonInsertar.setText("Insertar"); jButtonInsertar.addActionListener(evt -> jButtonInsertarActionPerformed(evt));
        jButtonActualizar.setText("Actualizar"); jButtonActualizar.addActionListener(evt -> jButtonActualizarActionPerformed(evt));
        jButtonEliminar.setText("Eliminar"); jButtonEliminar.addActionListener(evt -> jButtonEliminarActionPerformed(evt));
        jButtonConsultar1.setText("Consultar uno"); jButtonConsultar1.addActionListener(evt -> jButtonConsultar1ActionPerformed(evt));
        jButtonConsultarTodos.setText("Consultar todos"); jButtonConsultarTodos.addActionListener(evt -> cargarTabla());
        jButtonExTXT.setText("Exportar TXT"); jButtonExTXT.addActionListener(evt -> { try { JPP_ProyectoFinal.jugadorEquipoService.exportarTXT(); JOptionPane.showMessageDialog(this, "Exportado a TXT."); } catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } });
        jButtonExCSV.setText("Exportar CSV"); jButtonExCSV.addActionListener(evt -> { try { JPP_ProyectoFinal.jugadorEquipoService.exportarCSv(); JOptionPane.showMessageDialog(this, "Exportado a CSV."); } catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } });
        jButtonExBIN.setText("Exportar BIN"); jButtonExBIN.addActionListener(evt -> { try { JPP_ProyectoFinal.jugadorEquipoService.exportarBinario(); JOptionPane.showMessageDialog(this, "Exportado a Binario."); } catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } });
        jButtonExJSON.setText("Exportar JSON"); jButtonExJSON.addActionListener(evt -> { try { JPP_ProyectoFinal.jugadorEquipoService.exportarJson(); JOptionPane.showMessageDialog(this, "Exportado a JSON."); } catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); } });
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

        pack(); setSize(850, 500); setLocationRelativeTo(null);
    }

    private void jButtonInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        Jugador_equipo je = validarYConstruirJugadorEquipo();
        if (je == null) return;
        try {
            JPP_ProyectoFinal.jugadorEquipoService.insertar(je);
            JOptionPane.showMessageDialog(this, "Relación insertada correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Jugador_equipo je = validarYConstruirJugadorEquipo();
        if (je == null) return;
        try {
            JPP_ProyectoFinal.jugadorEquipoService.actualizar(je);
            JOptionPane.showMessageDialog(this, "Relación actualizada correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        String codJugStr = txtCodigoJugador.getText().trim();
        if (codJugStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce el código del jugador para eliminar.", "Error", JOptionPane.WARNING_MESSAGE); return;
        }
        try {
            int codigoJugador = Integer.parseInt(codJugStr);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar la relación del jugador " + codigoJugador + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            JPP_ProyectoFinal.jugadorEquipoService.eliminar(codigoJugador);
            JOptionPane.showMessageDialog(this, "Relación eliminada correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código del jugador debe ser un número entero.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonConsultar1ActionPerformed(java.awt.event.ActionEvent evt) {
        String codJugStr = txtCodigoJugador.getText().trim();
        if (codJugStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce el código del jugador para consultar.", "Error", JOptionPane.WARNING_MESSAGE); return;
        }
        try {
            int codigoJugador = Integer.parseInt(codJugStr);
            Jugador_equipo je = JPP_ProyectoFinal.jugadorEquipoService.consultar(codigoJugador);
            txtCodigoEquipo.setText(String.valueOf(je.getCodigoEquipo()));
            txtAñoEntrada.setText(String.valueOf(je.getAñoEntrada()));
            txtAñoSalida.setText(je.getAñoSalida() != null ? je.getAñoSalida().toString() : "");
            txtPartidosTitular.setText(je.getPartidosTitular() != null ? je.getPartidosTitular().toString() : "");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código del jugador debe ser un número entero.", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonImpTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorEquipoService.importarTxt(); JOptionPane.showMessageDialog(this, "Importado desde TXT."); cargarTabla(); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorEquipoService.importarCsv(); JOptionPane.showMessageDialog(this, "Importado desde CSV."); cargarTabla(); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtoniImpBINActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorEquipoService.importarBinario(); JOptionPane.showMessageDialog(this, "Importado desde Binario."); cargarTabla(); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorEquipoService.importarJson(); JOptionPane.showMessageDialog(this, "Importado desde JSON."); cargarTabla(); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    // Variables declaration
    private javax.swing.JButton Volver;
    private javax.swing.JButton jButtonActualizar, jButtonConsultar1, jButtonConsultarTodos;
    private javax.swing.JButton jButtonEliminar, jButtonExBIN, jButtonExCSV, jButtonExJSON, jButtonExTXT;
    private javax.swing.JButton jButtonImpCSV, jButtonImpJSON, jButtonImpTXT, jButtonInsertar, jButtoniImpBIN;
    private javax.swing.JLabel jLabelCodigoEquipo, jLabelCodigoJugador, jLabelAñoEntrada, jLabelAñoSalida, jLabelPartidosTitular;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableJugadorEquipo;
    private javax.swing.JTextField txtCodigoEquipo, txtCodigoJugador, txtAñoEntrada, txtAñoSalida, txtPartidosTitular;
    // End of variables declaration
}
