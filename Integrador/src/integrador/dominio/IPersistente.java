/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.ArrayList;

//
//
//  @ Project : Untitled
//  @ File Name : IPersistente.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public interface IPersistente {

    public void guardar(Object arg);

    public void modifcar(Object arg);

    public void eliminar(Object arg);
   
    public ArrayList obtenerTodos();
    
    public ArrayList obtenerTodos(Object aux);
}
