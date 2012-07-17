/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import exceptions.ElementoNoEncontradoException;
import exceptions.EstacionTieneLineaException;
import exceptions.NoExisteCPException;
import exceptions.NombreRepetidoException;
import integrador.Utilitaria;
import integrador.dominio.CodigoPostal;
import integrador.dominio.Estacion;
import integrador.dominio.FachadaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.text.ParseException;
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
public class MantenimientoEstacion extends Mantenimiento {

    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnMod;
    private JTable tableEst;
    int srow = -1;

    /**
     * Creates new form MantenimientoEstacion
     */
    public MantenimientoEstacion() throws ParseException {
        initComponents();
        //this.setSize(super.getWidth(), super.getHeight());
        this.setSize(super.getMaximumSize());
        this.btnAlta = super.getBtnAlta();
        this.btnBaja = super.getBtnBaja();
        this.btnMod = super.getBtnMod();
        this.tableEst = super.getTableItems();
        this.setComponents();
        btnMod.setVisible(false);
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o.getClass() == FachadaInterfaz.class && o1.equals("Estacion")) {
            try {
                setTableEstaciones();
            } catch (ParseException ex) {
                Logger.getLogger(MantenimientoEstacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
   public void setComponents(){
        try {
            setBtnAlta();
            setBtnBaja();
            setTableEstaciones();
            for (CodigoPostal objCP : this.objFI.getCPs().values()) {
                this.comboCP.addItem(objCP.getCp());
            }
        } catch (ParseException ex) {
            Logger.getLogger(MantenimientoEstacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTableEstaciones() throws ParseException {
        //  Utilitaria.cargarJTable(tableEst, "Estacion", null);
        Object[] headers = {"Nombre", "Codigo Postal"};
        Utilitaria.setJTable(tableEst, objFI.getEstaciones(), headers,0);
    }

    private void setBtnAlta() {
        btnAlta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if ("".equals(txtNom.getText())) {
                        throw new NullPointerException();
                    }
                    objFI.altaEstacion(txtNom.getText(), Integer.parseInt(comboCP.getSelectedItem().toString()));

                    JOptionPane.showMessageDialog(lblCP, "Operación Exitosa");
                    try {
                        setTableEstaciones();
                    } catch (ParseException ex) {
                        Logger.getLogger(MantenimientoEstacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (NoExisteCPException | NombreRepetidoException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } catch (NumberFormatException ex) {
                    if ("".equals(comboCP.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(rootPane, "No se ha ingresado el codigo postal", null, WIDTH);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "El codigo postal posee un formato incorrecto", null, WIDTH);
                    }
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
                    srow = tableEst.getSelectedRow();
                    objFI.bajaEstacion(((String) tableEst.getValueAt(srow, 0)), ((Integer) tableEst.getValueAt(srow, 1)));
                    JOptionPane.showMessageDialog(lblCP, "Operación Exitosa");
                    setTableEstaciones();
                } catch (NoSuchObjectException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Seleccione una Estacion para continuar");
                } catch (EstacionTieneLineaException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error Inesperado. Vuelva a repetir la operación");
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
        lblCP = new javax.swing.JLabel();
        btnVerLineas = new javax.swing.JButton();
        comboCP = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblNom.setText("Nombre Estacion");

        lblCP.setText("Código Postal");

        btnVerLineas.setText("Ver Lineas Asociadas");
        btnVerLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerLineasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNom)
                            .addComponent(lblCP))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                            .addComponent(comboCP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnVerLineas))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNom)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCP)
                    .addComponent(comboCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnVerLineas)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerLineasActionPerformed
        if (tableEst.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una Estacion para continuar");
        } else {
            try {
                srow = tableEst.getSelectedRow();
                LineasAsociadas frmLA = new LineasAsociadas(objFI.getEstacion((String) tableEst.getValueAt(srow, 0)), objFI);
                frmLA.setVisible(true);
            } catch (ElementoNoEncontradoException ex) {
                              JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(MantenimientoEstacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnVerLineasActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerLineas;
    private javax.swing.JComboBox comboCP;
    private javax.swing.JLabel lblCP;
    private javax.swing.JLabel lblNom;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables
}
