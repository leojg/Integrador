/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.ElementoNoEncontradoException;
import exceptions.EstacionTieneLineaException;
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
    private Broker objB;
    private HashMap<Integer, ParadaLinea> colEstaciones;
    private int proximaPosicion = 1;
    Broker objBEstacionesLineas;

    public Linea() {
        Estacion objE = new Estacion();
        this.colEstaciones = new HashMap<>();
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
        this.objBEstacionesLineas = FachadaBaseDeDatos.getInstance().obtenerBroker(this, objE);
    }

    public Linea(String nom, int id) {
        Estacion objE = new Estacion();
        this.nom = nom;
        this.id = id;
        this.colEstaciones = new HashMap<>();
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
        this.objBEstacionesLineas = FachadaBaseDeDatos.getInstance().obtenerBroker(this, objE);
    }

    public void cargarEstacionesLineas() throws ElementoNoEncontradoException {
        for (Object o : objBEstacionesLineas.obtenerTodos(this.getId())) {
            ParadaLinea objP = (ParadaLinea) o;
            if (objP.getObjE() != null) {
                Estacion objE = EstacionAdmin.getInstance().getEstacion(objP.getObjE().getId());
                objP.setObjE(objE);
                this.colEstaciones.put(objP.getPosicion(), objP);
                proximaPosicion = objP.getPosicion() + 1;
            }
        }
    }

    HashMap<Integer, ParadaLinea> getColParadas() {
        return this.colEstaciones;
    }

    HashMap<String, Estacion> getColEstaciones() {
        HashMap<String, Estacion> arr = new HashMap<>(this.colEstaciones.size());
        for (ParadaLinea objP : this.colEstaciones.values()) {
            arr.put(objP.getObjE().getNom(), objP.getObjE());
        }
        return arr;
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
        for (ParadaLinea objP : this.colEstaciones.values()) {
            this.objBEstacionesLineas.eliminar(objP, this);
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
    //Recorrido de Linea

    public boolean tieneEstacion(Estacion objE) {
        for (ParadaLinea objP : colEstaciones.values()) {
            if (objE.getNom().equals(objP.getObjE().getNom())) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarEstacion(Estacion objE) throws EstacionTieneLineaException {
        if (!tieneEstacion(objE)) {
            ParadaLinea objP = new ParadaLinea(this.colEstaciones.size() + 1, objE);
            colEstaciones.put(objP.getPosicion(), objP);
            proximaPosicion = this.colEstaciones.size() + 1;
            this.objBEstacionesLineas.guardar(objP, this);
            return true;
        }
        throw new EstacionTieneLineaException();

    }

    public boolean quitarEstacion(Estacion objE) throws ElementoNoEncontradoException {
        if (tieneEstacion(objE)) {
            int poss = getPosicion(objE);
            colEstaciones.remove(poss);
            this.objBEstacionesLineas.eliminar(objE, this);
            for (int i = poss + 1; i <= colEstaciones.size() + 1;) {
                ParadaLinea objP = this.colEstaciones.get(i);
                colEstaciones.remove(i);
                objP.setPosicion(i - 1);
                colEstaciones.put(objP.getPosicion(), objP);
                this.objBEstacionesLineas.modificar(objP, this);
                i++;
            }
            proximaPosicion -= 1;
            return true;
        }
        throw new ElementoNoEncontradoException();
    }

    public Estacion getEstacion(Estacion objE) {
        return null;
    }

    public int getPosicion(Estacion objE) {
        for (ParadaLinea objP : this.colEstaciones.values()) {
            if (objP.getObjE().getNom().equals(objE.getNom())) {
                return objP.getPosicion();
            }
        }
        return -1;
    }
}
