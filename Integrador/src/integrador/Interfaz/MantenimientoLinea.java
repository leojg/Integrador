package integrador.Interfaz;

import integrador.dominio.FachadaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class MantenimientoLinea extends Mantenimiento {

    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnMod;
    private JTable tableLin;
    int srow = -1;
    
    /**
     * Creates new form MantenimientoLinea
     */
    public MantenimientoLinea() {
        initComponents();
          this.setSize(super.getMaximumSize());
        this.btnAlta = super.getBtnAlta();
        this.btnBaja = super.getBtnBaja();
        this.btnMod = super.getBtnMod();
        this.tableLin = super.getTableItems();
        this.setComponents();

        btnMod.setVisible(false);

    }

    private void setComponents() {
        setBtnAlta();
        setBtnBaja();
        setTableLineas();
    }
    private void setTableLineas() {
        Utilitaria.cargarJTable(tableLin, "Linea",null);
    }
    private void setBtnAlta() {
        btnAlta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    FachadaInterfaz.altaLinea(txtNom.getText());
                    setTableLineas();
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(rootPane, "El codigo postal posee un formato incorrecto", null, WIDTH);
                }
            }
        });
    }

    private void setBtnBaja() {
        btnBaja.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    srow = tableLin.getSelectedRow();
                    FachadaInterfaz.bajaLinea(((String) tableLin.getValueAt(srow, 0)));
                    setTableLineas();
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(rootPane, "El codigo postal posee un formato incorrecto", null, WIDTH);
                }
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNom = new javax.swing.JTextField();
        lblNom = new javax.swing.JLabel();
        btnAsignarEst = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNom.setText("Nombre Linea");

        btnAsignarEst.setText("Asignar Estaciones");
        btnAsignarEst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarEstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblNom)
                .addGap(18, 18, 18)
                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnAsignarEst)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNom)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAsignarEst)))
                .addContainerGap(244, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAsignarEstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarEstActionPerformed
                    srow = tableLin.getSelectedRow();
        AsignarEstaciones frmAS = new AsignarEstaciones(FachadaInterfaz.getLinea((String) tableLin.getValueAt(srow, 0)));
        frmAS.setVisible(true);
        frmAS.setAlwaysOnTop(true);

    }//GEN-LAST:event_btnAsignarEstActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarEst;
    private javax.swing.JLabel lblNom;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}