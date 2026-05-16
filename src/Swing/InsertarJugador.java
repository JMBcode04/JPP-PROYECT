package Swing;

import Excepciones.ElDatoIntroducidoEsIncorrecto;
import Excepciones.SeHaProducidoUnError;
import Main.JPP_ProyectoFinal;
import Modelos.Jugador;
import javax.swing.JOptionPane;

/**
 * @author isard
 */
public class InsertarJugador extends javax.swing.JFrame {

    private Runnable onInsertado;

    public InsertarJugador() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public InsertarJugador(java.awt.Frame parent, boolean modal) {
        initComponents();
        setLocationRelativeTo(parent);
    }

    public InsertarJugador(java.awt.Frame parent, boolean modal, Runnable onInsertado) {
        this(parent, modal);
        this.onInsertado = onInsertado;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        NombreLBL       = new javax.swing.JLabel();
        NombreTXT       = new javax.swing.JTextField();
        NombreLBL1      = new javax.swing.JLabel();
        apellidotxt     = new javax.swing.JTextField();
        jLabelFecha     = new javax.swing.JLabel();
        jTxtFecha       = new javax.swing.JTextField();
        jLabelNacional  = new javax.swing.JLabel();
        jTxtNacional    = new javax.swing.JTextField();
        jButton1        = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Insertar Jugador");

        NombreLBL.setText("Nombre:");
        NombreLBL1.setText("Posicion:");
        jLabelFecha.setText("Fecha nacimiento (dd/mm/aaaa):");
        jLabelNacional.setText("Nacionalidad:");

        jTxtFecha.setToolTipText("Formato obligatorio: dd/mm/aaaa");

        NombreTXT.addActionListener(e -> apellidotxt.requestFocus());
        apellidotxt.addActionListener(e -> jTxtFecha.requestFocus());
        jTxtFecha.addActionListener(e -> jTxtNacional.requestFocus());
        jTxtNacional.addActionListener(e -> jButton1ActionPerformed(e));

        jButton1.setText("Insertar");
        jButton1.addActionListener(e -> jButton1ActionPerformed(e));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(NombreLBL).addComponent(NombreLBL1)
                .addComponent(jLabelFecha).addComponent(jLabelNacional))
            .addGroup(layout.createParallelGroup()
                .addComponent(NombreTXT, 180, 180, 180)
                .addComponent(apellidotxt, 180, 180, 180)
                .addComponent(jTxtFecha, 180, 180, 180)
                .addComponent(jTxtNacional, 180, 180, 180)
                .addComponent(jButton1))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(NombreLBL).addComponent(NombreTXT))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(NombreLBL1).addComponent(apellidotxt))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelFecha).addComponent(jTxtFecha))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabelNacional).addComponent(jTxtNacional))
            .addComponent(jButton1)
        );
        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String nombre      = NombreTXT.getText().trim();
        String posicion    = apellidotxt.getText().trim();
        String fecha       = jTxtFecha.getText().trim();
        String nacional    = jTxtNacional.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio.", "Error", JOptionPane.WARNING_MESSAGE);
            NombreTXT.requestFocus(); return;
        }
        if (posicion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La posicion no puede estar vacia.", "Error", JOptionPane.WARNING_MESSAGE);
            apellidotxt.requestFocus(); return;
        }
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/mm/aaaa.\nEjemplo: 15/06/1995", "Error", JOptionPane.WARNING_MESSAGE);
            jTxtFecha.requestFocus(); jTxtFecha.selectAll(); return;
        }
        if (nacional.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La nacionalidad no puede estar vacia.", "Error", JOptionPane.WARNING_MESSAGE);
            jTxtNacional.requestFocus(); return;
        }

        try {
            Jugador j = new Jugador(nombre, fecha, nacional, posicion);
            JPP_ProyectoFinal.jugadorService.insertar(j);
            JOptionPane.showMessageDialog(this,
                "Jugador '" + nombre + "' insertado con codigo " + j.getCodigo() + ".",
                "Exito", JOptionPane.INFORMATION_MESSAGE);
            NombreTXT.setText(""); apellidotxt.setText("");
            jTxtFecha.setText(""); jTxtNacional.setText("");
            NombreTXT.requestFocus();
            if (onInsertado != null) onInsertado.run();
        } catch (ElDatoIntroducidoEsIncorrecto e) {
            JOptionPane.showMessageDialog(this, "Error de validacion: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        } catch (SeHaProducidoUnError e) {
            JOptionPane.showMessageDialog(this, "Error al insertar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(InsertarJugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new InsertarJugador().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NombreLBL;
    private javax.swing.JLabel NombreLBL1;
    private javax.swing.JTextField NombreTXT;
    private javax.swing.JTextField apellidotxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelNacional;
    private javax.swing.JTextField jTxtFecha;
    private javax.swing.JTextField jTxtNacional;
    // End of variables declaration//GEN-END:variables
}
