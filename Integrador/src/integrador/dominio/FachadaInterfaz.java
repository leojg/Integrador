/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.NombreRepetidoException;
import java.rmi.NoSuchObjectException;
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

    public static boolean altaEstacion(String nom, Integer cp) throws NombreRepetidoException {
        return objEA.altaEstacion(crearEstacion(nom, cp));
    }

    public static boolean bajaEstacion(String nom, Integer cp) throws NoSuchObjectException {
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

    public static Estacion getEstacion(String nom) {
        return objEA.getEstacion(nom);
    }

    public static HashMap<String, Estacion> getEstaciones() {
        return objEA.getEstaciones();
    }

    public static HashMap<String, Estacion> getEstacionesLinea(String nom) {
        return objLA.getLinea(nom).getColEstaciones();
    }

    public static HashMap<String, Estacion> getEstacionesNoEstanEnLinea(String nom) {
        return objEA.getEstacionesNoEstanEnLinea(objLA.getLinea(nom));
    }
//    public static HashMap<String, Estacion> getEstacionesLinea(String nomLinea) {
//   
//    } 
    //************** Lineas ****************************//
    private static LineaAdmin objLA = LineaAdmin.getInstance();

    public static boolean altaLinea(String nom) {
        return objLA.altaLinea(crearLinea(nom));
    }

    public static boolean agregarEstacionALinea(String nomLinea, String nomEst) {
        Linea objL = objLA.getLinea(nomLinea);
        Estacion objE = objEA.getEstacion(nomEst);
        return objL.AgregarEstacion(objE);
    }

    public static boolean bajaLinea(String nom) {
        return objLA.bajaLinea(crearLinea(nom));
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

    public static Linea getLinea(String nom) {
        return objLA.getLinea(nom);
    }

    public static HashMap<String, Linea> getLineas() {
        return objLA.getLineas();
    }

    public static HashMap<String, Linea> getLineasEsacion(String nom) {
        return objLA.getLineasEstacion(objEA.getEstacion(nom));
    }

    public static boolean quitarEstacionDeLinea(String nomLinea, String nomEst) {
        Linea objL = objLA.getLinea(nomLinea);
        Estacion objE = objEA.getEstacion(nomEst);
        return objL.QuitarEstacion(objE);
    }
}
