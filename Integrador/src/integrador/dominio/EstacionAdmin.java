/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

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
    private static EstacionAdmin instance = null;

    private EstacionAdmin() {
        this.colEstacion = new HashMap<>();
    }

    public static EstacionAdmin getInstance() {
        if (instance == null) {
            instance = new EstacionAdmin();
        }
        return instance;
    }

    boolean altaEstacion(Estacion objE) {
        if (!this.colEstacion.containsKey(objE.getNom())) {
            this.colEstacion.put(objE.getNom(), objE);
            return true;
        }
        return false;
    }

    boolean bajaEstacion(Estacion objE) {
        if (this.colEstacion.containsKey(objE.getNom()) && objLA.getLineasEstacion(objE).size() <= 0) {
            this.colEstacion.remove(objE.getNom());
            return true;
        }
        return false;
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
