package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.*;
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
public class BrokerEstacionesLineasAccess extends Broker {

    public BrokerEstacionesLineasAccess(Linea objL) {
        super(objL);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        Linea objL = (Linea) aux;
        ParadaLinea objP = (ParadaLinea) arg;
        return "INSERT INTO EstacionesLineas (lin_id,est_id,posicion)VALUES(" + objL.getId()
                + "," + objP.getObjE().getId() + "," + objP.getPosicion() + ")";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {
        Linea objL = (Linea) aux;
        ParadaLinea objP = (ParadaLinea) arg;
        return "UPDATE EstacionesLineas SET posicion=" + objP.getPosicion()
                + " WHERE lin_id=" + objL.getId() + " and est_id=" + objP.getObjE().getId();
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Linea objL = (Linea) aux;
        Estacion objE = (Estacion) arg;
        return "DELETE FROM EstacionesLineas " + " WHERE lin_id=" + objL.getId()
                + " and est_id=" + objE.getId() + "";
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM EstacionesLineas ";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            if (dato.equals(rs.getInt("lin_id"))) {
                ParadaLinea objP = (ParadaLinea) aux;
                Estacion objE = new Estacion();
                objE.setId(rs.getInt("est_id"));
                int p = rs.getInt("posicion");
                objP.setObjE(objE);
                objP.setPosicion(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new ParadaLinea();
    }
}
