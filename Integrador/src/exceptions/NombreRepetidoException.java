/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class NombreRepetidoException extends Exception {

    public NombreRepetidoException() {
        super("El nombre elegido está en uso");
    }

    public NombreRepetidoException(String string) {
        super(string);
    }
}
