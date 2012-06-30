package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.*;
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
public class BrokerUsuariosAccess extends Broker {

    public BrokerUsuariosAccess(Usuario objU) {
        super(objU);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        Usuario objU = (Usuario) arg;
        java.sql.Date fecha = new java.sql.Date(objU.getFechaNac().getTimeInMillis());
        java.sql.Date fechaReg = new java.sql.Date(objU.getFechaRegistro().getTimeInMillis());       
        return "INSERT INTO Usuarios (usrCI,usrNom,usrFNac,usrDir,usrBarrio,usrCP,usrMail,usrTel,usrFRegistro)"
                + "VALUES(" + objU.getCI() + ",'" + objU.getNom() + "',#" + fecha + "#,'"
                + objU.getDir() + "','" + objU.getBarrio() + "'," + objU.getCP()
                + ",'" + objU.getMail() + "'," + objU.getTel() + ",#" + fecha + "#)";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {

        Usuario objU = (Usuario) arg;
        if (aux != null && aux.getClass() == Convenio.class) {
            Convenio objC = (Convenio) aux;
            return "UPDATE Usuarios SET conTipo=" + objC.getTipo() + " WHERE usrCI=" + objU.getCI();
        } else {
            java.sql.Date fecha = new java.sql.Date(objU.getFechaNac().getTimeInMillis());
            return "UPDATE Usuarios SET usrNom='" + objU.getNom() + "', usrFNac=#" + fecha + "#, usrDir='" + objU.getDir() + "', usrBarrio='" + objU.getBarrio() + "', usrCP=" + objU.getCP()
                    + ", usrMail='" + objU.getMail() + "', usrTel=" + objU.getTel()
                    + " WHERE usrCI=" + objU.getCI();
        }
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Usuario objU = (Usuario) arg;
        return "UPDATE Usuarios SET usrActivo=0 WHERE usrCI=" + objU.getCI();
    }

    @Override
    public String getSelectCommand() {
        return "SELECT * FROM Usuarios WHERE usrActivo=1 ";
    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            Usuario objU = (Usuario) aux;
            objU.setCI(rs.getInt("usrCI"));
            objU.setNom(rs.getString("usrNom"));
            objU.setCP(rs.getInt("usrCP"));
            objU.setMail(rs.getString("usrMail"));
            objU.setTel(rs.getInt("usrTel"));
            objU.setDir(rs.getString("usrDir"));
            objU.setBarrio(rs.getString("usrBarrio"));
            Date fecha = rs.getDate("usrFNac");
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            objU.setFechaNac(cal);
            Date fechaReg = rs.getDate("usrFRegistro");
            GregorianCalendar calReg = new GregorianCalendar();
            calReg.setTime(fechaReg);
            objU.setFechaRegistro(calReg);
            Convenio objC = new Convenio();
            objC.setTipo(rs.getInt("conTipo"));
            objU.setConvenio(objC);

        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Usuario();
    }
}
