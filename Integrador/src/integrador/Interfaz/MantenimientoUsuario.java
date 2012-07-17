/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import exceptions.ElementoNoEncontradoException;
import exceptions.IDRepetidoException;
import exceptions.NoExisteCPException;
import exceptions.NombreRepetidoException;
import integrador.Utilitaria;
import integrador.dominio.CodigoPostal;
import integrador.dominio.FachadaInterfaz;
import integrador.dominio.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Administrador
 */
public class MantenimientoUsuario extends Mantenimiento {

    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnMod;
    private JTable tableUsr;
    int srow = -1;

    /**
     * Creates new form MantenimientoUsuario
     */
    public MantenimientoUsuario() {
            initComponents();
            this.btnAlta = super.getBtnAlta();
            this.btnBaja = super.getBtnBaja();
            this.btnMod = super.getBtnMod();
            this.tableUsr = super.getTableItems();
            setComponents();
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o.getClass() == FachadaInterfaz.class && o1.equals("Usuario")) {
            try {
                setTableUsuarios();
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
            setBtnMod();
            setTableUsuarios();
            for (CodigoPostal objCP : this.objFI.getCPs().values())  {
               this.comboCP.addItem(objCP.getCp()); 
            }
        } catch (ParseException ex) {
            Logger.getLogger(MantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTableUsuarios() throws ParseException {
        Object[] header = {"CI", "Nombre", "Convenio", "Barrio", "Direccion", "Telefono",
            "Código Postal", "EMail", "Fecha de Registro", "Fecha de Nacimiento"};
        Utilitaria.setJTable(tableUsr, objFI.getUsuarios(), header,-1);
        setTableUsr();
    }

    private void setBtnAlta() {
        btnAlta.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    objFI.altaUsuario(Integer.parseInt(txtCI.getText()), txtNom.getText(), Utilitaria.ConvertirCalendar(calFNac.getCalendar()),
                            txtDir.getText(), txtBarrio.getText(), Integer.parseInt(comboCP.getSelectedItem().toString()), txtMail.getText(),
                            Integer.parseInt(txtTel.getText()));
                    JOptionPane.showMessageDialog(rootPane, "Operación Exitosa");
                    limpiarControles();
                } catch (NoExisteCPException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } catch (IDRepetidoException | NombreRepetidoException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                } catch (ParseException ex) {
                    Logger.getLogger(MantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Uno o mas de los datos requeridos no ha sido ingresado.");
                } catch (NumberFormatException ex) {
                    if (!Utilitaria.isNumeric(txtCI.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "El CI de Usuario debe ser un Número");
                    } else if (!Utilitaria.isNumeric(comboCP.getSelectedItem().toString())) {
                        JOptionPane.showMessageDialog(rootPane, "El Codigo Postal debe ser un Número");
                    } else if (!Utilitaria.isNumeric(txtTel.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "El Telefono debe ser un Número");
                    }
                }
            }
        });
    }

    private void setBtnBaja() {
        btnBaja.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    srow = tableUsr.getSelectedRow();
                    objFI.bajaUsuario((Integer) tableUsr.getValueAt(srow, 0));
                    JOptionPane.showMessageDialog(rootPane, "Operación Exitosa");
                    limpiarControles();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Seleccione un Usuario para continuar");
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
                    objFI.modUsuario(Integer.parseInt(txtCI.getText()), txtNom.getText(), Utilitaria.ConvertirCalendar(calFNac.getCalendar()),
                            txtDir.getText(), txtBarrio.getText(), Integer.parseInt(comboCP.getSelectedItem().toString()), txtMail.getText(),
                            Integer.parseInt(txtTel.getText()));
                    JOptionPane.showMessageDialog(rootPane, "Operación Exitosa");
                    limpiarControles();


                } catch (NoExisteCPException ex) {
                    Logger.getLogger(MantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Uno o mas de los datos requeridos no ha sido ingresado.");
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Uno o mas de los datos requeridos no ha sido ingresado.");
                }
            }
        });

    }

    private void setTableUsr() {
        ListSelectionModel lsm;
        tableUsr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);/*
         * lo de que solamente se pueda seleccionar una fila.
         */
        lsm = tableUsr.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableUsr.getSelectedRow() >= 0) {
                    try {
                        srow = tableUsr.getSelectedRow();
                        Usuario objU = objFI.getUsuario((Integer) tableUsr.getValueAt(srow, 0));
                        txtCI.setText(objU.getCI().toString());
                        txtNom.setText(objU.getNom());
                        comboCP.setSelectedItem(objU.getCP().toString());
                        txtTel.setText(objU.getTel().toString());
                        txtMail.setText(objU.getMail());
                        txtBarrio.setText(objU.getBarrio());
                        txtDir.setText(objU.getDir());
                        calFNac.setDate(objU.getFechaNac().getTime());
                    } catch (ElementoNoEncontradoException ex) {
                        Logger.getLogger(MantenimientoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    txtCI.setEditable(true);
                    txtCI.setText("");
                    txtNom.setText("");
                    txtTel.setText("");
                    txtMail.setText("");
                    txtBarrio.setText("");
                    txtDir.setText("");
                    calFNac.setDate(Calendar.getInstance().getTime());
                }
            }
        });

    }

    private void limpiarControles() {
        this.tableUsr.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCI = new javax.swing.JTextField();
        calFNac = new org.freixas.jcalendar.JCalendarCombo();
        txtNom = new javax.swing.JTextField();
        txtDir = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtBarrio = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        btnConvenioUsuario = new javax.swing.JButton();
        comboCP = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setText("C.I.:");

        jLabel2.setText("Nombre");

        jLabel3.setText("Telefono");

        jLabel4.setText("Direccion");

        jLabel5.setText("Barrio");

        jLabel6.setText("Fecha de Nacimiento");

        jLabel7.setText("Mail");

        jLabel8.setText("Codigo Postal");

        btnConvenioUsuario.setText("Asignar Convenio");
        btnConvenioUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvenioUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(btnConvenioUsuario))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel1)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCI, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboCP, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(calFNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(339, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(calFNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnConvenioUsuario)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    }//GEN-LAST:event_formMouseClicked

    private void btnConvenioUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvenioUsuarioActionPerformed
        srow = tableUsr.getSelectedRow();
        if (srow != -1) {
            try {
                AsignarConvenios frmAC = new AsignarConvenios(objFI.getUsuario((Integer) tableUsr.getValueAt(srow, 0)), objFI);
                frmAC.setVisible(true);
            } catch (ElementoNoEncontradoException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un Usuario primero.");
        }
    }//GEN-LAST:event_btnConvenioUsuarioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConvenioUsuario;
    private org.freixas.jcalendar.JCalendarCombo calFNac;
    private javax.swing.JComboBox comboCP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtBarrio;
    private javax.swing.JTextField txtCI;
    private javax.swing.JTextField txtDir;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
