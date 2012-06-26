/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.FormatoLineaIncorrectoException;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class LineaAdmin {

    private HashMap<String, Linea> colLineas;
    private static LineaAdmin instance;

    private LineaAdmin() {
        colLineas = new HashMap<>();
    }

    public static LineaAdmin getInstance() {
        if (instance == null) {
            instance = new LineaAdmin();
            instance.cargarLineas();
        }
        return instance;
    }

    boolean altaLinea(Linea objL) {
        if (!this.colLineas.containsKey(objL.getNom())) {
            this.colLineas.put(objL.getNom(), objL);
            objL.guardar(objL);
            return true;
        }
        return false;
    }

    boolean bajaLinea(Linea objL) {
        if (this.colLineas.containsKey(objL.getNom())) {
            this.colLineas.remove(objL.getNom());
            objL.eliminar(objL);
            return true;
        }
        return false;
    }

    void cargarLineas() {
        Linea objL = new Linea();
        for (Object o : objL.obtenerTodos()) {
            objL = (Linea) o;
            this.colLineas.put(objL.getNom(), objL);
            objL.cargarEstacionesLineas();
        }
    }

    Linea crearLinea(String nom) throws FormatoLineaIncorrectoException {
        String a = nom.substring(0, 1);
        if (nom.length() > 3) {
            throw new FormatoLineaIncorrectoException();
        }
        try {
            Integer.parseInt(a);
            //tirar excepcion;
        } catch (NumberFormatException nfe) {
            try {
                Integer b = Integer.parseInt(nom.substring(1));
            } catch (NumberFormatException nfe2) {
             throw new FormatoLineaIncorrectoException();
            }
        }
        return new Linea(nom.toUpperCase());
    }

    Linea getLinea(String nom) {
        return this.colLineas.get(nom);
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
