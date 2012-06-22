/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.EstacionTieneLineaException;
import exceptions.NombreRepetidoException;
import java.rmi.NoSuchObjectException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;

/**
 * Clase encargada de establecer el acceso por parte de la interfaz a la capa
 * logica
 *
 * @author Administrador
 */
public class FachadaInterfaz extends Observable {

    //***************** Estaciones *************************//
    private EstacionAdmin objEA = EstacionAdmin.getInstance();

    public boolean altaEstacion(String nom, Integer cp) throws NombreRepetidoException {
        if (objEA.altaEstacion(crearEstacion(nom, cp))) {
            setChanged();
            notifyObservers("Estacion");
            return true;
        }
        return false;
    }

    public boolean bajaEstacion(String nom, Integer cp) throws NoSuchObjectException, EstacionTieneLineaException {
        if (objEA.bajaEstacion(crearEstacion(nom, cp))) {
            setChanged();
            notifyObservers("Estacion");
            return true;
        }
        return false;
    }

    /**
     * *
     * Crea una estación
     *
     * @param nom
     * @param cp
     * @return Estacion
     */
    private Estacion crearEstacion(String nom, Integer cp) {
        nom = nom.toLowerCase();
        return new Estacion(nom, cp);
    }

    public Estacion getEstacion(String nom) {
        return objEA.getEstacion(nom);
    }

    public HashMap<String, Estacion> getEstaciones() {
        return objEA.getEstaciones();
    }

    public HashMap<String, Estacion> getEstacionesLinea(String nom) {
        return objLA.getLinea(nom).getColEstaciones();
    }

    public HashMap<String, Estacion> getEstacionesNoEstanEnLinea(String nom) {
        return objEA.getEstacionesNoEstanEnLinea(objLA.getLinea(nom));
    }
//    public   HashMap<String, Estacion> getEstacionesLinea(String nomLinea) {
//   
//    } 
    //************** Lineas ****************************//
    private LineaAdmin objLA = LineaAdmin.getInstance();

    public boolean altaLinea(String nom) {

        if (objLA.altaLinea(crearLinea(nom))) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;
    }

    public boolean agregarEstacionALinea(String nomLinea, String nomEst) {
        Linea objL = objLA.getLinea(nomLinea);
        Estacion objE = objEA.getEstacion(nomEst);
        if (objL.AgregarEstacion(objE)) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;

    }

    public boolean bajaLinea(String nom) {
        if (objLA.bajaLinea(crearLinea(nom))) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;
    }

    private Linea crearLinea(String nom) {
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

    public Linea getLinea(String nom) {
        return objLA.getLinea(nom);
    }

    public HashMap<String, Linea> getLineas() {
        return objLA.getLineas();
    }

    public HashMap<String, Linea> getLineasEsacion(String nom) {
        return objLA.getLineasEstacion(objEA.getEstacion(nom));
    }

    public boolean quitarEstacionDeLinea(String nomLinea, String nomEst) {
        Linea objL = objLA.getLinea(nomLinea);
        Estacion objE = objEA.getEstacion(nomEst);
        if (objL.QuitarEstacion(objE)) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;
    }
    //************** Convenios ****************************//
    private ConvenioAdmin objCA = ConvenioAdmin.getInstance();

    public boolean altaConvenio(Integer tipo, String nom, GregorianCalendar fecha, Integer valor, boolean tipopago) {
        if (objCA.altaConvenio(crearConvenio(tipo, nom, fecha, valor, tipopago))) {
            setChanged();
            notifyObservers("Convenio");
            return true;
        }
        return false;
    }

    public boolean bajaConvenio(Integer tipo, String nom, GregorianCalendar fecha, Integer valor, boolean tipopago) {
        if (objCA.bajaConvenio(crearConvenio(tipo, nom, fecha, valor, tipopago))) {
            setChanged();
            notifyObservers("Convenio");
            return true;
        }
        return false;
    }

    private Convenio crearConvenio(Integer tipo, String nom, GregorianCalendar fecha, Integer valor, boolean tipopago) {
        return new Convenio(tipo, nom, fecha, valor, tipopago);
    }

    public HashMap<Integer, Convenio> getConvenios() {
        return objCA.getConvenios();
    }
    
    public boolean modConvenio(Integer tipo, Integer precioNuevo) {
        return this.objCA.modConvenio(tipo, precioNuevo);
    }
}
