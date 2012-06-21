package integrador.persistencia;


import integrador.Integrador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//
//
//
//  @ Project : Untitled
//  @ File Name : BaseDeDatos.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public class BaseDeDatos {
    //contraseña a la base de datos si es que tuviera, si no se deja vacio
   private static String password = "";
    //nombre de la base de datos Acces con extension *.mdb o *.accdb
    private static String dbName = "Integrador.accdb";
    //direccion de la base de datos
    private static String bd = System.getProperty("user.dir") + "\\" + dbName + ";PWD=" + password;
    //driver para base de datos Access 2000, 2003, 2007, 2010
    private static String url = "jdbc:odbc:;DRIVER=Microsoft Access Driver (*.mdb, *.accdb);DBQ=" + bd;
    Connection conn = null;
    private static BaseDeDatos instance = null;
    
   private BaseDeDatos() {
      try{
         //obtenemos el driver para Access 
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         //obtenemos la conexión         
         conn = DriverManager.getConnection(url);
         //si la conexion tuvo exito
         if (conn!=null){
            System.out.println("Conexión a base de datos "+bd+". listo");
         }
      }catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }
   }
   
   public static BaseDeDatos GetInstance() {
       if (instance == null) 
           instance = new BaseDeDatos();
       return instance;
   }
   
   /**Permite retornar la conexión*/
   public Connection getConexion(){
      return conn;
   }

   //como dice su nombre, termina la conexion a la base de datos
   public void desconectar(){
        try {
            conn.close();
            //conn = null;
            System.out.println("La conexion a la  base de datos " + bd + " a terminado");
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
}
