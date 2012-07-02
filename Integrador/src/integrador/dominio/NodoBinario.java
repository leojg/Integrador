/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrador.dominio;

import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class NodoBinario <AnyType> implements IPersistente
{

    //atributos amistosos
    AnyType dato;
    NodoBinario <AnyType> izquierdo;
    NodoBinario <AnyType> derecho;
    int altura; //para arbolrd AVL
    /** Creates a new instance of NodoBinario*/
    public NodoBinario(AnyType dt)
    {
        this(dt, null, null);
    }

    public NodoBinario(AnyType dt, NodoBinario<AnyType> izq, NodoBinario<AnyType> der)
    {
        dato = dt;
        izquierdo = izq;
        derecho = der;
        altura = 0;
    }

    public NodoBinario() {
        this(null,null,null);
    }
    
        public void setNodo(AnyType dt, NodoBinario<AnyType> izq, NodoBinario<AnyType> der)
    {
        dato = dt;
        izquierdo = izq;
        derecho = der;
        altura = 0;
    }

    public AnyType getDato() {
        return dato;
    }

    public void setDato(AnyType dato) {
        this.dato = dato;
    }

    public NodoBinario<AnyType> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBinario<AnyType> derecho) {
        this.derecho = derecho;
    }

    public NodoBinario<AnyType> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBinario<AnyType> izquierdo) {
        this.izquierdo = izquierdo;
    }
        
    @Override
    public void guardar(Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void modifcar(Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList obtenerTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList obtenerTodos(Object aux) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
