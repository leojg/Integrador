/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.ElementoNoEncontradoException;
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

    private int id;
    private String nom;
    private ArrayList<NodoBinario> colEstaciones;
    private Broker objB;
    Broker objBEstacionesLineas;

    public Linea() {
        Estacion objE = new Estacion();
        this.colEstaciones = new ArrayList<>();
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
        this.objBEstacionesLineas = FachadaBaseDeDatos.getInstance().obtenerBroker(this, objE);
    }

    public Linea(String nom, int id) {
        Estacion objE = new Estacion();
        this.nom = nom;
        this.id = id;
        this.colEstaciones = new ArrayList<>();
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
        this.objBEstacionesLineas = FachadaBaseDeDatos.getInstance().obtenerBroker(this, objE);
    }

    boolean AgregarEstacion(Estacion objE) {
        if (!tieneEstacion(objE)) {
            NodoBinario nb = new NodoBinario(objE, null, null);
            if (colEstaciones.isEmpty()) {
                colEstaciones.add(nb);
            } else {
                for (NodoBinario nb2 : this.colEstaciones) {
                    if (nb2.derecho == null) {
                        nb2.derecho = nb;
                        nb.izquierdo = nb2;
                        colEstaciones.add(nb);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean tieneEstacion(Estacion objE) {
        for (NodoBinario nb : this.colEstaciones) {
            Estacion e = (Estacion) nb.dato;
            if (e.getNom().equals(objE.getNom())) {
                return true;
            }
        }
        return false;

    }

    public void cargarEstacionesLineas() throws ElementoNoEncontradoException {
        for (Object o : objBEstacionesLineas.obtenerTodos(this.getId())) {
            NodoBinario nb = (NodoBinario) o;
            if (nb.dato != null) {
                Estacion objE = (Estacion) nb.dato;
                objE = EstacionAdmin.getInstance().getEstacion(objE.getId());
                nb.dato = objE;
                if (nb.izquierdo != null) {
                    Estacion ant = (Estacion) nb.izquierdo.dato;
                    ant = EstacionAdmin.getInstance().getEstacion(ant.getId());
                    nb.izquierdo.dato = ant;
                }
                if (nb.derecho != null) {
                    Estacion next = (Estacion) nb.derecho.dato;
                    next = EstacionAdmin.getInstance().getEstacion(next.getId());
                    nb.derecho.dato = next;
                }
                this.colEstaciones.add(nb);
            }
        }
    }

    ArrayList<NodoBinario> getColNodos() {
        return colEstaciones;
    }
    
    HashMap<String, Estacion> getColEstaciones() {
        HashMap<String, Estacion> colEst = new HashMap<>();
        for (NodoBinario nb : this.colEstaciones) {
            Estacion objE = (Estacion) nb.dato;
            colEst.put(objE.getNom(), objE);
        }
        return colEst;
    }

    boolean QuitarEstacion(Estacion objE) {
        for (NodoBinario nb : colEstaciones) {
            Estacion e = (Estacion) nb.dato;
            if (e.getNom().equals(objE.getNom())) {
                NodoBinario anterior = nb.izquierdo;
                NodoBinario posterior = nb.derecho;
                colEstaciones.remove(nb);
                anterior.derecho = posterior;
                posterior.izquierdo = anterior;
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        for (NodoBinario nb : this.colEstaciones) {
            this.objBEstacionesLineas.eliminar(nb, this);
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
