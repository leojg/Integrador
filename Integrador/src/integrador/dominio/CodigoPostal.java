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
    private String nom;
    private Broker objB;

    public CodigoPostal(Integer cp, String nom) {
        this.cp = cp;
        this.nom = nom;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
