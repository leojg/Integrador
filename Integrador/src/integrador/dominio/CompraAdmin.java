/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.ElementoNoEncontradoException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try {
                instance.cargarCompras();
            } catch (ElementoNoEncontradoException ex) {
                Logger.getLogger(CompraAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            System.out.println(objC.getFechaCompra().getTime().toString());
            System.out.println(objC.getFechaCompra().getTime().compareTo(inicio));
            System.out.println(objC.getFechaCompra().getTime().compareTo(fin));
            if (objC.getUsrCI().compareTo(objU.getCI()) == 0 && objC.getFechaCompra().getTime().compareTo(inicio) >= 0 && objC.getFechaCompra().getTime().compareTo(fin) <= 0) {
                gasto = gasto + (objC.getCantidadTickets() * objC.getCosto());
            }
        }
        return gasto;
    }

    void cargarCompras() throws ElementoNoEncontradoException {
        Compra objC = new Compra();
        for (Object o : objC.obtenerTodos()) {
            Compra c = (Compra) o;
            this.colCompras.put(c.getId(), c);
        }
    }

    Compra crearCompra(Long id, Usuario objU, GregorianCalendar fecha, Integer cantidad) {
        if (UsuarioAdmin.getInstance().comprobarTipoConvenioUsuario(objU) == true) {
            cantidad = 1;
        }
        int costo = cantidad * objU.getConvenio().getValor();
        return new Compra(id, objU.getCI(), cantidad, fecha, costo);
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
