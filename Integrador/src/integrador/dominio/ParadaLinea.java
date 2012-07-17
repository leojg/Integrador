/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import integrador.persistencia.Broker;
import integrador.persistencia.FachadaBaseDeDatos;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class ParadaLinea implements IPersistente {

    private int posicion;
    private Estacion objE;
    private Broker objB;

    public ParadaLinea(int posicion, Estacion objE) {
        this.posicion = posicion;
        this.objE = objE;
        objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public ParadaLinea() {
        objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public Estacion getObjE() {
        return objE;
    }

    public void setObjE(Estacion objE) {
        this.objE = objE;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public void guardar(Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modifcar(Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList obtenerTodos(Object aux) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
