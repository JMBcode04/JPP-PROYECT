package Swing;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Jugador;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Submenu Swing para la Gestión de Jugadores.
 * @author isard
 */
public class SubmenuGestionJugadores extends javax.swing.JDialog {

    public SubmenuGestionJugadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        cargarTabla();
    }

    // -------------------------------------------------------------------------
    // Lógica de negocio
    // -------------------------------------------------------------------------

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTableJugadores.getModel();
        // Desactivar listener durante la carga para evitar eventos espurios
        jTableJugadores.getSelectionModel().clearSelection();
        modelo.setRowCount(0);
        try {
            List<Jugador> lista = JPP_ProyectoFinal.jugadorService.consultarTodos();
            for (Jugador j : lista) {
                modelo.addRow(new Object[]{
                    j.getCodigo(), j.getNombre(), j.getFechaNacimiento(),
                    j.getNacionalidad(), j.getPosicion()
                });
            }
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error al cargar jugadores: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtFechaNacimiento.setText("");
        txtNacionalidad.setText("");
        txtPosicion.setText("");
        jTableJugadores.clearSelection();
    }

    /**
     * Valida todos los campos del formulario antes de insertar o actualizar.
     * Muestra un mensaje específico por cada error y devuelve null si hay alguno.
     */
    private Jugador validarYConstruirJugador() {
        String codigoStr       = txtCodigo.getText().trim();
        String nombre          = txtNombre.getText().trim();
        String fechaNacimiento = txtFechaNacimiento.getText().trim();
        String nacionalidad    = txtNacionalidad.getText().trim();
        String posicion        = txtPosicion.getText().trim();

        if (!BaseDialogHelper.esEnteroValido(codigoStr)) {
            JOptionPane.showMessageDialog(this,
                "El campo 'Código' debe ser un número entero.\nValor introducido: \"" + codigoStr + "\"",
                "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus(); txtCodigo.selectAll(); return null;
        }
        if (!BaseDialogHelper.esTextoValido(nombre)) {
            JOptionPane.showMessageDialog(this, "El campo 'Nombre' no puede estar vacío.",
                "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus(); return null;
        }
        if (!BaseDialogHelper.esFechaValida(fechaNacimiento)) {
            JOptionPane.showMessageDialog(this,
                "El campo 'Fecha de nacimiento' debe tener el formato dd/mm/aaaa.\nValor introducido: \"" + fechaNacimiento + "\"",
                "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtFechaNacimiento.requestFocus(); txtFechaNacimiento.selectAll(); return null;
        }
        if (!BaseDialogHelper.esTextoValido(nacionalidad)) {
            JOptionPane.showMessageDialog(this, "El campo 'Nacionalidad' no puede estar vacío.",
                "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtNacionalidad.requestFocus(); return null;
        }
        if (!BaseDialogHelper.esTextoValido(posicion)) {
            JOptionPane.showMessageDialog(this, "El campo 'Posición' no puede estar vacío.",
                "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtPosicion.requestFocus(); return null;
        }
        return new Jugador(BaseDialogHelper.toInt(codigoStr), nombre, fechaNacimiento, nacionalidad, posicion);
    }

    // -------------------------------------------------------------------------
    // Inicialización de componentes
    // -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Declarar TODOS los componentes antes de usarlos
        jPanelFormulario        = new javax.swing.JPanel();
        jLabelCodigo            = new javax.swing.JLabel();
        txtCodigo               = new javax.swing.JTextField();
        jLabelNombre            = new javax.swing.JLabel();
        txtNombre               = new javax.swing.JTextField();
        jLabelFechaNacimiento   = new javax.swing.JLabel();
        txtFechaNacimiento      = new javax.swing.JTextField();
        jLabelNacionalidad      = new javax.swing.JLabel();
        txtNacionalidad         = new javax.swing.JTextField();
        jLabelPosicion          = new javax.swing.JLabel();
        txtPosicion             = new javax.swing.JTextField();

        jButtonInsertar         = new javax.swing.JButton();
        jButtonActualizar       = new javax.swing.JButton();
        jButtonEliminar         = new javax.swing.JButton();
        jButtonConsultar1       = new javax.swing.JButton();
        jButtonConsultarTodos   = new javax.swing.JButton();
        jButtonExTXT            = new javax.swing.JButton();
        jButtonExCSV            = new javax.swing.JButton();
        jButtonExBIN            = new javax.swing.JButton();
        jButtonExJSON           = new javax.swing.JButton();
        jButtonImpTXT           = new javax.swing.JButton();
        jButtonImpCSV           = new javax.swing.JButton();
        jButtoniImpBIN          = new javax.swing.JButton();
        jButtonImpJSON          = new javax.swing.JButton();
        Volver                  = new javax.swing.JButton();

        jScrollPane1            = new javax.swing.JScrollPane();
        jTableJugadores         = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Jugadores");

        // --- Formulario ---
        jPanelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Jugador"));
        jLabelCodigo.setText("Código:");
        jLabelNombre.setText("Nombre:");
        jLabelFechaNacimiento.setText("Fecha Nacimiento (dd/mm/aaaa):");
        jLabelNacionalidad.setText("Nacionalidad:");
        jLabelPosicion.setText("Posición:");
        txtFechaNacimiento.setToolTipText("Formato obligatorio: dd/mm/aaaa  (Ej: 15/06/1995)");

        javax.swing.GroupLayout fl = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(fl);
        fl.setAutoCreateGaps(true);
        fl.setAutoCreateContainerGaps(true);
        fl.setHorizontalGroup(fl.createSequentialGroup()
            .addGroup(fl.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabelCodigo).addComponent(jLabelNombre)
                .addComponent(jLabelFechaNacimiento).addComponent(jLabelNacionalidad)
                .addComponent(jLabelPosicion))
            .addGroup(fl.createParallelGroup()
                .addComponent(txtCodigo, 200, 200, 200)
                .addComponent(txtNombre, 200, 200, 200)
                .addComponent(txtFechaNacimiento, 200, 200, 200)
                .addComponent(txtNacionalidad, 200, 200, 200)
                .addComponent(txtPosicion, 200, 200, 200))
        );
        fl.setVerticalGroup(fl.createSequentialGroup()
            .addGroup(fl.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelCodigo).addComponent(txtCodigo))
            .addGroup(fl.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelNombre).addComponent(txtNombre))
            .addGroup(fl.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelFechaNacimiento).addComponent(txtFechaNacimiento))
            .addGroup(fl.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelNacionalidad).addComponent(txtNacionalidad))
            .addGroup(fl.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelPosicion).addComponent(txtPosicion))
        );

        // --- Tabla ---
        jTableJugadores.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Código", "Nombre", "F. Nacimiento", "Nacionalidad", "Posición"}
        ) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        });
        jTableJugadores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // Listener seguro: comprueba fila >= 0 y evita doble disparo con getValueIsAdjusting
        jTableJugadores.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            int fila = jTableJugadores.getSelectedRow();
            if (fila >= 0 && fila < jTableJugadores.getRowCount()) {
                txtCodigo.setText(jTableJugadores.getValueAt(fila, 0).toString());
                txtNombre.setText(jTableJugadores.getValueAt(fila, 1).toString());
                txtFechaNacimiento.setText(jTableJugadores.getValueAt(fila, 2).toString());
                txtNacionalidad.setText(jTableJugadores.getValueAt(fila, 3).toString());
                txtPosicion.setText(jTableJugadores.getValueAt(fila, 4).toString());
            }
        });
        jScrollPane1.setViewportView(jTableJugadores);

        // --- Botones ---
        jButtonInsertar.setText("Insertar ");           jButtonInsertar.addActionListener(e -> jButtonInsertarActionPerformed(e));
        jButtonActualizar.setText("Actualizar");        jButtonActualizar.addActionListener(e -> jButtonActualizarActionPerformed(e));
        jButtonEliminar.setText("Eliminar");            jButtonEliminar.addActionListener(e -> jButtonEliminarActionPerformed(e));
        jButtonConsultar1.setText("Consultar uno");     jButtonConsultar1.addActionListener(e -> jButtonConsultar1ActionPerformed(e));
        jButtonConsultarTodos.setText("Consultar todos"); jButtonConsultarTodos.addActionListener(e -> cargarTabla());
        jButtonExTXT.setText("Exportar txt");           jButtonExTXT.addActionListener(e -> jButtonExTXTActionPerformed(e));
        jButtonExCSV.setText("Exportar csv");           jButtonExCSV.addActionListener(e -> jButtonExCSVActionPerformed(e));
        jButtonExBIN.setText("Exportar bin");           jButtonExBIN.addActionListener(e -> jButtonExBINActionPerformed(e));
        jButtonExJSON.setText("Exportar json");         jButtonExJSON.addActionListener(e -> jButtonExJSONActionPerformed(e));
        jButtonImpTXT.setText("Importar txt");          jButtonImpTXT.addActionListener(e -> jButtonImpTXTActionPerformed(e));
        jButtonImpCSV.setText("Importar csv");          jButtonImpCSV.addActionListener(e -> jButtonImpCSVActionPerformed(e));
        jButtoniImpBIN.setText("Importar bin");         jButtoniImpBIN.addActionListener(e -> jButtoniImpBINActionPerformed(e));
        jButtonImpJSON.setText("Importar json");        jButtonImpJSON.addActionListener(e -> jButtonImpJSONActionPerformed(e));
        Volver.setText("Volver");                       Volver.addActionListener(e -> VolverActionPerformed(e));

        javax.swing.JPanel panelBotones = new javax.swing.JPanel(new java.awt.GridLayout(0, 2, 5, 5));
        panelBotones.add(jButtonInsertar);   panelBotones.add(jButtonActualizar);
        panelBotones.add(jButtonEliminar);   panelBotones.add(jButtonConsultar1);
        panelBotones.add(jButtonConsultarTodos); panelBotones.add(new javax.swing.JLabel());
        panelBotones.add(jButtonExTXT);      panelBotones.add(jButtonImpTXT);
        panelBotones.add(jButtonExCSV);      panelBotones.add(jButtonImpCSV);
        panelBotones.add(jButtonExBIN);      panelBotones.add(jButtoniImpBIN);
        panelBotones.add(jButtonExJSON);     panelBotones.add(jButtonImpJSON);
        panelBotones.add(Volver);

        // --- Layout principal ---
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(jPanelFormulario)
                .addComponent(panelBotones))
            .addComponent(jScrollPane1, 400, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelFormulario)
                .addComponent(panelBotones))
            .addComponent(jScrollPane1)
        );

        pack();
        setSize(820, 520);
    }

    // -------------------------------------------------------------------------
    // Handlers de botones
    // -------------------------------------------------------------------------

    private void jButtonInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        Jugador jugador = validarYConstruirJugador();
        if (jugador == null) return;
        try {
            JPP_ProyectoFinal.jugadorService.insertar(jugador);
            JOptionPane.showMessageDialog(this, "Jugador insertado correctamente.");
            limpiarCampos(); 
            cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
    }

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Jugador jugador = validarYConstruirJugador();
        if (jugador == null) return;
        try {
            JPP_ProyectoFinal.jugadorService.actualizar(jugador);
            JOptionPane.showMessageDialog(this, "Jugador actualizado correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        String codigoStr = txtCodigo.getText().trim();
        if (!BaseDialogHelper.esEnteroValido(codigoStr)) {
            JOptionPane.showMessageDialog(this, "Introduce un código numérico válido para eliminar.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE); return;
        }
        int codigo = BaseDialogHelper.toInt(codigoStr);
        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Seguro que deseas eliminar el jugador con código " + codigo + "?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            JPP_ProyectoFinal.jugadorService.eliminar(codigo);
            JOptionPane.showMessageDialog(this, "Jugador eliminado correctamente.");
            limpiarCampos(); cargarTabla();
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonConsultar1ActionPerformed(java.awt.event.ActionEvent evt) {
        String codigoStr = txtCodigo.getText().trim();
        if (!BaseDialogHelper.esEnteroValido(codigoStr)) {
            JOptionPane.showMessageDialog(this, "Introduce un código numérico válido para consultar.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE); return;
        }
        try {
            Jugador jugador = JPP_ProyectoFinal.jugadorService.consultar(BaseDialogHelper.toInt(codigoStr));
            txtNombre.setText(jugador.getNombre());
            txtFechaNacimiento.setText(jugador.getFechaNacimiento());
            txtNacionalidad.setText(jugador.getNacionalidad());
            txtPosicion.setText(jugador.getPosicion());
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonExTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.exportarTxt(); JOptionPane.showMessageDialog(this, "Exportado a TXT correctamente."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonExCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.exportarCsv(); JOptionPane.showMessageDialog(this, "Exportado a CSV correctamente."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonExBINActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.exportarBinario(); JOptionPane.showMessageDialog(this, "Exportado a Binario correctamente."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonExJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.exportarJson(); JOptionPane.showMessageDialog(this, "Exportado a JSON correctamente."); }
        catch (SeHaProducidoUnError e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.jugadorService.importarTxt();
            JOptionPane.showMessageDialog(this, "Importado desde TXT.");
            cargarTabla();
        } catch (YaImportadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jButtonImpCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.importarCsv(); JOptionPane.showMessageDialog(this, "Importado desde CSV."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtoniImpBINActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.importarBinario(); JOptionPane.showMessageDialog(this, "Importado desde Binario."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        catch (ClassNotFoundException e) { JOptionPane.showMessageDialog(this, "Error de clase: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }
    private void jButtonImpJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try { JPP_ProyectoFinal.jugadorService.importarJson(); JOptionPane.showMessageDialog(this, "Importado desde JSON."); cargarTabla(); }
        catch (YaImportadoException e) { JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE); }
        catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Volver;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonConsultar1;
    private javax.swing.JButton jButtonConsultarTodos;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonExBIN;
    private javax.swing.JButton jButtonExCSV;
    private javax.swing.JButton jButtonExJSON;
    private javax.swing.JButton jButtonExTXT;
    private javax.swing.JButton jButtonImpCSV;
    private javax.swing.JButton jButtonImpJSON;
    private javax.swing.JButton jButtonImpTXT;
    private javax.swing.JButton jButtonInsertar;
    private javax.swing.JButton jButtoniImpBIN;
    private javax.swing.JLabel jLabelCodigo;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelNacionalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPosicion;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableJugadores;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPosicion;
    // End of variables declaration//GEN-END:variables
}
