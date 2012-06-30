/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import integrador.persistencia.Broker;
import integrador.persistencia.FachadaBaseDeDatos;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta clase representa las lineas o recorridos del metro. Una linea es un
 * conjunto de estaciones
 *
 * @author Administrador
 */
public class Linea implements IPersistente {

    private String nom;
    private HashMap<String, Estacion> colEstaciones;
    private Broker objB;
    Broker objBEstacionesLineas;

    public Linea() {
        Estacion objE = new Estacion();
        this.colEstaciones = new HashMap<>();
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
        this.objBEstacionesLineas = FachadaBaseDeDatos.getInstance().obtenerBroker(this, objE);
    }

    public Linea(String nom) {
        Estacion objE = new Estacion();
        this.nom = nom;
        this.colEstaciones = new HashMap<>();
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
                this.objBEstacionesLineas = FachadaBaseDeDatos.getInstance().obtenerBroker(this, objE);
    }

    boolean AgregarEstacion(Estacion objE) {
        if (!this.colEstaciones.containsKey(objE.getNom())) {
            this.colEstaciones.put(objE.getNom(), objE);
            this.objBEstacionesLineas.guardar(objE, this);
            return true;
        }
        return false;
    }
    
    public void cargarEstacionesLineas() {
        for (Object o : objBEstacionesLineas.obtenerTodos(this.getNom())) {
            Estacion e = (Estacion) o;
            if (e.getNom() != null) {
            e = EstacionAdmin.getInstance().getEstacion(e.getNom());
            this.colEstaciones.put(e.getNom(), e);
            }
        }
    }

    HashMap<String, Estacion> getColEstaciones() {
        return colEstaciones;
    }

    boolean QuitarEstacion(Estacion objE) {
        if (this.colEstaciones.containsKey(objE.getNom())) {
            this.colEstaciones.remove(objE.getNom());
            this.objBEstacionesLineas.eliminar(objE, this);
            return true;
        }
        return false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Linea: " + nom;
    }

    @Override
    public void guardar(Object arg) {
        this.objB.guardar(arg, null);
    }

    @Override
    public void modifcar(Object arg) {
        this.objB.modificar(arg, null);
    }

    @Override
    public void eliminar(Object arg) {
        this.objB.eliminar(arg, null);
        for (Estacion objE : this.colEstaciones.values())  {
                    this.objBEstacionesLineas.eliminar(objE, this);
        }
    }

    @Override
    public ArrayList obtenerTodos() {
        return this.objB.obtenerTodos();
    }

    @Override
    public ArrayList obtenerTodos(Object aux) {
        return this.objB.obtenerTodos(aux);
    }
}
