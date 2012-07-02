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
            instance.cagarCP();
        }
        return instance;
    }
    
    private void cagarCP() {
        CodigoPostal cp = new CodigoPostal();
     for (Object o : cp.obtenerTodos()) {
         CodigoPostal cp2 = (CodigoPostal) o;
         this.colCP.put(cp2.getCp(), cp2);
     }
    }
    
    public boolean existeCP(int cp) {
        if (this.colCP.containsKey(cp)) {
            return true;
        }
        return false;
    }
    
    public HashMap<Integer,CodigoPostal> getCPs() {
        return this.colCP;        
    }
    
}
