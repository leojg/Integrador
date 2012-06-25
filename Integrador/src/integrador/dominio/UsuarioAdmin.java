/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.HashMap;

/**
 * Clase encargada de administrar usuarios.
 * @author Administrador
 */
public class UsuarioAdmin {
    private HashMap<Integer,Usuario> colUsr;
    private static UsuarioAdmin instance;

    private UsuarioAdmin() {
        this.colUsr = new HashMap<>();
    }
    
    public static UsuarioAdmin getInstance() {
        if(instance == null)
            instance = new UsuarioAdmin();
        return instance;
    }
    
    boolean altaUsuario(Usuario objU) {
        if (!this.colUsr.containsKey(objU.getCI())) {
            this.colUsr.put(objU.getCI(), objU);
            return true;
        }
        return false;
    }
    
    boolean bajaUsuario(Usuario objU) {
        if (this.colUsr.containsKey(objU.getCI())) {
            this.colUsr.remove(objU.getCI());
            return true;
        }
        return false;
    }
    
    HashMap<Integer, Usuario> getUsuarios() {
        return this.colUsr;
    }
     
    boolean modUsuario(Usuario objU) {
        if (this.colUsr.containsKey(objU.getCI())) {
            this.colUsr.put(objU.getCI(), objU);
            return true;
        }
        return false;
    }

    Usuario getUsuario(Integer CI) {
        return this.colUsr.get(CI);
    }
    
    
}
