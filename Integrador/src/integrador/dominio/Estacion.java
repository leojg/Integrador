/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

/**
 * Clase que representa las estaciones. El conjunto de estaciones forma una linea.
 * @author Administrador
 */

public class Estacion {
    
    private String nom;
    private Integer cp;

    public Estacion(String nom, Integer cp) {
        this.nom = nom;
        this.cp = cp;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Nombre:" + this.nom + "  C.P.:" + this.cp;
    }

    
    
}
