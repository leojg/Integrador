/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.GregorianCalendar;

/**
 * Representa las compras de tickets que realizan los usuarios.
 * @author Administrador
 */
public class Compra {
    static long ultimoID = 0;
    
    private Long id;
    private Usuario objU;
    private Integer cantidadTickets;
    private GregorianCalendar fechaCompra;

    public Compra(Long id, Usuario objU, Integer cantidadTickets, GregorianCalendar fechaCompra) {
        this.id = id;
        this.objU = objU;
        this.cantidadTickets = cantidadTickets;
        this.fechaCompra = fechaCompra;
    }

    public static void actualizarID() {
        Compra.ultimoID += 1;
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
    
    
}
