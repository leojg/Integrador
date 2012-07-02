package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.IPersistente;
import integrador.dominio.Estacion;
import integrador.dominio.EstacionAdmin;
import integrador.persistencia.Broker;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

//
//
//  @ Project : Untitled
//  @ File Name : BrokerUsuarioPVAccess.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public class BrokerEstacionesAccess extends Broker {


    public BrokerEstacionesAccess(Estacion objE) {
        super(objE);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        Estacion objE = (Estacion) arg;
        return "INSERT INTO Estaciones (est_nom,cp_num)VALUES('" + objE.getNom() + "'," + objE.getCp() + ")";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {
        return "weit";
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Estacion objE = (Estacion) arg;
        return "UPDATE Estaciones SET est_act=0 WHERE est_id=" + objE.getId();
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM Estaciones WHERE est_act=1 ";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            Estacion objE = (Estacion) aux;
            objE.setNom(rs.getString("est_nom").toLowerCase());
            objE.setCp(rs.getInt("cp_num"));
            objE.setId(rs.getInt("est_id"));
        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Estacion();
    }
}
