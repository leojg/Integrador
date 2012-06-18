/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class LineaAdmin {

    private HashMap<String, Linea> colLineas;
    private static LineaAdmin instance = null;

    private LineaAdmin() {
        colLineas = new HashMap<>();
    }

    public static LineaAdmin getInstance() {
        if (instance == null) {
            instance = new LineaAdmin();
        }
        return instance;
    }

    boolean altaLinea(Linea objL, HashMap<String, Estacion> colEstaciones) {
        if (!this.colLineas.containsKey(objL.getNom())) {
            this.colLineas.put(objL.getNom(), objL);
            for (Estacion objE : colEstaciones.values()) {
                objL.AgregarEstacion(objE);
            }
            return true;
        }
        return false;
    }
    
    HashMap<String, Linea> getLineas() {
        return this.colLineas;
    }
    
    HashMap<String, Linea> getLineasEstacion(Estacion objE) {
        HashMap<String, Linea> colLineasEstacion = new HashMap<>();
        for (Linea objL : colLineas.values()) {
            if (objL.getColEstaciones().containsKey(objE.getNom())) {
                colLineasEstacion.put(objL.getNom(), objL);
            }
        }
        return colLineasEstacion;
    }
}
