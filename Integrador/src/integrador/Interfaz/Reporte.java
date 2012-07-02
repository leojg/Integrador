/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import integrador.Utilitaria;
import integrador.dominio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Reporte extends Mantenimiento {

    private JButton btnAlta;
    private JButton btnBaja;
    private JButton btnMod;
    private JTable tableReporte;
    private MessageFormat header;
    private MessageFormat footer;
    int srow = -1;
    Object[] headersEst = {"Nombre", "Codigo Postal"};
    Object[] headerUsrGasto = {"CI", "Nombre", "Edad", "Tipo de Convenio", "Gasto"};
    Object[] headerLineaEstiones = {"Nombre Linea", "Nombre Estacion", "Codigo Postal"};
    Object[] headerUsr = {"CI", "Nombre", "Convenio", "Barrio", "Direccion", "Telefono",
        "Código Postal", "EMail", "Fecha de Registro", "Fecha de Nacimiento"};
    Object[] headerCon = {"Tipo", "Nombre", "Tipo de Pago", "Valor", "Fecha de Inicio"};

    /**
     * Creates new form Reporte
     */
    public Reporte() {
        initComponents();
        this.btnAlta = super.getBtnAlta();
        this.btnBaja = super.getBtnBaja();
        this.btnMod = super.getBtnMod();
        this.tableReporte = super.getTableItems();
        this.btnBaja.setVisible(false);
        this.btnMod.setVisible(false);
        this.btnAlta.setVisible(false);
        setComponents();
    }

    @Override
    public void update(Observable o, Object o1) {
        if (o.getClass() == FachadaInterfaz.class && o1.equals("Compra")) {
        }
    }

    private void setComponents() {
        this.lblParam1.setVisible(false);
        this.lblParam2.setVisible(false);
        this.txtParam1.setVisible(false);
        this.txtParam2.setVisible(false);
        this.calFecha1.setVisible(false);
        this.calFecha2.setVisible(false);
        this.lblEMaxPromo.setVisible(false);
        this.txtEdadPromo.setVisible(false);
        this.txtParam1.setText("");
        this.txtParam2.setText("");
        this.txtEdadPromo.setText("");
        header = new MessageFormat("");
        footer = new MessageFormat("");

        switch (cboxReporte.getSelectedItem().toString()) {
            case "Usuarios Por Edad":
                this.lblParam1.setText("Edad Minima");
                this.lblParam2.setText("Edad Maxima");
                this.lblParam1.setVisible(true);
                this.lblParam2.setVisible(true);
                this.txtParam1.setVisible(true);
                this.txtParam2.setVisible(true);
                break;
            case "Lineas y sus Estaciones":
                break;
            case "Listado de Estaciones":
                break;
            case "Consulta de Usuario":
                this.lblParam1.setText("Ingrese CI");
                this.txtParam1.setVisible(true);
                this.lblParam1.setVisible(true);
                break;
            case "Consulta de Estacion":
                this.lblParam1.setText("Ingrese Nombre");
                this.txtParam1.setVisible(true);
                this.lblParam1.setVisible(true);
                break;
            case "Usuarios con más Consumo":
                this.lblParam1.setText("Inicio");
                this.lblParam2.setText("Fin");
                this.lblParam1.setVisible(true);
                this.lblParam2.setVisible(true);
                this.calFecha1.setVisible(true);
                this.calFecha2.setVisible(true);
                break;
            case "Consulta de Promocion":
                this.lblParam1.setText("Dias Registrado");
                this.lblParam2.setText("Cantidad Maxima de Usuarios");
                this.lblEMaxPromo.setVisible(true);
                this.lblParam1.setVisible(true);
                this.lblParam2.setVisible(true);
                this.txtParam1.setVisible(true);
                this.txtParam2.setVisible(true);
                this.txtEdadPromo.setVisible(true);
                break;
            case "Convenios Antiguos":
                this.lblParam1.setText("Año de Referencia");
                this.lblParam1.setVisible(true);
                this.txtParam1.setVisible(true);
                break;
            case "Listado de Estaciones Cercanas":
                this.lblParam1.setText("Ingrese CI");
                this.lblParam1.setVisible(true);
                this.txtParam1.setVisible(true);
                break;
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

        jLabel1 = new javax.swing.JLabel();
        cboxReporte = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        lblParam1 = new javax.swing.JLabel();
        lblParam2 = new javax.swing.JLabel();
        txtParam2 = new javax.swing.JTextField();
        txtParam1 = new javax.swing.JTextField();
        calFecha2 = new org.freixas.jcalendar.JCalendarCombo();
        calFecha1 = new org.freixas.jcalendar.JCalendarCombo();
        lblEMaxPromo = new javax.swing.JLabel();
        txtEdadPromo = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Reportes Disponibles");

        cboxReporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta de Usuario", "Usuarios Por Edad", "Usuarios con más Consumo", "Listado de Estaciones Cercanas", "Listado de Estaciones", "Lineas y sus Estaciones", "Consulta de Estacion", "Consulta de Promocion", "Convenios Antiguos" }));
        cboxReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxReporteActionPerformed(evt);
            }
        });

        lblParam1.setText("jLabel2");

        lblParam2.setText("jLabel2");

        calFecha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calFecha2ActionPerformed(evt);
            }
        });

        lblEMaxPromo.setText("Edad Maxima");

        btnPrint.setText("Imprimir");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblParam1)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtParam1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(lblParam2)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEMaxPromo)
                .addGap(6, 6, 6)
                .addComponent(txtEdadPromo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPrint))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblParam1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblParam2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblEMaxPromo))
                            .addComponent(txtEdadPromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPrint))))
        );

        btnReporte.setText("Generar Reporte");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboxReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReporte))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cboxReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReporte))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calFecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calFecha2ActionPerformed
    }//GEN-LAST:event_calFecha2ActionPerformed

    private void cboxReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxReporteActionPerformed
        setComponents();
    }//GEN-LAST:event_cboxReporteActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        switch (cboxReporte.getSelectedItem().toString()) {
            case "Usuarios Por Edad":
                if ("".equals(txtParam1.getText()) || "".equals(txtParam2.getText())) {
                    //JOptionPane.showMessageDialog(rootPane, "Falta ingresar uno o ambos datos requeridos");
                } else {
                    header = new MessageFormat("Listado de Usuarios por Edad");
                    footer = new MessageFormat("Edad acotada entre:" + txtParam1.getText() + " y " + txtParam2.getText());
                    Object[][] datos = new Object[objFI.getUsuariosPorEdad(Integer.parseInt(txtParam1.getText()), Integer.parseInt(txtParam2.getText())).size()][5];
                    int cont = 0;
                    for (Object o : objFI.getUsuariosPorEdad(Integer.parseInt(txtParam1.getText()),
                            Integer.parseInt(txtParam2.getText()))) {
                        Object[] arr = (Object[]) o;
                        datos[cont][0] = arr[0];
                        datos[cont][1] = arr[1];
                        datos[cont][2] = arr[2];
                        datos[cont][3] = arr[3];
                        datos[cont][4] = arr[4];
                        cont++;
                    }
                    Utilitaria.asd(tableReporte, datos, headerUsrGasto);

                }
                break;
            case "Lineas y sus Estaciones":

