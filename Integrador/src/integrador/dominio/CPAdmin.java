/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Administra los codigos postales
 *
 * @author Administrador
 */
public class CPAdmin {

    private HashMap<Integer,CodigoPostal> colCP;
    private static CPAdmin instance;

    private CPAdmin() {
        colCP = new HashMap<>();
    }

    public static CPAdmin getInstance() {
        if (instance == null) {
            CPAdmin.instance = new CPAdmin();
        }
        return instance;
    }
    
    private void cagarCP() {
        CodigoPostal cp = new CodigoPostal();
        cp.obtenerTodos();
    }
    
    public HashMap<Integer,CodigoPostal> getCP() {
        return this.colCP;        
    }
    
}
