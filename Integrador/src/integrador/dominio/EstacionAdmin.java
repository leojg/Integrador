/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.HashMap;

/**
 * Se encarga de gestionar las estaciones y las funcionalidades relacionadas a ellas
 * @author Administrador
 */
public class EstacionAdmin {
    
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
        if (this.colEstacion.containsKey(objE.getNom())) {
            this.colEstacion.remove(objE.getNom());
            return true;
        }
        return false;
    }
    
    HashMap<String,Estacion> getEstaciones() {
        return this.colEstacion;
    }
    
    
    
    
}
