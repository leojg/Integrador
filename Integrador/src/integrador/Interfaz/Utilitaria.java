/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.Interfaz;

import integrador.dominio.Estacion;
import integrador.dominio.FachadaInterfaz;
import integrador.dominio.Linea;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Administrador
 */
public class Utilitaria {

    /**
     * *
     * Este metodo se encarga de setear una tabla segun lo que indique el
     * parametro id. EJ: Si id es "Estacion", cargará la tabla indicada con
     * estaciones. Datos es un parametro auxiliar. Si se desea cargar la tabla
     * con un array en particular
     *
     * @param table
     * @param id
     * @param datos
     */
    public static void cargarJTable(JTable table, String id, Object[] datos) {
        if ("Estacion".equals(id)) {

            setTableEstaciones(table, datos);
        } else if ("Linea".equals(id)) {
            setTableLineas(table, datos);
        }
    }

    public static void cargarJTable(JTable table, String id) {
        if ("Estacion".equals(id)) {
            setTableEstaciones(table, null);
        } else if ("Linea".equals(id)) {
            setTableLineas(table, null);
        }
    }

    private static void setTableEstaciones(JTable table, Object[] datos) {
        DefaultTableModel Modelo = (DefaultTableModel) table.getModel();
        Object[] cn = {"Nombre", "Codigo Postal"};
        Modelo.setColumnIdentifiers(cn);
        Modelo.setRowCount(0);
        if (datos != null) {
            for (Object Obj0 : datos) {
                Estacion ObjE = (Estacion) Obj0;
                Object[] row = {ObjE.getNom(), ObjE.getCp()};
                Modelo.addRow(row);
            }
        } else {
            for (Estacion ObjE : FachadaInterfaz.getEstaciones().values()) {
                Object[] row = {ObjE.getNom(), ObjE.getCp()};
                Modelo.addRow(row);
            }
        }
    }

    private static void setTableLineas(JTable table, Object[] datos) {
        DefaultTableModel Modelo = (DefaultTableModel) table.getModel();
        Object[] cn = {"Nombre de Linea"};
        Modelo.setColumnIdentifiers(cn);
        Modelo.setRowCount(0);
        if (datos != null) {
            for (Object Obj0 : datos) {
                Linea ObjL = (Linea) Obj0;
                Object[] row = {ObjL.getNom()};
                Modelo.addRow(row);
            }
        } else {
            for (Linea ObjL : FachadaInterfaz.getLineas().values()) {
                Object[] row = {ObjL.getNom()};
                Modelo.addRow(row);
            }
        }
    }

    public static void setTreeLineas(JTree tree) {
        DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode("Lineas y Estaciones");
        DefaultTreeModel modelo = new DefaultTreeModel(nodoPadre);
        int contLinea = 0;
        
        
        for (Linea objL : FachadaInterfaz.getLineas().values()) {
            DefaultMutableTreeNode nodoLinea = new DefaultMutableTreeNode(objL.getNom());
            modelo.insertNodeInto(nodoLinea, nodoPadre, contLinea);
            contLinea++;
            int contEst = 0;
            for (Estacion objE : FachadaInterfaz.getEstacionesLinea(objL.getNom()).values()) {
                DefaultMutableTreeNode nodoEst = new DefaultMutableTreeNode(objE.getNom());
                modelo.insertNodeInto(nodoEst, nodoLinea, contEst);
                contEst ++;
            }
        }
     
        tree.setModel(modelo);
    }
}
