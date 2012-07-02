/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import integrador.persistencia.Broker;
import integrador.persistencia.FachadaBaseDeDatos;
import java.util.ArrayList;

/**
 * Representa los codigos postales.
 *
 * @author Administrador
 */
public class CodigoPostal implements IPersistente {

    private Integer cp;
    private Broker objB;

    public CodigoPostal(Integer cp) {
        this.cp = cp;
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public CodigoPostal() {
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    @Override
    public void guardar(Object arg) {
        this.objB.guardar(arg,null);
    }

    @Override
    public void modifcar(Object arg) {
        this.objB.modificar();
    }

    @Override
    public void eliminar(Object arg) {
        this.objB.eliminar(arg,null);
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
