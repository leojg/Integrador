/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador;

import integrador.dominio.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String ConvertirGCalendarString(GregorianCalendar cal) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String s = sdf.format(cal.getTime());
        return s;

    }

    public static String ConvertirGCalendarStringHora(GregorianCalendar cal) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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

    public static GregorianCalendar ConvertirStringGCalendarHora(String s) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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
    public static void setTreeLineas(JTree tree) {
        DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode("Lineas y Estaciones");
        DefaultTreeModel modelo = new DefaultTreeModel(nodoPadre);
        int contLinea = 0;


        for (Object l : objFI.getLineas()) {
            Object[] arrLin = (Object[]) l;
            DefaultMutableTreeNode nodoLinea = new DefaultMutableTreeNode(arrLin[0]);
            modelo.insertNodeInto(nodoLinea, nodoPadre, contLinea);
            contLinea++;
            int contEst = 0;
            for (Object o : objFI.getEstacionesLinea(arrLin[0].toString())) {
                Object[] arr = (Object[]) o;
                DefaultMutableTreeNode nodoEst = new DefaultMutableTreeNode(arr[0]);
                modelo.insertNodeInto(nodoEst, nodoLinea, contEst);
                contEst++;
            }

        }
        tree.setPreferredSize(null);
        tree.setModel(modelo);
        expand(tree, (TreePath) tree.getPathForRow(0));


    }

    /**
     * Permite que el jtree quede expandido siempre. Incluso cuando se realiza
     * una modificación
     *
     * @param tree
     * @param path
     */
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

    /**
     * Datos debe ser un array multidimensional. En el cual, cada "fila"
     * representa una fila a agregar a la jtable y cada columna un atributo de
     * esa fila. columnaOrdenadora Indica la columna que se debe emplear para
     * ordenar la tabla.
     *
     * @param table
     * @param datos
     * @param headers
     * @param columnaOrdenadora
     */
    public static void setJTable(JTable table, Object[][] datos, Object[] headers, int columnaOrdenadora) {
        DefaultTableModel Modelo = (DefaultTableModel) table.getModel();
        Modelo.setColumnIdentifiers(headers);
        Modelo.setRowCount(0);
        if (datos != null) {
            for (int r = 0; r < datos.length; r++) {
                Modelo.addRow(datos[r]);
            }

            if (columnaOrdenadora >= 0) {
                List<Object[]> lista = ((DefaultTableModel) table.getModel()).getDataVector();
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
                table.repaint();

            }
        }

    }
}