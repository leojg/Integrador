package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.Estacion;
import integrador.dominio.IPersistente;
import integrador.dominio.Linea;
import integrador.dominio.LineaAdmin;
import integrador.dominio.NodoBinario;
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
        Estacion objE = (Estacion) arg;
        return "INSERT INTO EstacionesLineas (linNom,estNom)VALUES('" + objL.getNom()
                + "','" + objE.getNom() + "')";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {
        Linea objL = (Linea) arg;
        Estacion objE = (Estacion) aux;
        return "wut";
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Linea objL = (Linea) aux;
        Estacion objE = (Estacion) arg;
        return "DELETE FROM EstacionesLineas " + " WHERE linNom='" + objL.getNom()
                + "' and estNom='" + objE.getNom() + "'";
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM EstacionesLineas ";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            if (dato.equals(rs.getInt("lin_id"))) {
                Object antid = rs.getObject("ant_id");
                Object nextid = rs.getObject("next_id");
                
                Estacion objE = new Estacion();
                objE.setId(rs.getInt("est_id"));
                NodoBinario ant = null;
                if (antid != null) {
                    Estacion estAnt = new Estacion();
                    estAnt.setId((int) antid);
                    ant = new NodoBinario(estAnt);
                }
                NodoBinario next = null;
                if (nextid != null) {
  
                    Estacion estNext = new Estacion();
                    estNext.setId((int) nextid);
                    next = new NodoBinario(estNext);
                }
                NodoBinario nb = (NodoBinario) aux;

                nb.setNodo(objE, ant, next);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new NodoBinario<>();
    }
}
