/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class NoExisteCPException extends Exception {

    public NoExisteCPException() {
        super("El Codigo Postal ingresado no es valido");
    }

    public NoExisteCPException(String string) {
        super(string);
    }
}
