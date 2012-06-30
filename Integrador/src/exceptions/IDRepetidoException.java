/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class IDRepetidoException extends Exception {

    public IDRepetidoException() {
        super("El ID elegido está en uso");
    }

    public IDRepetidoException(String string) {
        super(string);
    }
}
