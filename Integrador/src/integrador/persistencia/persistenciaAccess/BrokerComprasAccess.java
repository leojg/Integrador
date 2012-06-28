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
        return "INSERT INTO Compras (compraID,usrCI,compraFecha,compraCantidad) "
                + "VALUES (" + objC.getId() + "," + objC.getObjU().getCI() + ",#" + fechahora + "#," + objC.getCantidadTickets() + ")";
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
            objC.setId(rs.getLong("compraID"));
            Usuario objU = new Usuario();
            objU.setCI(rs.getInt("usrCI"));
            objC.setObjU(objU);
            java.sql.Timestamp fecha = rs.getTimestamp("compraFecha");
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            objC.setFechaCompra(cal);
            objC.setCantidadTickets(rs.getInt("compraCantidad"));
            Compra.setUltimoID(objC.getId());

        } catch (SQLException e) {
            System.out.println("Error al obtener");
        }
    }

    @Override
    public IPersistente getNuevo() {
        return new Compra();
    }
}
