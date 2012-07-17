/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.*;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;

/**
 * Se encarga de gestionar las estaciones y las funcionalidades relacionadas a
 * ellas
 *
 * @author Administrador
 */
public class EstacionAdmin {

    private int numEst;
    private LineaAdmin objLA = LineaAdmin.getInstance();
    private HashMap<String, Estacion> colEstacion;
    private static EstacionAdmin instance;

    private EstacionAdmin() {
        this.colEstacion = new HashMap<>();
    }

    public static EstacionAdmin getInstance() {
        if (instance == null) {
            instance = new EstacionAdmin();
            instance.cargarEstaciones();
        }
        return instance;
    }

    boolean altaEstacion(Estacion objE) throws NombreRepetidoException {
        if (!this.colEstacion.containsKey(objE.getNom())) {
            this.colEstacion.put(objE.getNom(), objE);
            objE.guardar(objE);
            setNumEst(objE.getId() + 1);
            return true;
        }
        throw new NombreRepetidoException();
    }

    boolean bajaEstacion(Estacion objE) throws NoSuchObjectException, EstacionTieneLineaException {
        if (this.colEstacion.containsKey(objE.getNom())) {
            if (objLA.getLineasEstacion(objE).size() > 0) {
                throw new EstacionTieneLineaException();
            }
            this.colEstacion.remove(objE.getNom());
            objE.eliminar(objE);
            return true;
        }
        throw new NoSuchObjectException("No existe una estación con ese nombre.");
    }

    void cargarEstaciones() {
        Estacion objE = new Estacion();
        for (Object o : objE.obtenerTodos()) {
            Estacion e = (Estacion) o;
            this.colEstacion.put(e.getNom(), e);
            setNumEst(e.getId() + 1);
        }
    }

    /**
     * *
     * Crea una estación
     *
     * @param nom
     * @param cp
     * @return Estacion
     */
    Estacion crearEstacion(String nom, Integer cp) throws NoExisteCPException {
        nom = nom.toLowerCase();
        if (CPAdmin.getInstance().existeCP(cp) == true) {
            return new Estacion(nom, cp, numEst);
        }
        throw new NoExisteCPException();
    }

    Estacion getEstacion(String nom) throws ElementoNoEncontradoException {
        if (this.colEstacion.containsKey(nom)) {
            return this.colEstacion.get(nom);
        }
        throw new ElementoNoEncontradoException("No se ha encontrado una estacion coincidente");
    }

    Estacion getEstacion(Integer id) throws ElementoNoEncontradoException {
        for (Estacion objE : this.colEstacion.values()) {
            if (objE.getId() == id) {
                return objE;
            }
        }
        throw new ElementoNoEncontradoException();
    }

    HashMap<String, Estacion> getEstaciones() {
        return this.colEstacion;
    }

    HashMap<String, Estacion> getEstacionesNoEstanEnLinea(Linea objL) {
        HashMap<String, Estacion> colEstacionesSinLinea = new HashMap<>();
        for (Estacion objE : colEstacion.values()) {
             if (!objL.getColEstaciones().containsKey(objE.getNom())) {
                    colEstacionesSinLinea.put(objE.getNom(), objE);
                }
            }
        return colEstacionesSinLinea;
    }

    HashMap<String, Estacion> getEstacionesCercanas(Usuario objU) {
        HashMap<String, Estacion> colEst = new HashMap<>();
        for (Estacion objE : this.colEstacion.values()) {
            int cp = objU.getCP();
            if (cp == objE.getCp()) {
                colEst.put(objE.getNom(), objE);
            }
        }
        return colEst;
    }

    public int getNumEst() {
        return numEst;
    }

    public void setNumEst(int numEst) {
        if (this.numEst <= numEst) {
            this.numEst = numEst;
        }
    }
}
