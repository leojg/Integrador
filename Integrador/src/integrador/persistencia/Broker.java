package integrador.persistencia;

//
import integrador.Integrador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import integrador.dominio.IPersistente;

//
//
//  @ Project : Untitled
//  @ File Name : Broker.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public abstract class Broker {

    protected IPersistente objP;

    public Broker(IPersistente xObjP) {
        objP = xObjP;
    }

    public IPersistente getObjP() {
        return objP;
    }

    public void setObjP(IPersistente objP) {
        this.objP = objP;
    }

    public void guardar(Object arg, Object aux) {
        this.ejecutar(this.getInsertCommand(arg, aux));
    }

    public void modificar(Object arg, Object aux) {
        this.ejecutar(this.getUpdateCommand(arg, aux));
    }

    public void eliminar(Object arg, Object aux) {
        this.ejecutar(this.getDeleteCommand(arg, aux));
    }

    public void guardar() {
        guardar(null,null);
    }

    public void modificar() {
        modificar(null,null);
    }

    public void eliminar() {
        eliminar(null,null);
    }

    public void ejecutar(String sentenciaSql) {
        try {
            Statement st = BaseDeDatos.GetInstance().getConexion().createStatement();
            st.executeUpdate(sentenciaSql);
            st.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar sql.\n" + e.getMessage());
        }
    }

    public ResultSet obtenerResultSet(String consultaSql) {
        ResultSet rs = null;
        try {
            Statement st = BaseDeDatos.GetInstance().getConexion().createStatement();
            rs = st.executeQuery(consultaSql);
        } catch (SQLException e) {
            System.out.println("Error al ejecutar sql.\n" + e.getMessage());
        }
        return rs;
    }

    public ArrayList obtenerTodos() {
        return obtenerTodos(null);
    }
    
    public ArrayList obtenerTodos(Object dato) {
        ArrayList ret = new ArrayList();
        try {
            ResultSet rs = this.obtenerResultSet(getSelectCommand());
            while (rs.next()) {
                IPersistente aux = getNuevo();
                obtenerDesdeResultSet(rs, aux, dato);
                ret.add(aux);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener objetos.\n" + e.getMessage());
        }
        return ret;
    }

    public abstract String getInsertCommand(Object arg, Object aux);

    public abstract String getUpdateCommand(Object arg, Object aux);

    public abstract String getDeleteCommand(Object arg, Object aux);

    public abstract String getSelectCommand();
    /***
     * Obtiene objetos desde una base de datos. rs es el resultset que contiene lo obtenido.
     * Aux es un parametro para crear el objeto obtenido.
     * Dato es cualquier dato auxiliar requerido.
     * 
     * @param rs
     * @param aux
     * @param dato 
     */
    public abstract void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato);

    public abstract IPersistente getNuevo();
}
