/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador;

import integrador.dominio.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrador
 */
public class Utilitaria {

    static FachadaInterfaz objFI = new FachadaInterfaz();

    public static String ConvertirGCalendarString(GregorianCalendar cal) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s = sdf.format(cal.getTime());
        return s;

    }

    public static GregorianCalendar ConvertirStringGCalendar(String s) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        GregorianCalendar gc = new GregorianCalendar();
        d = sdf.parse(s);
        gc.setTime(d);
        return gc;

    }

    public static GregorianCalendar ConvertirCalendar(Calendar c) throws ParseException {
        GregorianCalendar fecha = (GregorianCalendar) c;
        String fstr = ConvertirGCalendarString(fecha);
        GregorianCalendar fecha2 = ConvertirStringGCalendar(fstr);
        return fecha2;
    }

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
    public static void cargarJTable(JTable table, String id, Object[] datos) throws ParseException {
        if ("Estacion".equals(id)) {

            setTableEstaciones(table, datos);
        } else if ("Linea".equals(id)) {
            setTableLineas(table, datos);
        } else if ("Convenio".equals(id)) {
            setTableConvenios(table, datos);
        } else if ("Usuario".equals(id)) {
            setTableUsuarios(table, datos);
        }
    }

    public static void cargarJTable(JTable table, String id) throws ParseException {
        if ("Estacion".equals(id)) {
            setTableEstaciones(table, null);
        } else if ("Linea".equals(id)) {
            setTableLineas(table, null);
        } else if ("Convenio".equals(id)) {
            setTableConvenios(table, null);
        } else if ("Usuario".equals(id)) {
            setTableUsuarios(table, null);
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
            for (Estacion ObjE : objFI.getEstaciones().values()) {
                Object[] row = {ObjE.getNom(), ObjE.getCp()};
                Modelo.addRow(row);
            }
        }
        if (Modelo.getRowCount() <= 0) {
            Object[] row = {"No Existen Datos Disponibles"};
            Modelo.addRow(row);
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
            for (Linea ObjL : objFI.getLineas().values()) {
                Object[] row = {ObjL.getNom()};
                Modelo.addRow(row);
            }
        }
        if (Modelo.getRowCount() <= 0) {
            Object[] row = {"No Existen Datos Disponibles"};
            Modelo.addRow(row);
        }
    }

    private static void setTableConvenios(JTable table, Object[] datos) throws ParseException {
        DefaultTableModel Modelo = (DefaultTableModel) table.getModel();
        Object[] cn = {"Tipo", "Nombre", "Tipo de Pago", "Valor", "Fecha de Inicio"};
        Modelo.setColumnIdentifiers(cn);
        Modelo.setRowCount(0);
        if (datos != null) {
            for (Object Obj0 : datos) {
                Convenio ObjC = (Convenio) Obj0;
                String tp;
                if (ObjC.getTipoPago() == false) {
                    tp = "Mensual";
                } else {
                    tp = "Por Compra";
                }
                Object[] row = {ObjC.getTipo(), ObjC.getNom(), tp, ObjC.getValor(), ConvertirGCalendarString(ObjC.getFechaIni())};
                Modelo.addRow(row);
            }
        } else {
            for (Convenio ObjC : objFI.getConvenios().values()) {
                String tp;
                if (ObjC.getTipoPago() == false) {
                    tp = "Mensual";
                } else {
                    tp = "Por Compra";
                }

                Object[] row = {ObjC.getTipo(), ObjC.getNom(), tp, ObjC.getValor(), ConvertirGCalendarString(ObjC.getFechaIni())};
                Modelo.addRow(row);
            }
        }
        if (Modelo.getRowCount() <= 0) {
            Object[] row = {"No Existen Datos Disponibles"};
            Modelo.addRow(row);
        }
    }

        private static void setTableUsuarios(JTable table, Object[] datos) {
        DefaultTableModel Modelo = (DefaultTableModel) table.getModel();
        Object[] cn = {"CI", "Nombre", "Convenio","Telefono","EMail","Fecha de Nacimiento"};
        Modelo.setColumnIdentifiers(cn);
        Modelo.setRowCount(0);
         if (datos != null) {
            for (Object Obj0 : datos) {
                Usuario objU = (Usuario) Obj0;
                String convenioNom;
                if (objU.getConvenio() == null) {
                    convenioNom = "Sin Convenio";
                } else {
                    convenioNom = objU.getConvenio().getNom();
                }
                Object[] row = {objU.getCI(), objU.getNom(), convenioNom,  objU.getTel(), 
                    objU.getMail(),  ConvertirGCalendarString(objU.getFechaNac())};
                Modelo.addRow(row);
            }
        } else {
            for (Usuario objU : objFI.getUsuarios().values()) {
                String convenioNom;
                if (objU.getConvenio() == null) {
                    convenioNom = "Sin Convenio";
                } else {
                    convenioNom = objU.getConvenio().getNom();
                }
                Object[] row = {objU.getCI(), objU.getNom(), convenioNom, objU.getCP(), objU.getDir(),
                objU.getBarrio(), objU.getTel(), objU.getMail(),  ConvertirGCalendarString(objU.getFechaNac())};
               Modelo.addRow(row);
            }
        }
        if (Modelo.getRowCount() <= 0) {
            Object[] row = {"No Existen Datos Disponibles"};
            Modelo.addRow(row);
        }
    }
    
    public static void setTreeLineas(JTree tree) {
        DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode("Lineas y Estaciones");
        DefaultTreeModel modelo = new DefaultTreeModel(nodoPadre);
        int contLinea = 0;


        for (Linea objL : objFI.getLineas().values()) {
            DefaultMutableTreeNode nodoLinea = new DefaultMutableTreeNode(objL.getNom());
            modelo.insertNodeInto(nodoLinea, nodoPadre, contLinea);
            contLinea++;
            int contEst = 0;
            for (Estacion objE : objFI.getEstacionesLinea(objL.getNom()).values()) {
                DefaultMutableTreeNode nodoEst = new DefaultMutableTreeNode(objE.getNom());
                modelo.insertNodeInto(nodoEst, nodoLinea, contEst);
                contEst++;
            }

        }
        tree.setModel(modelo);
        expand(tree, (TreePath) tree.getPathForRow(0));


    }

    private static void expand(JTree tree, TreePath path) {
        TreeNode node = (TreeNode) path.getLastPathComponent();
        if (node.getChildCount() > 0) {
            Enumeration e = node.children();
            while (e.hasMoreElements()) {
                TreeNode n = (TreeNode) e.nextElement();
                expand(tree, path.pathByAddingChild(n));
            }
        }
        tree.expandPath(path);
    }
}
