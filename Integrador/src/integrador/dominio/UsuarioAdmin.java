/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.IDRepetidoException;
import exceptions.NoExisteCPException;
import exceptions.NombreRepetidoException;
import java.util.*;

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

    boolean altaUsuario(Usuario objU) throws NombreRepetidoException, IDRepetidoException {
        if (!this.colUsr.containsKey(objU.getCI())) {
            if (!comprobarNombreUnico(objU)) {
                throw new NombreRepetidoException();
            }
            this.colUsr.put(objU.getCI(), objU);
            objU.guardar(objU);
            return true;
        }
        throw new IDRepetidoException("El CI elegido ya existe");
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
            objC = ConvenioAdmin.getInstance().getConvenio(u.getConvenio().getTipo());
            u.setConvenio(objC);
            this.colUsr.put(u.getCI(), u);
        }
    }

    private boolean comprobarNombreUnico(Usuario objU) {
        for (Usuario u : this.colUsr.values()) {
            if (u.getNom().equals(objU.getNom())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna True si el convenio es de pago mensual Y false si es por ticket o
     * si el usuario no posee convenio
     *
     * @param objU
     * @return
     */
    boolean comprobarTipoConvenioUsuario(Usuario objU) {
        if (objU.getConvenio() == null) {
            return false;
        } else {
            if (objU.getConvenio().getTipoPago() == false) {
                return true;
            }
        }
        return false;
    }

    Usuario crearUsuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel) throws NoExisteCPException {
       if (CPAdmin.getInstance().existeCP(CP)) {    
        return new Usuario(CI, nom, fechaNac, dir, barrio, CP, mail, tel, ConvenioAdmin.getInstance().getConvenio(0));
       }
       throw new NoExisteCPException();
    }

    boolean modUsuario(Usuario objU) {
        if (this.colUsr.containsKey(objU.getCI())) {
            Usuario u = this.colUsr.get(objU.getCI());
            objU.setConvenio(u.getConvenio());
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

    ArrayList<Usuario> getUsuariosParaPromocion(int edadMax, int diasRegistroMax, int cantidadMax) {
        ArrayList<Usuario> arr = new ArrayList(cantidadMax);
        for (Usuario objU : this.colUsr.values()) {
                        GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
            int edadUsr = cal.getTime().getYear() - objU.getFechaNac().getTime().getYear();
            //diasRegUsr hay que pasarlo a dias porque es un long.
            long fechaRegistroMili = objU.getFechaRegistro().getTime().getTime();

            long hoyMili = cal.getTimeInMillis();
            long diasRegUsrMili = hoyMili - fechaRegistroMili;
            int diasRegUsr = (int) ((diasRegUsrMili * 1.1574074074) / 100000000);
            if (edadUsr <= edadMax && diasRegUsr <= diasRegistroMax) {
                arr.add(objU);
            }
        }
        while (arr.size() > cantidadMax) {
            int gastoMin = Integer.MAX_VALUE;
            Usuario usrElegido = null;
            for (Usuario u : arr) {
                Date fecha = new Date(1, 1, 1900);
                int gastoUsr = CompraAdmin.getInstance().calcularGastoUsuario(fecha, GregorianCalendar.getInstance().getTime(), u);
                if (gastoUsr <= gastoMin) {
                    usrElegido = u;
                }

            }
            arr.remove(usrElegido);
        }
        return arr;
    }

//    void asd(int cantidadMax) {
//        ArrayList<Usuario> arr = new ArrayList(cantidadMax);
//        while (arr.size() > cantidadMax) {
//            int gastoMin = Integer.MAX_VALUE;
//            Usuario usrElegido = null;
//            for (Usuario u : arr) {
//                Date fecha = new Date(1, 1, 1900);
//                int gastoUsr = CompraAdmin.getInstance().calcularGastoUsuario(fecha, GregorianCalendar.getInstance().getTime(), u);
//                if (gastoUsr <= gastoMin) {
//                    usrElegido = u;
//                }
//
//            }
//            arr.remove(usrElegido);
//        }
//    }

    ArrayList getUsuariosPorEdad(int eMin, int eMax) {
        ArrayList arr = new ArrayList();
        int cont = 0;
        for (Usuario objU : this.colUsr.values()) {
            Date hoy = GregorianCalendar.getInstance().getTime();
            int edadUsr = hoy.getYear() - objU.getFechaNac().getTime().getYear();
            if (edadUsr >= eMin && edadUsr <= eMax) {
                cont++;
                Date ini = new Date(hoy.getTime());
                ini.setYear(ini.getYear() - 1);
                int gasto = CompraAdmin.getInstance().calcularGastoUsuario(ini, hoy, objU);
                Object[] usr = {objU.getCI(), objU.getNom(), edadUsr, objU.getConvenio().getTipo(), gasto};
                arr.add(usr);
            }
        }
        return arr;
    }

    HashMap<Integer, Usuario> getUsuariosMasGasto(Date ini, Date fin) {
        HashMap<Integer, Usuario> colUsrGasto = new HashMap<>();
        int max = 0;
        for (Usuario objU : this.colUsr.values()) {
            int gastoUsr = CompraAdmin.getInstance().calcularGastoUsuario(ini, fin, objU);
            if (max < gastoUsr) {
                colUsrGasto.values().clear();
                colUsrGasto.put(objU.getCI(), objU);
                max = gastoUsr;
            } else if (max == gastoUsr) {
                colUsrGasto.put(objU.getCI(), objU);
            }
        }
        return colUsrGasto;
    }
}
