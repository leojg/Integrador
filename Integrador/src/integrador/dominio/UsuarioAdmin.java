/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Clase encargada de administrar usuarios.
 *
 * @author Administrador
 */
public class UsuarioAdmin {

    private HashMap<Integer, Usuario> colUsr;
    private static UsuarioAdmin instance;

    private UsuarioAdmin() {
        this.colUsr = new HashMap<>();
    }

    public static UsuarioAdmin getInstance() {
        if (instance == null) {
            instance = new UsuarioAdmin();
            instance.cargarUsuarios();
        }
        return instance;
    }

    boolean altaUsuario(Usuario objU) {
        if (!this.colUsr.containsKey(objU.getCI())) {
            this.colUsr.put(objU.getCI(), objU);
            objU.guardar(objU);
            return true;
        }
        return false;
    }

    boolean bajaUsuario(Usuario objU) {
        if (this.colUsr.containsKey(objU.getCI())) {
            this.colUsr.remove(objU.getCI());
            objU.eliminar(objU);
            return true;
        }
        return false;
    }

    void cargarUsuarios() {
        Usuario objU = new Usuario();
        for (Object o : objU.obtenerTodos()) {
            Usuario u = (Usuario) o;
            Convenio objC = null;
            try {
            objC  = ConvenioAdmin.getInstance().getConvenio(u.getConvenio().getTipo());
            } catch (NullPointerException ex) {
            } finally  {
            u.setConvenio(objC);
            this.colUsr.put(u.getCI(), u);
            }
        }
    }
    /**
     * Retorna True si el convenio es de pago mensual Y false si es por ticket 
     * o si el usuario no posee convenio 
     * @param objU
     * @return 
     */
    boolean comprobarTipoConvenioUsuario(Usuario objU) {
        if (objU.getConvenio() == null) {
           return false;
        } else {
            if (objU.getConvenio().getTipoPago() == false)
                return true;
        }
        return false;
    }
    
    Usuario crearUsuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel) {
        return new Usuario(CI, nom, fechaNac, dir, barrio, CP, mail, tel);
    }

    boolean modUsuario(Usuario objU) {
        if (this.colUsr.containsKey(objU.getCI())) {
            this.colUsr.put(objU.getCI(), objU);
            objU.modifcar(objU);
            return true;
        }
        return false;
    }

    boolean modConvenioUsuario(Convenio objC, Usuario objU) {
        if (this.colUsr.containsKey(objU.getCI())) {
            objU.setConvenio(objC);
            objU.modifcarConvUsr(objU);
            return true;
        }
        return false;
    }

    Usuario getUsuario(Integer CI) {
        return this.colUsr.get(CI);
    }

    HashMap<Integer, Usuario> getUsuarios() {
        return this.colUsr;
    }

    HashMap<Integer, Usuario> getUsuariosPorEdad(Date edadMin, Date edadMax) {
        HashMap<Integer, Usuario> colU = new HashMap<>();
        for (Usuario objU : this.colUsr.values()) {
            if (objU.getFechaNac().getTime().compareTo(edadMin) >= 0
                    && objU.getFechaNac().getTime().compareTo(edadMax) <= 0) {
                colU.put(objU.getCI(), objU);
            }
        }
        return colU;
    }
}
