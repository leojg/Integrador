package integrador.persistencia.persistenciaAccess;

//
import integrador.Utilitaria;
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
public class BrokerComprasAccess extends Broker {

    public BrokerComprasAccess(Compra objC) {
        super(objC);
    }

    @Override
    public String getInsertCommand(Object arg, Object aux) {
        Compra objC = (Compra) arg;
        String fechahora = Utilitaria.ConvertirGCalendarStringHora(objC.getFechaCompra());
        return "INSERT INTO Compras (comp_id,usr_ci,comp_fecha,comp_cantidad,comp_costo) "
                + "VALUES (" + objC.getId() + "," + objC.getUsrCI() + ",#" + fechahora + "#," 
                + objC.getCantidadTickets() + "," + objC.getCosto() + ")";
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

        return "SELECT * FROM Compras";

    }

    @Override
    public void obtenerDesdeResultSet(ResultSet rs, Object aux, Object dato) {
        try {
            Compra objC = (Compra) aux;
            objC.setId(rs.getLong("comp_id"));
            objC.setUsrCI(rs.getInt("usr_ci"));
            java.sql.Timestamp fecha = rs.getTimestamp("comp_fecha");
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            objC.setFechaCompra(cal);
            objC.setCantidadTickets(rs.getInt("comp_cantidad"));
            Compra.setUltimoID(objC.getId());
            objC.setCosto(rs.getInt("comp_costo"));

        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Compra();
    }
}
