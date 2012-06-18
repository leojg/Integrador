/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

/**
 * Clase encargada de establecer el acceso por parte de la interfaz a la capa logica
 * @author Administrador
 */
public class FachadaInterfaz {
    
    private static EstacionAdmin objEA = EstacionAdmin.getInstance();
    
    public static boolean altaEstacion(String nom, Integer cp) {
        return objEA.altaEstacion(crearEstacion(nom, cp));
    }
    
    private static Estacion crearEstacion(String nom, Integer cp) {
        nom = nom.toLowerCase();
        return new Estacion(nom, cp);
    } 
    
}
