/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.ElementoNoEncontradoException;
import exceptions.FormatoLineaIncorrectoException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class LineaAdmin {

    private HashMap<String, Linea> colLineas;
    private static LineaAdmin instance;
    private int numLinea = 0;
    
    private LineaAdmin() {
        colLineas = new HashMap<>();
    }

    public static LineaAdmin getInstance() {
        if (instance == null) {
            try {
                instance = new LineaAdmin();
                instance.cargarLineas();
            } catch (ElementoNoEncontradoException ex) {
                Logger.getLogger(LineaAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    void cargarLineas() throws ElementoNoEncontradoException {
        Linea objL = new Linea();
        for (Object o : objL.obtenerTodos(objL)) {
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
        return new Linea(nom.toUpperCase(), numLinea);
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
            if (!objL.tieneEstacion(objE)) {
                colLineasEstacion.put(objL.getNom(), objL);
            }
        }
        return colLineasEstacion;
    }

    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
       if (this.numLinea <= numLinea)
        this.numLinea = numLinea;
    }
    
}
