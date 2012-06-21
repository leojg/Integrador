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

    private LineaAdmin objLA = LineaAdmin.getInstance();
    private HashMap<String, Estacion> colEstacion;
    private static EstacionAdmin instance;

    private EstacionAdmin() {
       this.colEstacion = new HashMap<>();
    }

    public static EstacionAdmin getInstance() {
        if (instance == null) {
            System.out.print("Una sola vez aca adentro lpm");
            instance = new EstacionAdmin();
            instance.cargarEstaciones();
        }
        return instance;
    }

    boolean altaEstacion(Estacion objE) throws NombreRepetidoException {
        if (!this.colEstacion.containsKey(objE.getNom())) {
            this.colEstacion.put(objE.getNom(), objE);
            objE.guardar(objE);
            return true;
        }
        throw new NombreRepetidoException();
    }

    boolean bajaEstacion(Estacion objE) throws NoSuchObjectException {
        if (this.colEstacion.containsKey(objE.getNom()) && objLA.getLineasEstacion(objE).size() <= 0) {
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
        }
    }

    boolean existenEstaciones(HashMap<String, Estacion> colEst) {
        for (Estacion objE : colEst.values()) {
            if (!this.colEstacion.containsKey(objE.getNom())) {
                return false;
            }
        }
        return true;
    }
    
    Estacion getEstacion(String nom) {
        return this.colEstacion.get(nom);
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
}
