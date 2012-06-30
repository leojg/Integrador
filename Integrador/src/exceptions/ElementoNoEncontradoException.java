/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class ElementoNoEncontradoException extends Exception {

    public ElementoNoEncontradoException() {
        super("No se encontró ningun valor coincidente.");
    }

    public ElementoNoEncontradoException(String string) {
        super(string);
    }
}