//                    header = new MessageFormat("Listado de Lineas y sus Estaciones");
//                    footer = new MessageFormat("");

//                    Object[][] datos = new Object[objFI.getEstaciones().size()][3];
//                    int cont = 0;
//                    for (Linea ObjL : objFI.getLineas().values()) {
//                        Object[] row = {ObjL.getNom()};
//                        datos[cont][0] = ObjL;
//                        cont++;
//                        for (Estacion objE : objFI.getEstacionesLinea(ObjL.getNom()).values()) {
//                            Object[] row2 = {"", objE.getNom(), objE.getCp()};
//                            datos[cont][0] = row2;
//                            cont++;
//                        }
//                        cont++;
//                    }
//                Utilitaria.asd(tableReporte, datos, headerTable);

                break;
            case "Listado de Estaciones":
                try {
                    header = new MessageFormat("Listado de Estaciones");
                    footer = new MessageFormat("");
                    Utilitaria.cargarJTable(tableReporte, "Estacion");
                } catch (ParseException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Consulta de Usuario":
                try {
                    header = new MessageFormat("Consulta de Usuario segun CI:" + txtParam1.getText());
                    footer = new MessageFormat("");
                    Object[] arr = {objFI.getUsuario(Integer.parseInt(txtParam1.getText()))};
                    Utilitaria.cargarJTable(tableReporte, "Usuario", arr);
                } catch (ParseException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(rootPane, "No existen usuarios con esa CI");
                }
                break;
            case "Consulta de Estacion":
                header = new MessageFormat("Listado de Lineas que atraviezan una Estacion");
                footer = new MessageFormat("Estación:" + txtParam1.getText());
                Object[] heardLinea = {"Nombre de Linea"};
                Utilitaria.asd(tableReporte, objFI.getLineasEsacion(txtParam1.getText()), heardLinea);
                break;
            case "Usuarios con más Consumo":
                header = new MessageFormat("Listado de Usuarios con mayor Consumo");
                footer = new MessageFormat("Periodo entre" + calFecha1.getDate() + " - " + calFecha2.getDate());
                Utilitaria.asd(tableReporte, objFI.getUsuariosMasGasto(calFecha1.getDate(), calFecha2.getDate()), headerUsr);
                break;
            case "Consulta de Promocion":
                header = new MessageFormat("Listado de Usuarios aplicables a promocion");
                footer = new MessageFormat("Edad Maxima: " + txtEdadPromo.getText() + " - Tiempo Registrado: "
                        + txtParam1.getText() + " - Cantidad de Usuarios Maxima:" + txtParam2.getText());
                Utilitaria.asd(tableReporte, objFI.getUsuariosParaPromocion(Integer.parseInt(txtEdadPromo.getText()),
                        Integer.parseInt(txtParam1.getText()), Integer.parseInt(txtParam2.getText())), headerUsr);
                break;
            case "Convenios Antiguos":
                header = new MessageFormat("Listado de Convenios anteriores a:" + txtParam1.getText());
                footer = new MessageFormat("");
                Utilitaria.asd(tableReporte, objFI.getConveniosVigentes(Integer.parseInt(txtParam1.getText())), headerCon);
                break;
            case "Listado de Estaciones Cercanas":
                header = new MessageFormat("Listado de Estaciones Cercanas");
                footer = new MessageFormat("CI de Usuario:" + txtParam1.getText());
                Utilitaria.asd(tableReporte, objFI.getEstacionesCercanas(Integer.parseInt(txtParam1.getText())), headersEst);
                break;
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            this.tableReporte.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPrintActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReporte;
    private org.freixas.jcalendar.JCalendarCombo calFecha1;
    private org.freixas.jcalendar.JCalendarCombo calFecha2;
    private javax.swing.JComboBox cboxReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEMaxPromo;
    private javax.swing.JLabel lblParam1;
    private javax.swing.JLabel lblParam2;
    private javax.swing.JTextField txtEdadPromo;
    private javax.swing.JTextField txtParam1;
    private javax.swing.JTextField txtParam2;
    // End of variables declaration//GEN-END:variables
}
