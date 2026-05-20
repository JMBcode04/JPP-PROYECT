/*
 * Dialogo Swing para Gestion de Equipos
 */
package Swing;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Excepciones.YaImportadoException;
import Main.JPP_ProyectoFinal;
import Modelos.Equipo;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Submenu Swing para la Gestion de Equipos.
 *
 * @author jorge
 */
public class SubmenuGestionEquipos extends javax.swing.JDialog {

    public SubmenuGestionEquipos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarTabla();
    }

    // Carga todos los equipos en la tabla
    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) jTableEquipos.getModel();
        jTableEquipos.getSelectionModel().clearSelection();
        modelo.setRowCount(0);
        try {
            List<Equipo> lista = JPP_ProyectoFinal.equipoService.consultarTodos();
            for (Equipo e : lista) {
                modelo.addRow(new Object[]{
                    e.getCodigo(), e.getNombre(), e.getañoFundacion(),
                    e.getLugarSede(), e.getEstadio(), e.getSociosAficionados()
                });
            }
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Limpia los campos del formulario
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtAñoFundacion.setText("");
        txtLugarSede.setText("");
        txtEstadio.setText("");
        txtSociosAficionados.setText("");
    }

    // Valida que los campos obligatorios estén bien rellenados
    // Devuelve null si hay error, o el Equipo construido si todo es correcto
    private Equipo validarYConstruirEquipo() {
        String codigoStr = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String añoStr = txtAñoFundacion.getText().trim();
        String lugarSede = txtLugarSede.getText().trim();
        String estadio = txtEstadio.getText().trim();
        String sociosStr = txtSociosAficionados.getText().trim();

        if (codigoStr.isEmpty() || nombre.isEmpty() || añoStr.isEmpty()
                || lugarSede.isEmpty() || estadio.isEmpty() || sociosStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        int codigo, añoFundacion, sociosAficionados;
        try {
            codigo = Integer.parseInt(codigoStr);
            System.out.println("El codigo es en la interfaz " + codigo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Código' debe ser un número entero.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtCodigo.requestFocus();
            return null;
        }
        try {
            añoFundacion = Integer.parseInt(añoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Año de fundación' debe ser un número entero.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtAñoFundacion.requestFocus();
            return null;
        }
        try {
            sociosAficionados = Integer.parseInt(sociosStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Número de socios' debe ser un número entero.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            txtSociosAficionados.requestFocus();
            return null;
        }

        return new Equipo(codigo, nombre, añoFundacion, lugarSede, estadio, sociosAficionados);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFormulario = new javax.swing.JPanel();
        jLabelCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabelAñoFundacion = new javax.swing.JLabel();
        txtAñoFundacion = new javax.swing.JTextField();
        jLabelLugarSede = new javax.swing.JLabel();
        txtLugarSede = new javax.swing.JTextField();
        jLabelEstadio = new javax.swing.JLabel();
        txtEstadio = new javax.swing.JTextField();
        jLabelSociosAficionados = new javax.swing.JLabel();
        txtSociosAficionados = new javax.swing.JTextField();
        jPanelBotones = new javax.swing.JPanel();
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
        jTableEquipos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Equipos");

        // Formulario
        jPanelFormulario.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Equipo"));

        jLabelCodigo.setText("Código:");
        jLabelNombre.setText("Nombre:");
        jLabelAñoFundacion.setText("Año Fundación:");
        jLabelLugarSede.setText("Lugar de Sede:");
        jLabelEstadio.setText("Estadio:");
        jLabelSociosAficionados.setText("Nº Socios:");

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(formLayout);
        formLayout.setAutoCreateGaps(true);
        formLayout.setAutoCreateContainerGaps(true);
        formLayout.setHorizontalGroup(
            formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCodigo)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelAñoFundacion)
                    .addComponent(jLabelLugarSede)
                    .addComponent(jLabelEstadio)
                    .addComponent(jLabelSociosAficionados))
                .addGroup(formLayout.createParallelGroup()
                    .addComponent(txtCodigo, 200, 200, 200)
                    .addComponent(txtNombre, 200, 200, 200)
                    .addComponent(txtAñoFundacion, 200, 200, 200)
                    .addComponent(txtLugarSede, 200, 200, 200)
                    .addComponent(txtEstadio, 200, 200, 200)
                    .addComponent(txtSociosAficionados, 200, 200, 200))
        );
        formLayout.setVerticalGroup(
            formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigo).addComponent(txtCodigo))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre).addComponent(txtNombre))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAñoFundacion).addComponent(txtAñoFundacion))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLugarSede).addComponent(txtLugarSede))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEstadio).addComponent(txtEstadio))
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSociosAficionados).addComponent(txtSociosAficionados))
        );

        // Tabla
        jTableEquipos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Código", "Nombre", "Año Fund.", "Sede", "Estadio", "Socios"}
        ) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        });
        jTableEquipos.getSelectionModel().addListSelectionListener(e -> { if (e.getValueIsAdjusting()) return;
            int fila = jTableEquipos.getSelectedRow();
            if (fila >= 0) {
                txtCodigo.setText(jTableEquipos.getValueAt(fila, 0).toString());
                txtNombre.setText(jTableEquipos.getValueAt(fila, 1).toString());
                txtAñoFundacion.setText(jTableEquipos.getValueAt(fila, 2).toString());
                txtLugarSede.setText(jTableEquipos.getValueAt(fila, 3).toString());
                txtEstadio.setText(jTableEquipos.getValueAt(fila, 4).toString());
                txtSociosAficionados.setText(jTableEquipos.getValueAt(fila, 5).toString());
            }
        });
        jScrollPane1.setViewportView(jTableEquipos);

        // Botones
        jButtonInsertar.setText("Insertar");
        jButtonInsertar.addActionListener(evt -> jButtonInsertarActionPerformed(evt));

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(evt -> jButtonActualizarActionPerformed(evt));

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(evt -> jButtonEliminarActionPerformed(evt));

        jButtonConsultar1.setText("Consultar uno");
        jButtonConsultar1.addActionListener(evt -> jButtonConsultar1ActionPerformed(evt));

        jButtonConsultarTodos.setText("Consultar todos");
        jButtonConsultarTodos.addActionListener(evt -> jButtonConsultarTodosActionPerformed(evt));

        jButtonExTXT.setText("Exportar TXT");
        jButtonExTXT.addActionListener(evt -> jButtonExTXTActionPerformed(evt));

        jButtonExCSV.setText("Exportar CSV");
        jButtonExCSV.addActionListener(evt -> jButtonExCSVActionPerformed(evt));

        jButtonExBIN.setText("Exportar BIN");
        jButtonExBIN.addActionListener(evt -> jButtonExBINActionPerformed(evt));

        jButtonExJSON.setText("Exportar JSON");
        jButtonExJSON.addActionListener(evt -> jButtonExJSONActionPerformed(evt));

        jButtonImpTXT.setText("Importar TXT");
        jButtonImpTXT.addActionListener(evt -> jButtonImpTXTActionPerformed(evt));

        jButtonImpCSV.setText("Importar CSV");
        jButtonImpCSV.addActionListener(evt -> jButtonImpCSVActionPerformed(evt));

        jButtoniImpBIN.setText("Importar BIN");
        jButtoniImpBIN.addActionListener(evt -> jButtoniImpBINActionPerformed(evt));

        jButtonImpJSON.setText("Importar JSON");
        jButtonImpJSON.addActionListener(evt -> jButtonImpJSONActionPerformed(evt));

        Volver.setText("Volver");
        Volver.addActionListener(evt -> VolverActionPerformed(evt));

        jPanelBotones.setLayout(new java.awt.GridLayout(0, 2, 5, 5));
        jPanelBotones.add(jButtonInsertar);
        jPanelBotones.add(jButtonActualizar);
        jPanelBotones.add(jButtonEliminar);
        jPanelBotones.add(jButtonConsultar1);
        jPanelBotones.add(jButtonConsultarTodos);
        jPanelBotones.add(new javax.swing.JLabel()); // spacer
        jPanelBotones.add(jButtonExTXT);
        jPanelBotones.add(jButtonImpTXT);
        jPanelBotones.add(jButtonExCSV);
        jPanelBotones.add(jButtonImpCSV);
        jPanelBotones.add(jButtonExBIN);
        jPanelBotones.add(jButtoniImpBIN);
        jPanelBotones.add(jButtonExJSON);
        jPanelBotones.add(jButtonImpJSON);
        jPanelBotones.add(Volver);

        // Layout principal
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(jPanelFormulario)
                    .addComponent(jPanelBotones))
                .addComponent(jScrollPane1, 400, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanelFormulario)
                    .addComponent(jPanelBotones))
                .addComponent(jScrollPane1)
        );

        pack();
        setSize(800, 500);
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void jButtonInsertarActionPerformed(java.awt.event.ActionEvent evt) {
        Equipo equipo = validarYConstruirEquipo();
        if (equipo == null) return;
        try {
            JPP_ProyectoFinal.equipoService.insertar(equipo);
            JOptionPane.showMessageDialog(this, "Equipo insertado correctamente.");
            limpiarCampos();
            cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        Equipo equipo = validarYConstruirEquipo();
        if (equipo == null) return;
        try {
            JPP_ProyectoFinal.equipoService.actualizar(equipo);
            JOptionPane.showMessageDialog(this, "Equipo actualizado correctamente.");
            limpiarCampos();
            cargarTabla();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validación: " + e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        String codigoStr = txtCodigo.getText().trim();
        if (codigoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce el código del equipo a eliminar.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Código' debe ser un número entero.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar el equipo con código " + codigo + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
        try {
            JPP_ProyectoFinal.equipoService.eliminar(codigo);
            JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.");
            limpiarCampos();
            cargarTabla();
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonConsultar1ActionPerformed(java.awt.event.ActionEvent evt) {
        String codigoStr = txtCodigo.getText().trim();
        if (codigoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce el código del equipo a consultar.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo 'Código' debe ser un número entero.",
                    "Error de validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Equipo equipo = JPP_ProyectoFinal.equipoService.consultar(codigo);
            txtNombre.setText(equipo.getNombre());
            txtAñoFundacion.setText(String.valueOf(equipo.getañoFundacion()));
            txtLugarSede.setText(equipo.getLugarSede());
            txtEstadio.setText(equipo.getEstadio());
            txtSociosAficionados.setText(String.valueOf(equipo.getSociosAficionados()));
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonConsultarTodosActionPerformed(java.awt.event.ActionEvent evt) {
        cargarTabla();
    }

    private void jButtonExTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.exportarTxt();
            JOptionPane.showMessageDialog(this, "Exportado a TXT correctamente.");
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonExCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.exportarCsv();
            JOptionPane.showMessageDialog(this, "Exportado a CSV correctamente.");
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonExBINActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.exportarBinario();
            JOptionPane.showMessageDialog(this, "Exportado a Binario correctamente.");
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonExJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.exportarJson();
            JOptionPane.showMessageDialog(this, "Exportado a JSON correctamente.");
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonImpTXTActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.importarTxt();
            JOptionPane.showMessageDialog(this, "Importado desde TXT correctamente.");
            cargarTabla();
        } catch (YaImportadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonImpCSVActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.importarCsv();
            JOptionPane.showMessageDialog(this, "Importado desde CSV correctamente.");
            cargarTabla();
        } catch (YaImportadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtoniImpBINActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.importarBinario();
            JOptionPane.showMessageDialog(this, "Importado desde Binario correctamente.");
            cargarTabla();
        } catch (YaImportadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error de clase: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonImpJSONActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JPP_ProyectoFinal.equipoService.importarJson();
            JOptionPane.showMessageDialog(this, "Importado desde JSON correctamente.");
            cargarTabla();
        } catch (YaImportadoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError | ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void VolverActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
    }

    // Variables declaration
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
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelAñoFundacion;
    private javax.swing.JLabel jLabelLugarSede;
    private javax.swing.JLabel jLabelEstadio;
    private javax.swing.JLabel jLabelSociosAficionados;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEquipos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtAñoFundacion;
    private javax.swing.JTextField txtLugarSede;
    private javax.swing.JTextField txtEstadio;
    private javax.swing.JTextField txtSociosAficionados;
    // End of variables declaration
}
