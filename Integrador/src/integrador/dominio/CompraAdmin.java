/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author Administrador
 */
public class CompraAdmin {
    private HashMap<Long,Compra> colCompras;
    private static CompraAdmin instance;

    private CompraAdmin() {
        this.colCompras = new HashMap<>(1000);
    }
    
    public static CompraAdmin getInstance() {
        if (instance == null) 
                instance = new CompraAdmin();
        return instance;                        
    }
    
    boolean altaCompra(Compra objC) {
        if (!this.colCompras.containsKey(objC.getId())) {
            this.colCompras.put(objC.getId(), objC);
            Compra.actualizarID();
            return true;
        }
        return false;
    }
   
    Compra crearCompra(Long id, Usuario objU, GregorianCalendar fecha, Integer cantidad) {
        if (UsuarioAdmin.getInstance().comprobarTipoConvenioUsuario(objU) == true)
            cantidad = 1;
        return new Compra(id, objU, cantidad, fecha);
    }
    
    Compra getCompra(Long id) {
        if (this.colCompras.containsKey(id))
            return colCompras.get(id);
        return null;
    }
    
    HashMap<Long, Compra> getCompras() {
        return this.colCompras;
    }
    
    
}
