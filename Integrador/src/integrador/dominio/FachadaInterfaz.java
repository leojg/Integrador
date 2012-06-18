/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.HashMap;

/**
 * Clase encargada de establecer el acceso por parte de la interfaz a la capa
 * logica
 *
 * @author Administrador
 */
public class FachadaInterfaz {

    //***************** Estaciones *************************//
    private static EstacionAdmin objEA = EstacionAdmin.getInstance();

    public static boolean altaEstacion(String nom, Integer cp) {
        return objEA.altaEstacion(crearEstacion(nom, cp));
    }

    public static boolean bajaEstacion(String nom, Integer cp) {
        return objEA.bajaEstacion(crearEstacion(nom, cp));
    }

    /**
     * *
     * Crea una estación
     *
     * @param nom
     * @param cp
     * @return Estacion
     */
    private static Estacion crearEstacion(String nom, Integer cp) {
        nom = nom.toLowerCase();
        return new Estacion(nom, cp);
    }

    public static HashMap<String, Estacion> getEstaciones() {
        return objEA.getEstaciones();
    }
    //************** Lineas ****************************//
    private static LineaAdmin objLA = LineaAdmin.getInstance();

    public static boolean altaLinea(String nom, HashMap<String, Estacion> colEstaciones) {
        if (objEA.existenEstaciones(colEstaciones)) {
            return objLA.altaLinea(crearLinea(nom), colEstaciones);
        }
        return false;
    }

    private static Linea crearLinea(String nom) {
        String a = nom.substring(0, 1);
        try {
            Integer.parseInt(a);
            //tirar excepcion;
        } catch (NumberFormatException nfe) {
            try {
                Integer b = Integer.parseInt(nom.substring(1));
            } catch (NumberFormatException nfe2) {
                //nombre mal formateado;
            }
        }
        return new Linea(nom);
    }
}
