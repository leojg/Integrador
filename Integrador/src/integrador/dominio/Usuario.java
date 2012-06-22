/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.GregorianCalendar;

/**
 * Esta clase representa los usuarios del sistema
 * @author Administrador
 */
public class Usuario {
    private Integer CI;
    private String nom;
    private GregorianCalendar fechaNac;
    private String dir;
    private String barrio;
    private Integer CP;
    private String mail;
    private Integer tel;
    private Convenio convenio;

    public Usuario(Integer CI, String nom, GregorianCalendar fechaNac, String dir, String barrio, Integer CP, String mail, Integer tel, Convenio convenio) {
        this.CI = CI;
        this.nom = nom;
        this.fechaNac = fechaNac;
        this.dir = dir;
        this.barrio = barrio;
        this.CP = CP;
        this.mail = mail;
        this.tel = tel;
        this.convenio = convenio;
    }

    public Integer getCI() {
        return CI;
    }

    public void setCI(Integer CI) {
        this.CI = CI;
    }

    public Integer getCP() {
        return CP;
    }

    public void setCP(Integer CP) {
        this.CP = CP;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public GregorianCalendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(GregorianCalendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return CI + " - " + nom + " Domicilio:" + barrio + ", " + dir + " " + CP + " Tel:" + tel + " eMail:" + mail + " Fecha de Nacimiento:" + fechaNac;
    }
    
    
    
    
}
