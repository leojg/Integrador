/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class CompraAdmin {

    private HashMap<Long, Compra> colCompras;
    private static CompraAdmin instance;

    private CompraAdmin() {
        this.colCompras = new HashMap<>(1000);
    }

    public static CompraAdmin getInstance() {
        if (instance == null) {
            instance = new CompraAdmin();
            instance.cargarCompras();
        }
        return instance;
    }

    boolean altaCompra(Compra objC) {
        if (!this.colCompras.containsKey(objC.getId())) {
            this.colCompras.put(objC.getId(), objC);
            Compra.actualizarID();
            objC.guardar(objC);
            return true;
        }
        return false;
    }
    
    Integer calcularGastoUsuario(Date inicio, Date fin, Usuario objU) {
        Integer gasto = 0;
        for (Compra objC : this.colCompras.values()) {
            if (objC.getObjU().getCI() == objU.getCI() && objC.getFechaCompra().getTime().compareTo(inicio) >= 0 && objC.getFechaCompra().getTime().compareTo(fin) <= 0) {
                gasto = gasto + (objC.getCantidadTickets() * objU.getConvenio().getValor());
            }
        }
        return gasto;
    }

    void cargarCompras() {
        Compra objC = new Compra();
        for (Object o : objC.obtenerTodos()) {
            Compra c = (Compra) o;
            Usuario objU = null;
            try {
                objU = UsuarioAdmin.getInstance().getUsuario(c.getObjU().getCI());
            } catch (NullPointerException ex) {
            } finally {
                c.setObjU(objU);
                this.colCompras.put(c.getId(), c);
            }
        }
    }

    Compra crearCompra(Long id, Usuario objU, GregorianCalendar fecha, Integer cantidad) {
        if (UsuarioAdmin.getInstance().comprobarTipoConvenioUsuario(objU) == true) {
            cantidad = 1;
        }
        return new Compra(id, objU, cantidad, fecha);
    }

    Compra getCompra(Long id) {
        if (this.colCompras.containsKey(id)) {
            return colCompras.get(id);
        }
        return null;
    }

    HashMap<Long, Compra> getCompras() {
        return this.colCompras;
    }
}
