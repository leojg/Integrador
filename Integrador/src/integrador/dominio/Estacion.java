/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import integrador.persistencia.Broker;
import integrador.persistencia.FachadaBaseDeDatos;
import java.util.ArrayList;

/**
 * Clase que representa las estaciones. El conjunto de estaciones forma una
 * linea.
 *
 * @author Administrador
 */
public class Estacion implements IPersistente {

    private String nom;
    private Integer cp;
    private Broker objB;

    public Estacion(String nom, Integer cp) {
        this.nom = nom;
        this.cp = cp;
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public Estacion() {
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
    public String toString() {
        return "Nombre:" + this.nom + "  C.P.:" + this.cp;
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
