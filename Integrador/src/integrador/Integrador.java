/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador;

import integrador.Interfaz.*;
import integrador.dominio.Estacion;
import integrador.dominio.EstacionAdmin;
import integrador.dominio.LineaAdmin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Integrador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EstacionAdmin ea = EstacionAdmin.getInstance();
        LineaAdmin la = LineaAdmin.getInstance();
        MantenimientoEstacion frm = new MantenimientoEstacion();
        frm.setVisible(true);
    }
}
