/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.*;
import java.rmi.NoSuchObjectException;
import java.util.*;

/**
 * Clase encargada de establecer el acceso por parte de la interfaz a la capa
 * logica
 *
 * @author Administrador
 */
public class FachadaInterfaz extends Observable {

    //***************** Estaciones *************************//
    private EstacionAdmin objEA = EstacionAdmin.getInstance();

    public boolean altaEstacion(String nom, Integer cp) throws NombreRepetidoException {
        if (objEA.altaEstacion(objEA.crearEstacion(nom, cp))) {
            setChanged();
            notifyObservers("Estacion");
            return true;
        }
        return false;
    }

    public boolean bajaEstacion(String nom, Integer cp) throws NoSuchObjectException, EstacionTieneLineaException {
        if (objEA.bajaEstacion(objEA.crearEstacion(nom, cp))) {
            setChanged();
            notifyObservers("Estacion");
            return true;
        }
        return false;
    }

    public Estacion getEstacion(String nom) {
        return objEA.getEstacion(nom);
    }

    public HashMap<String, Estacion> getEstaciones() {
        return objEA.getEstaciones();
    }
    
    public HashMap<String, Estacion> getEstacionesCercanas(int CI) {
        return objEA.getEstacionesCercanas(objUA.getUsuario(CI));
    }

    public HashMap<String, Estacion> getEstacionesLinea(String nom) {
        return objLA.getLinea(nom).getColEstaciones();
    }

    public HashMap<String, Estacion> getEstacionesNoEstanEnLinea(String nom) {
        return objEA.getEstacionesNoEstanEnLinea(objLA.getLinea(nom));
    }
//    public   HashMap<String, Estacion> getEstacionesLinea(String nomLinea) {
//   
//    } 
    //************** Lineas ****************************//
    private LineaAdmin objLA = LineaAdmin.getInstance();

    public boolean altaLinea(String nom) throws FormatoLineaIncorrectoException {

        if (objLA.altaLinea(objLA.crearLinea(nom))) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;
    }

    public boolean agregarEstacionALinea(String nomLinea, String nomEst) {
        Linea objL = objLA.getLinea(nomLinea);
        Estacion objE = objEA.getEstacion(nomEst);
        if (objL.AgregarEstacion(objE)) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;

    }

    public boolean bajaLinea(String nom) {
        if (objLA.bajaLinea(objLA.getLinea(nom))) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;
    }

    public Linea getLinea(String nom) {
        return objLA.getLinea(nom);
    }

    public HashMap<String, Linea> getLineas() {
        return objLA.getLineas();
    }

    public HashMap<String, Linea> getLineasEsacion(String nom) {
        return objLA.getLineasEstacion(objEA.getEstacion(nom));
    }

    public boolean quitarEstacionDeLinea(String nomLinea, String nomEst) {
        Linea objL = objLA.getLinea(nomLinea);
        Estacion objE = objEA.getEstacion(nomEst);
        if (objL.QuitarEstacion(objE)) {
            setChanged();
            notifyObservers("Linea");
            return true;
        }
        return false;
    }
    //************** Convenios ****************************//
    private ConvenioAdmin objCA = ConvenioAdmin.getInstance();

    public boolean altaConvenio(Integer tipo, String nom, GregorianCalendar fecha, Integer valor, boolean tipopago) throws NombreRepetidoException {
        if (objCA.altaConvenio(objCA.crearConvenio(tipo, nom, fecha, valor, tipopago))) {
            setChanged();
            notifyObservers("Convenio");
            return true;
        }
        return false;
    }

    public boolean bajaConvenio(Integer tipo, String nom, GregorianCalendar fecha, Integer valor, boolean tipopago) throws UsuariosAsociadosException {
        if (objCA.bajaConvenio(objCA.crearConvenio(tipo, nom, fecha, valor, tipopago))) {
            setChanged();
            notifyObservers("Convenio");
            return true;
        }
        return false;
    }

    public HashMap<Integer, Convenio> getConvenios() {
        return objCA.getConvenios();
    }
    
    public HashMap<Integer, Convenio> getConveniosVigentes(int año) {
        return objCA.getConveniosVigentes(año);
    }

    public boolean modConvenio(Integer tipo, Integer precioNuevo) {
        return this.objCA.modConvenio(tipo, precioNuevo);
    }
    //***************** Usuarios *************************//
    private UsuarioAdmin objUA = UsuarioAdmin.getInstance();

    public boolean altaUsuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel) throws NombreRepetidoException, IDRepetidoException {
        if (objUA.altaUsuario(objUA.crearUsuario(CI, nom, fechaNac, dir, barrio, CP, mail, tel))) {
            setChanged();
            notifyObservers("Usuario");
            return true;
        }
        return false;
    }

    public boolean bajaUsuario(Integer CI) {
        if (objUA.bajaUsuario(objUA.getUsuario(CI))) {
            setChanged();
            notifyObservers("Usuario");
            return true;
        }
        return false;
    }

    public Usuario getUsuario(Integer CI) {
        return this.objUA.getUsuario(CI);
    }

    public HashMap<Integer, Usuario> getUsuarios() {
        return this.objUA.getUsuarios();
    }

    public HashMap<Integer, Usuario> getUsuariosMasGasto(Date ini, Date fin) {
        return this.objUA.getUsuariosMasGasto(ini, fin);
    }

    public ArrayList getUsuariosPorEdad(int eMin, int eMax) {
        return this.objUA.getUsuariosPorEdad(eMin, eMax);
    }

    public boolean modUsuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel) {
        if (objUA.modUsuario(objUA.crearUsuario(CI, nom, fechaNac, dir, barrio, CP, mail, tel))) {
            setChanged();
            notifyObservers("Usuario");
            return true;
        }
        return false;
    }

    public boolean modUsrConv(Integer CI, Integer conTipo) {
        Convenio objC = objCA.getConvenio(conTipo);
        Usuario objU = objUA.getUsuario(CI);
        if (objUA.modConvenioUsuario(objC, objU)) {
            setChanged();
            notifyObservers("Usuario");
            return true;
        }
        return false;
    }
    //***************** Compras *************************//
    private CompraAdmin objCoA = CompraAdmin.getInstance();

    public boolean altaCompra(Long ID, Integer usrCI, GregorianCalendar fechaCompra, Integer cantidad) {
        if (objCoA.altaCompra(objCoA.crearCompra(ID, objUA.getUsuario(usrCI), fechaCompra, cantidad))) {
            setChanged();
            notifyObservers("Compra");
            return true;
        }
        return false;
    }

    public Compra getCompra(Long id) {
        return objCoA.getCompra(id);
    }

    public HashMap<Long, Compra> getCompras() {
        return objCoA.getCompras();
    }

    public Long getUltimoIDCompra() {
        return Compra.getUltimoID();
    }
}
