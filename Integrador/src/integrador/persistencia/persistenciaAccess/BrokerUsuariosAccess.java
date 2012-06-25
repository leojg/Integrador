package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.IPersistente;
import integrador.dominio.Usuario;
import integrador.dominio.UsuarioAdmin;
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
//        Usuario objU = (Usuario) arg;
//        return "INSERT INTO Usuarios (conTipo,conNom,conFvigencia,conValor,conTipopago)"
//                + "VALUES(" + objU.getTipo() + ",'" + objU.getNom() + "',#" + fecha + "#,"
//                + objU.getValor() + "," + objU.getTipoPago() + ")";
        return "";
    }

    @Override
    public String getUpdateCommand(Object arg, Object aux) {
//               Usuario objU = (Usuario) arg;
//        return "UPDATE Usuarios SET conValor=" + objU.getValor() + " WHERE conTipo=" + objU.getTipo();
                return "";
    }

    @Override
    public String getDeleteCommand(Object arg, Object aux) {
//        Usuario objU = (Usuario) arg;
//        return "UPDATE Usuarios SET conActivo=0 WHERE conTipo=" + objU.getTipo();
                return "";
    }

    @Override
    public String getSelectCommand() {

        return "SELECT * FROM Usuarios WHERE conActivo=1 ";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
//        try {
//            Usuario objU = (Usuario) aux;
//            objU.setNom(rs.getString("conNom"));
//            objU.setTipo(rs.getInt("conTipo"));
//            objU.setTipoPago(rs.getBoolean("conTipopago"));
//            objU.setValor(rs.getInt("conValor"));
//            Date fecha = rs.getDate("conFvigencia");
//            GregorianCalendar cal = new GregorianCalendar();
//            cal.setTime(fecha);
//            objU.setFechaIni(cal);
//
//
//        } catch (SQLException e) {
//            System.out.println("Error al obtener");
//        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Usuario();
    }
}
