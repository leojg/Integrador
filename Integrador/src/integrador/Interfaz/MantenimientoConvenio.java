/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import integrador.Utilitaria;
import integrador.dominio.FachadaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Administrador
 */
public class MantenimientoConvenio extends Mantenimiento {

    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnMod;
    private JTable tableCon;
    int srow = -1;

    /**
     * Creates new form MantenimientoConvenio
     */
    public MantenimientoConvenio() throws ParseException {
        initComponents();
        this.btnAlta = super.getBtnAlta();
        this.btnBaja = super.getBtnBaja();
        this.btnMod = super.getBtnMod();
        this.tableCon = super.getTableItems();
        this.setComponents();

    }

    @Override
    public void update(Observable o, Object o1) {
        if (o.getClass() == FachadaInterfaz.class && o1.equals("Convenio")) {
            try {
                setTableConvenios();
            } catch (ParseException ex) {
                Logger.getLogger(MantenimientoConvenio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setComponents() throws ParseException {
        setBtnAlta();
        setBtnBaja();
        setBtnMod();
        setTableConvenios();
    }

    private void setTableConvenios() throws ParseException {
        Utilitaria.cargarJTable(tableCon, "Convenio", null);
    }

    private void setBtnAlta() {
        btnAlta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if ("".equals(txtNom.getText())) {
                        throw new NullPointerException();
                    }
                    boolean tp = true;
                    if (rbMens.isSelected()) {
                        tp = false;
                    } else if (rbTick.isSelected()) {
                        tp = true;
                    }
                    objFI.altaConvenio(Integer.parseInt(txtTipo.getText()), txtNom.getText(), Utilitaria.ConvertirCalendar(calendarIni.getCalendar()), Integer.parseInt(txtValor.getText()), tp);
                    JOptionPane.showMessageDialog(rootPane, "Operación Exitosa");
                    setTableConvenios();
                } catch (ParseException ex) {
                    Logger.getLogger(MantenimientoConvenio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(rootPane, "No se ha ingresado nombre de la estación");
                }
            }
        });
    }

    private void setBtnBaja() {
        btnBaja.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    srow = tableCon.getSelectedRow();
                    boolean tp = true;
                    if ("Mensual".equals(tableCon.getValueAt(srow, 2).toString())) {
                        tp = false;
                    } else if ("Por Compra".equals(tableCon.getValueAt(srow, 2).toString())) {
                        tp = true;
                    }

                    GregorianCalendar calendar = Utilitaria.ConvertirStringGCalendar((String) tableCon.getValueAt(srow, 4));
                    objFI.bajaConvenio((Integer) tableCon.getValueAt(srow, 0), tableCon.getValueAt(srow, 1).toString(),
                            calendar,
                            (Integer) tableCon.getValueAt(srow, 3), tp);
                    JOptionPane.showMessageDialog(rootPane, "Operación Exitosa");
                    setTableConvenios();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Seleccione una Estacion para continuar");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error Inesperado. Vuelva a repetir la operación");
                }
            }
        });
    }

    private void setBtnMod() {
        btnMod.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    srow = tableCon.getSelectedRow();

                    objFI.modConvenio(Integer.parseInt(tableCon.getValueAt(srow, 0).toString()), Integer.parseInt(txtValor.getText()));
                    JOptionPane.showMessageDialog(rootPane, "Operación Exitosa");
                    setTableConvenios();
                } catch (ParseException ex) {
                    Logger.getLogger(MantenimientoConvenio.class.getName()).log(Level.SEVERE, null, ex);
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

        rbMens = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        calendarIni = new org.freixas.jcalendar.JCalendarCombo();
        jLabel3 = new javax.swing.JLabel();
        rbTick = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        txtValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rbMens.setText("Mensual");
        rbMens.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbMensItemStateChanged(evt);
            }
        });

        jLabel1.setText("Tipo");

        jLabel2.setText("Nombre");

        jLabel6.setText("Fecha de Inicio");

        jLabel3.setText("Forma de Pago");

        rbTick.setText("Por Ticket");
        rbTick.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbTickItemStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        jLabel4.setText("Importe");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTipo)
                            .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbTick)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbMens))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calendarIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 348, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rbMens)
                    .addComponent(rbTick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(calendarIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void rbMensItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbMensItemStateChanged
        if (rbMens.isSelected()) {
            rbTick.setSelected(false);
        }
    }//GEN-LAST:event_rbMensItemStateChanged

    private void rbTickItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbTickItemStateChanged
        if (rbTick.isSelected()) {
            rbMens.setSelected(false);
        }
    }//GEN-LAST:event_rbTickItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.freixas.jcalendar.JCalendarCombo calendarIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbMens;
    private javax.swing.JRadioButton rbTick;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
