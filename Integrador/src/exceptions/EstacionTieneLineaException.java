/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class EstacionTieneLineaException extends Exception {

    public EstacionTieneLineaException() {
        super("La estacion elegida tiene una o mas lineas asociadas. No se puede eliminar.");
    }

    public EstacionTieneLineaException(String string) {
        super(string);
    }
}
