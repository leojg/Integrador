/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import integrador.Utilitaria;
import integrador.dominio.Convenio;
import integrador.dominio.FachadaInterfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    int srow = -1;

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
        switch (cboxReporte.getSelectedItem().toString()) {
            case "Usuarios Por Edad":
                this.lblParam1.setText("Edad Minima");
                this.lblParam2.setText("Edad Maxima");
                this.lblParam1.setVisible(true);
                this.lblParam2.setVisible(true);
                this.txtParam1.setVisible(true);
                this.txtParam2.setVisible(true);
                setBtnReporteUsrPorEdad();
                break;
            case "Lineas y sus Estaciones":
                setBtnReporteLinEst();
                break;
            case "Listado de Estaciones":
                setBtnReporteEst();
                break;
            case "Consulta de Usuario":
                this.lblParam1.setText("Ingrese CI");
                this.txtParam1.setVisible(true);
                this.lblParam1.setVisible(true);
                setBtnReporteUsr();
                break;
            case "Consulta de Estacion":
                this.lblParam1.setText("Ingrese Nombre");
                this.txtParam1.setVisible(true);
                this.lblParam1.setVisible(true);
                setBtnReporteEstLin();
                break;
            case "Usuarios con más Consumo":
                this.lblParam1.setText("Inicio");
                this.lblParam2.setText("Fin");
                this.lblParam1.setVisible(true);
                this.lblParam2.setVisible(true);
                this.calFecha1.setVisible(true);
                this.calFecha2.setVisible(true);
                setBtnReporteUsrPorConsumo();
                break;
            case "Consulta de Promocion":
                setBtnReportePromociones();
                break;
            case "Convenios Antiguos":
                this.lblParam1.setText("Año de Referencia");
                this.lblParam1.setVisible(true);
                this.txtParam1.setVisible(true);
                setBtnReportesrConveniosAntiguos();
                break;
            case "Listado de Estaciones Cercanas":
                this.lblParam1.setText("Ingrese CI");
                this.lblParam1.setVisible(true);
                this.txtParam1.setVisible(true);
                setBtnReporteEstCercanas();
                break;
        }
    }

    private void setBtnReporteUsrPorEdad() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if ("".equals(txtParam1.getText()) || "".equals(txtParam2.getText())) {
                    //JOptionPane.showMessageDialog(rootPane, "Falta ingresar uno o ambos datos requeridos");
                } else {
                    setJtableUsrPorEdad();
                }
            }
        });
    }

    private void setBtnReporteLinEst() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void setBtnReporteEst() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Utilitaria.cargarJTable(tableReporte, "Estacion");
                } catch (ParseException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setBtnReporteUsr() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Object[] arr = {objFI.getUsuario(Integer.parseInt(txtParam1.getText()))};
                    Utilitaria.cargarJTable(tableReporte, "Usuario", arr);
                } catch (ParseException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setBtnReporteEstLin() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Utilitaria.cargarJTable(tableReporte, "Linea", objFI.getLineasEsacion(txtParam1.getText()).values().toArray());
                } catch (ParseException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setBtnReporteUsrPorConsumo() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Utilitaria.cargarJTable(tableReporte, "Usuario", objFI.getUsuariosMasGasto(calFecha1.getDate(), calFecha2.getDate()).values().toArray());
                } catch (ParseException ex) {
                    Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void setBtnReportePromociones() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void setBtnReportesrConveniosAntiguos() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void setBtnReporteEstCercanas() {
        btnReporte.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
    }

    private void setJtableUsrPorEdad() {
        ArrayList datos = objFI.getUsuariosPorEdad(Integer.parseInt(txtParam1.getText()), Integer.parseInt(txtParam2.getText()));
        DefaultTableModel Modelo = (DefaultTableModel) tableReporte.getModel();
        Object[] cn = {"CI", "Nombre", "Edad", "Tipo de Convenio", "Gasto"};
        Modelo.setColumnIdentifiers(cn);
        Modelo.setRowCount(0);
        for (Object o : datos) {
            Object[] row;
            Object[] usrArr = (Object[]) o;
            Modelo.addRow(usrArr);
        }
        //obtenemos el DefaultTableModel de la tabla y guardamos  
//su vector de datos en un ArrayList de tipo Object  
        List<Object[]> lista = ((DefaultTableModel) tableReporte.getModel()).getDataVector();
//ordenamos la lista  
        Collections.sort(lista, new Comparator() {

            public int compare(Object o1, Object o2) {
                //el objeto o1 y o2 representan una fila de la tabla dentro de la lista  
                //casteamos los objetos o1 y o2 para poder guardarlos dentro de otro ArrayList de tipo Object  
                List<Object> fila1 = (List<Object>) o1;
                List<Object> fila2 = (List<Object>) o2;
                //ahora obtenemos los valores de de la fila  
                //en este caso obtenemos el valor de la columna Nombre  
                //dentro de la lista la columna nombre es el indice 1  
                //por eso hacemos un fila1.get(1)  
                String nombre1 = String.valueOf(fila1.get(1));
                String nombre2 = String.valueOf(fila2.get(1));
                return nombre1.compareToIgnoreCase(nombre2);
            }
        });
//el ultimo paso es repintar la tabla  
        tableReporte.repaint();

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
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Reportes Disponibles");

        cboxReporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuarios Por Edad", "Lineas y sus Estaciones", "Listado de Estaciones", "Consulta de Usuario", "Consulta de Estacion", "Usuarios con más Consumo", "Consulta de Promociones", "Convenios Antiguos", "Listado de Estaciones Cercanas" }));
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
                        .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(txtParam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        btnReporte.setText("Generar Reporte");

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
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calFecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calFecha2ActionPerformed
    }//GEN-LAST:event_calFecha2ActionPerformed

    private void cboxReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxReporteActionPerformed
        setComponents();
    }//GEN-LAST:event_cboxReporteActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReporte;
    private org.freixas.jcalendar.JCalendarCombo calFecha1;
    private org.freixas.jcalendar.JCalendarCombo calFecha2;
    private javax.swing.JComboBox cboxReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblParam1;
    private javax.swing.JLabel lblParam2;
    private javax.swing.JTextField txtParam1;
    private javax.swing.JTextField txtParam2;
    // End of variables declaration//GEN-END:variables
}
