/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.HashMap;

/**
 * Esta clase representa las lineas o recorridos del metro. Una linea es un
 * conjunto de estaciones
 *
 * @author Administrador
 */
public class Linea {

    private String nom;
    private HashMap<String, Estacion> colEstaciones;

    public Linea(String nom) {
        this.nom = nom;
        this.colEstaciones = new HashMap<>();
    }

    boolean AgregarEstacion(Estacion objE) {
        if (!this.colEstaciones.containsKey(objE.getNom())) {
            this.colEstaciones.put(objE.getNom(), objE);
            return true;
        }
        return false;
    }
    
    HashMap<String, Estacion> getColEstaciones() {
        return colEstaciones;
    }

    
    boolean QuitarEstacion(Estacion objE) {
        if (this.colEstaciones.containsKey(objE.getNom())) {
            this.colEstaciones.remove(objE.getNom());
            return true;
        }
        return false;
    }
    
    public String getNom() {
        return nom;
    }

    void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Linea: " + nom;
    }
}
