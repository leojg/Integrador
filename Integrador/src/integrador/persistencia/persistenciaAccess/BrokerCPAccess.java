package integrador.persistencia.persistenciaAccess;

//
import integrador.Utilitaria;
import integrador.dominio.CodigoPostal;
import integrador.dominio.Compra;
import integrador.dominio.IPersistente;
import integrador.dominio.Usuario;
import integrador.persistencia.Broker;
import java.security.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

//
//
//  @ Project : Untitled
//  @ File Name : BrokerUsuarioPVAccess.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public class BrokerCPAccess extends Broker {

    public BrokerCPAccess(CodigoPostal objC) {
        super(objC);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        CodigoPostal objC = (CodigoPostal) arg;
        return "INSERT INTO CodigosPostales ("+ objC.getCp() +")";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {
        return "wut";
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        return "wut";
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM CodigosPostales";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            CodigoPostal objC = (CodigoPostal) aux;
            objC.setCp(rs.getInt("cp_num"));

        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new CodigoPostal();
    }
}
