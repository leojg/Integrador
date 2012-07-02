/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import exceptions.*;
import integrador.Utilitaria;
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

    public boolean altaEstacion(String nom, Integer cp) throws NombreRepetidoException, NoExisteCPException {
        if (objEA.altaEstacion(objEA.crearEstacion(nom, cp))) {
            setChanged();
            notifyObservers("Estacion");
            return true;
        }
        return false;
    }

    public boolean bajaEstacion(String nom, Integer cp) throws NoSuchObjectException, EstacionTieneLineaException, NoExisteCPException {
        if (objEA.bajaEstacion(objEA.getEstacion(nom))) {
            setChanged();
            notifyObservers("Estacion");
            return true;
        }
        return false;
    }

    public Estacion getEstacion(String nom) {
        return objEA.getEstacion(nom);
    }

    public Object[][] getEstaciones() {
        Object[][] datos = new Object[objEA.getEstaciones().size()][2];
        int cont = 0;
        for (Estacion objE : objEA.getEstaciones().values()) {
            datos[cont][0] = objE.getNom();
            datos[cont][1] = objE.getCp();
            cont++;
        }
        return datos;
    }

    public Object[][] getEstacionesCercanas(int CI) {
        Object[][] datos = new Object[objEA.getEstacionesCercanas(objUA.getUsuario(CI)).size()][2];
        int cont = 0;
        for (Estacion objE : objEA.getEstacionesCercanas(objUA.getUsuario(CI)).values()) {
            datos[cont][0] = objE.getNom();
            datos[cont][1] = objE.getCp();
            cont++;
        }
        return datos;
    }

    public Object[][] getEstacionesLinea(String nom) {
        Object[][] datos = new Object[objLA.getLinea(nom).getColEstaciones().size()][2];
        int cont = 0;
        for (Estacion objE : objLA.getLinea(nom).getColEstaciones().values()) {
            datos[cont][0] = objE.getNom();
            datos[cont][1] = objE.getCp();
            cont++;
        }
        return datos;
    }

    public Object[][] getEstacionesNoEstanEnLinea(String nom) {
        Object[][] datos = new Object[objEA.getEstacionesNoEstanEnLinea(objLA.getLinea(nom)).size()][2];
        int cont = 0;
        for (Estacion objE : objEA.getEstacionesNoEstanEnLinea(objLA.getLinea(nom)).values()) {
            datos[cont][0] = objE.getNom();
            datos[cont][1] = objE.getCp();
            cont++;
        }
        return datos;
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

    public Object[][] getLineas() {
        Object[][] datos = new Object[objLA.getLineas().size()][1];
        int cont = 0;
        for (Linea objL : objLA.getLineas().values()) {
            datos[cont][0] = objL.getNom();
            cont++;
        }
        return datos;
    }

    public Object[][] getLineasEsacion(String nom) {
        Object[][] datos = new Object[objLA.getLineasEstacion(objEA.getEstacion(nom)).size()][1];
        int cont = 0;
        for (Linea objL : objLA.getLineasEstacion(objEA.getEstacion(nom)).values()) {
            datos[cont][0] = objL.getNom();
            cont++;
        }
        return datos;
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

    public Object[][] getConvenios() {
        Object[][] datos = new Object[objCA.getConvenios().size()][5];
        int cont = 0;
        for (Convenio objC : objCA.getConvenios().values()) {
            datos[cont][0] = objC.getTipo();
            datos[cont][1] = objC.getNom();
            String tp;
            if (objC.getTipoPago() == false) {
                tp = "Mensual";
            } else {
                tp = "Por Compra";
            }
            datos[cont][2] = tp;
            datos[cont][3] = objC.getValor();
            datos[cont][4] = Utilitaria.ConvertirGCalendarString(objC.getFechaIni());
            cont++;
        }
        return datos;
    }

    public Object[][] getConveniosVigentes(int año) {
        Object[][] datos = new Object[objCA.getConveniosVigentes(año).size()][5];
        int cont = 0;
        for (Convenio objC : objCA.getConveniosVigentes(año).values()) {
            datos[cont][0] = objC.getTipo();
            datos[cont][1] = objC.getNom();
            String tp;
            if (objC.getTipoPago() == false) {
                tp = "Mensual";
            } else {
                tp = "Por Compra";
            }
            datos[cont][2] = tp;
            datos[cont][3] = objC.getValor();
            datos[cont][4] = Utilitaria.ConvertirGCalendarString(objC.getFechaIni());
            cont++;
        }
        return datos;
    }

    public boolean modConvenio(Integer tipo, Integer precioNuevo) {
        return this.objCA.modConvenio(tipo, precioNuevo);
    }
    //***************** Usuarios *************************//
    private UsuarioAdmin objUA = UsuarioAdmin.getInstance();

    public boolean altaUsuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel) throws NombreRepetidoException, IDRepetidoException, NoExisteCPException {
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

    public Object[][] getUsuarios() {
        Object[][] datos = new Object[objUA.getUsuarios().size()][10];
        int cont = 0;
        for (Usuario objU : objUA.getUsuarios().values()) {
            datos[cont][0] = objU.getCI();
            datos[cont][1] = objU.getNom();
            datos[cont][2] = objU.getConvenio().getNom();
            datos[cont][3] = objU.getBarrio();
            datos[cont][4] = objU.getDir();
            datos[cont][5] = objU.getTel();
            datos[cont][6] = objU.getCP();
            datos[cont][7] = objU.getMail();
            datos[cont][8] = Utilitaria.ConvertirGCalendarString(objU.getFechaRegistro());
            datos[cont][9] = Utilitaria.ConvertirGCalendarString(objU.getFechaNac());
            cont++;
        }
        return datos;
    }

    public Object[][] getUsuariosMasGasto(Date ini, Date fin) {
        Object[][] datos = new Object[objUA.getUsuarios().size()][10];
        int cont = 0;
        for (Usuario objU : objUA.getUsuarios().values()) {
            datos[cont][0] = objU.getCI();
            datos[cont][1] = objU.getNom();
            datos[cont][2] = objU.getConvenio().getNom();
            datos[cont][3] = objU.getBarrio();
            datos[cont][4] = objU.getDir();
            datos[cont][5] = objU.getTel();
            datos[cont][6] = objU.getCP();
            datos[cont][7] = objU.getMail();
            datos[cont][8] = Utilitaria.ConvertirGCalendarString(objU.getFechaRegistro());
            datos[cont][9] = Utilitaria.ConvertirGCalendarString(objU.getFechaNac());
            cont++;
        }
        return datos;
    }

    public ArrayList getUsuariosPorEdad(int eMin, int eMax) {
        return this.objUA.getUsuariosPorEdad(eMin, eMax);
    }

    public Object[][] getUsuariosParaPromocion(Integer edadMax, Integer diasRegistroMax, Integer cantidadMax) {
                        Object[][] datos = new Object[objUA.getUsuariosParaPromocion(edadMax, diasRegistroMax, cantidadMax).size()][10];
                int cont = 0;
                for (Usuario objU : objUA.getUsuariosParaPromocion(edadMax, diasRegistroMax, cantidadMax)) {
                    datos[cont][0] = objU.getCI();
                    datos[cont][1] = objU.getNom();
                    datos[cont][2] = objU.getConvenio().getNom();
                    datos[cont][3] = objU.getBarrio();
                    datos[cont][4] = objU.getDir();
                    datos[cont][5] = objU.getTel();
                    datos[cont][6] = objU.getCP();
                    datos[cont][7] = objU.getMail();
                    datos[cont][8] = Utilitaria.ConvertirGCalendarString(objU.getFechaRegistro());
                    datos[cont][9] = Utilitaria.ConvertirGCalendarString(objU.getFechaNac());
                    cont++;
                }
        return datos;
    }

    public boolean modUsuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel) throws NoExisteCPException {
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

    /**
     * ******** Codigo Postal ***************
     */
    public HashMap<Integer, CodigoPostal> getCPs() {
        return CPAdmin.getInstance().getCPs();
    }
}
