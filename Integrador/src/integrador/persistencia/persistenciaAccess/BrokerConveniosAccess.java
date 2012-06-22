package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.IPersistente;
import integrador.dominio.Convenio;
import integrador.dominio.ConvenioAdmin;
import integrador.persistencia.Broker;
import java.sql.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class BrokerConveniosAccess extends Broker {

    public BrokerConveniosAccess(Convenio objC) {
        super(objC);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        Convenio objC = (Convenio) arg;
        java.sql.Date fecha = new java.sql.Date(objC.getFechaIni().getTimeInMillis());
        return "INSERT INTO Convenios (conTipo,conNom,conFvigencia,conValor,conTipopago)"
                + "VALUES(" + objC.getTipo() + ",'" + objC.getNom() + "'," + fecha + ","
                + objC.getValor() + "," + objC.getTipoPago() + ")";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {
               Convenio objC = (Convenio) arg;
        return "UPDATE Convenios SET conValor=" + objC.getValor() + " WHERE conTipo=" + objC.getTipo();
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Convenio objC = (Convenio) arg;
        return "UPDATE Convenios SET conActivo=0 WHERE conTipo=" + objC.getTipo();
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM Convenios WHERE conActivo=1 ";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            Convenio objC = (Convenio) aux;
            objC.setNom(rs.getString("conNom"));
            objC.setTipo(rs.getInt("conTipo"));
            objC.setTipoPago(rs.getBoolean("conTipopago"));
            objC.setValor(rs.getInt("conValor"));
            Date fecha = rs.getDate("conFvigencia");
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            objC.setFechaIni(cal);


        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Convenio();
    }
}
