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
 * Representa las compras de tickets que realizan los usuarios.
 *
 * @author Administrador
 */
public class Compra implements IPersistente {

    static long ultimoID = 0;
    private Long id;
    private Usuario objU;
    private Integer cantidadTickets;
    private GregorianCalendar fechaCompra;
    private Integer costo;
    private Broker objB;

    public Compra(Long id, Usuario objU, Integer cantidadTickets, GregorianCalendar fechaCompra, Integer costo) {
        this.id = id;
        this.objU = objU;
        this.cantidadTickets = cantidadTickets;
        this.fechaCompra = fechaCompra;
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
        this.costo = costo;
    }

    public Compra() {
        this.objB = FachadaBaseDeDatos.getInstance().obtenerBroker(this, null);
    }

    public static void actualizarID() {
        Compra.ultimoID += 1;
    }

    public static void setUltimoID(long ultimoID) {
        if (Compra.ultimoID <= ultimoID) 
            Compra.ultimoID = ultimoID + 1;
    }

    public static Long getUltimoID() {
        return ultimoID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadTickets() {
        return cantidadTickets;
    }

    public void setCantidadTickets(Integer cantidadTickets) {
        this.cantidadTickets = cantidadTickets;
    }

    public GregorianCalendar getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(GregorianCalendar fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Usuario getObjU() {
        return objU;
    }

    public void setObjU(Usuario objU) {
        this.objU = objU;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
    

    @Override
    public void guardar(Object arg) {
        this.objB.guardar(arg, null);
    }

    @Override
    public void modifcar(Object arg) {
        this.objB.modificar();
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
