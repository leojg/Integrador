/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import integrador.persistencia.Broker;
import integrador.persistencia.FachadaBaseDeDatos;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Representa los convenios del sistema
 *
 * @author Administrador
 */
public class Convenio implements IPersistente {

    private Integer tipo;
    private String nom;
    private GregorianCalendar fechaIni;
    private Integer valor;
//True indica que es por compra de ticket. False que se paga fijo a fin de mes
    private Boolean tipoPago;
        private Broker objB;

    public Convenio(Integer tipo, String nom, GregorianCalendar fechaIni, Integer valor, boolean tipoPago) {
        this.tipo = tipo;
        this.nom = nom;
        this.fechaIni = fechaIni;
        this.tipoPago = tipoPago;
        this.valor = valor;
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public Convenio() {
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public boolean getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(boolean tipoPago) {
        this.tipoPago = tipoPago;
    }

    public GregorianCalendar getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(GregorianCalendar fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        String tp;
        if (this.tipoPago == true) {
            tp = "Mensual";
        } else {
            tp = "Por Compra";
        }

        return nom + " - Convenio Tipo:" + tipo.toString() + " - Vigente desde:" + fechaIni.toString() + " Tipo:" + tp + " Valor" + valor;
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
