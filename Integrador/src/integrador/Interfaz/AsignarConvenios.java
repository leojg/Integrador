/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import integrador.Utilitaria;
import integrador.dominio.FachadaInterfaz;
import integrador.dominio.Usuario;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class AsignarConvenios extends javax.swing.JFrame {

    private Usuario objU;
    private FachadaInterfaz objFI;
    private int srow = -1;

    /**
     * Creates new form AsignarConvenios
     */
    public AsignarConvenios(Usuario objU, FachadaInterfaz objFI) {
        try {
            initComponents();
            this.objU = objU;
            this.objFI = objFI;
            this.lblNom.setText(objU.getNom());
            this.lblCI.setText(objU.getCI().toString());
            if (objU.getConvenio() == null) {
                this.lblConv.setText("Sin Convenio");
            } else {
                this.lblConv.setText(objU.getConvenio().getNom());
            }
            Utilitaria.cargarJTable(tableCon, "Convenio");
        } catch (ParseException ex) {
            Logger.getLogger(AsignarConvenios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableCon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnAddCon = new javax.swing.JButton();
        lblNom = new javax.swing.JLabel();
        lblCI = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblConv = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableCon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableCon);

        jLabel1.setText("Usuario:");

        jLabel2.setText("Convenio Actual:");

        btnAddCon.setText("Aceptar");
        btnAddCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddConActionPerformed(evt);
            }
        });

        lblNom.setText("jLabel3");

        lblCI.setText("jLabel3");

        jLabel5.setText("CI:");

        lblConv.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lblConv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddCon, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNom)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCI)
                        .addGap(27, 141, Short.MAX_VALUE))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNom)
                    .addComponent(jLabel5)
                    .addComponent(lblCI))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblConv)
                    .addComponent(btnAddCon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddConActionPerformed
        try {
            srow = tableCon.getSelectedRow();
            if (srow != -1) {
                objFI.modUsrConv((Integer) tableCon.getValueAt(srow, 0), objU.getCI());
                JOptionPane.showMessageDialog(rootPane, "Operacion Exitosa");
            }
            this.setVisible(false);
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(AsignarConvenios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAddConActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCI;
    private javax.swing.JLabel lblConv;
    private javax.swing.JLabel lblNom;
    private javax.swing.JTable tableCon;
    // End of variables declaration//GEN-END:variables
}