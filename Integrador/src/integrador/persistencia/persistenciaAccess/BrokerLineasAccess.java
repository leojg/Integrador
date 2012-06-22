package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.IPersistente;
import integrador.dominio.Linea;
import integrador.dominio.LineaAdmin;
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
public class BrokerLineasAccess extends Broker {

   
    public BrokerLineasAccess(Linea objL) {
        super(objL);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        Linea objL = (Linea) arg;
        return "INSERT INTO Lineas (linNom)VALUES('" + objL.getNom() + "')";
    }
    
    @Override
    public String getUpdateCommand(Object arg, Object aux) {
        Linea objL = (Linea) arg;
        return "wut";
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Linea objL = (Linea) arg;
        return "UPDATE Lineas set linActivo=0 WHERE linNom=" + objL.getNom();
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM Lineas WHERE linActivo=1 ";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            Linea objL = (Linea) aux;
            objL.setNom(rs.getString("linNom"));
         
        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Linea();
    }
}
