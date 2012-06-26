/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Clase encargada de administrar convenios
 *
 * @author Administrador
 */
public class ConvenioAdmin {

    private HashMap<Integer, Convenio> colConvenios;
    private static ConvenioAdmin instance;

    private ConvenioAdmin() {
        this.colConvenios = new HashMap<>();
    }

    public static ConvenioAdmin getInstance() {
        if (instance == null) {
            instance = new ConvenioAdmin();
            instance.cargarConvenios();
        }
        return instance;
    }

    boolean altaConvenio(Convenio objC) {
        if (!this.colConvenios.containsKey(objC.getTipo())) {
            this.colConvenios.put(objC.getTipo(), objC);
            objC.guardar(objC);
            return true;
        }
        return false;
    }

    boolean bajaConvenio(Convenio objC) {
        if (this.colConvenios.containsKey(objC.getTipo())) {
            this.colConvenios.remove(objC.getTipo());
            objC.eliminar(objC);
            return true;
        }
        return false;
    }

    void cargarConvenios() {
        Convenio objC = new Convenio();
        for (Object o : objC.obtenerTodos()) {
            Convenio c = (Convenio) o;
            this.colConvenios.put(c.getTipo(), c);
        }
    }

    Convenio crearConvenio(Integer tipo, String nom, GregorianCalendar fecha, Integer valor, boolean tipopago) {
        return new Convenio(tipo, nom, fecha, valor, tipopago);
    }

    boolean modConvenio(Integer tipo, Integer nuevoPrecio) {
        if (this.colConvenios.containsKey(tipo)) {
            Convenio c = this.colConvenios.get(tipo);
            c.setValor(nuevoPrecio);
            this.colConvenios.put(c.getTipo(), c);
            c.modifcar(c);
            return true;
        }
        return false;
    }

    Convenio getConvenio(Integer tipo) {
        return this.colConvenios.get(tipo);
    }

    HashMap<Integer, Convenio> getConvenios() {
        return this.colConvenios;
    }

    HashMap<Integer, Convenio> getConveniosVigentes(Integer año) {
        HashMap<Integer, Convenio> colConv = new HashMap<>();
        for (Convenio objC : this.colConvenios.values()) {
            if (objC.getFechaIni().YEAR <= año) {
                colConv.put(objC.getTipo(), objC);
            }
        }
        return colConv;
    }
}
