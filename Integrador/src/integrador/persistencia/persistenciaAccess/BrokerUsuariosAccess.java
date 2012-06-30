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
        return "INSERT INTO Usuarios (usr_ci,usr_nom,usr_fnac,usr_dir,usr_barrio,cp_num,usr_mail,"
                + "usr_tel,usr_fregistro)"
                + "VALUES(" + objU.getCI() + ",'" + objU.getNom() + "',#" + fecha + "#,'"
                + objU.getDir() + "','" + objU.getBarrio() + "'," + objU.getCP()
                + ",'" + objU.getMail() + "'," + objU.getTel() + ",#" + fecha + "#)";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {

        Usuario objU = (Usuario) arg;
        if (aux != null && aux.getClass() == Convenio.class) {
            Convenio objC = (Convenio) aux;
            return "UPDATE Usuarios SET con_tipo=" + objC.getTipo() + " WHERE usr_ci=" + objU.getCI() + " and usr_activo=1";
        } else {
            java.sql.Date fecha = new java.sql.Date(objU.getFechaNac().getTimeInMillis());
            return "UPDATE Usuarios SET usr_nom='" + objU.getNom() + "', usr_fnac=#" + fecha 
                    + "#, usr_dir='" + objU.getDir() + "', usr_barrio='" + objU.getBarrio() 
                    + "', usr_cp=" + objU.getCP() + ", usr_mail='" + objU.getMail() 
                    + "', usr_tel=" + objU.getTel() + " WHERE usr_ci=" + objU.getCI() + " and usr_activo=1";
        }
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
        Usuario objU = (Usuario) arg;
        return "UPDATE Usuarios SET usr_activo=0 WHERE usr_ci=" + objU.getCI();
    }

    @Override
    public String getSelectCommand() {
        return "SELECT * FROM Usuarios WHERE usr_activo=1 ";
    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            Usuario objU = (Usuario) aux;
            objU.setCI(rs.getInt("usr_ci"));
            objU.setNom(rs.getString("usr_nom"));
            objU.setCP(rs.getInt("cp_num"));
            objU.setMail(rs.getString("usr_mail"));
            objU.setTel(rs.getInt("usr_tel"));
            objU.setDir(rs.getString("usr_dir"));
            objU.setBarrio(rs.getString("usr_barrio"));
            Date fecha = rs.getDate("usr_fnac");
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            objU.setFechaNac(cal);
            Date fechaReg = rs.getDate("usr_fregistro");
            GregorianCalendar calReg = new GregorianCalendar();
            calReg.setTime(fechaReg);
            objU.setFechaRegistro(calReg);
            Convenio objC = new Convenio();
            objC.setTipo(rs.getInt("con_tipo"));
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
