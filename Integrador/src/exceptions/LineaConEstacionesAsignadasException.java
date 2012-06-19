/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class LineaConEstacionesAsignadasException extends Exception {

    public LineaConEstacionesAsignadasException() {
        super("La linea elegida tiene estaciones asignadas. Quitelas antes de continuar.");
    }

    public LineaConEstacionesAsignadasException(String string) {
        super(string);
    }
}
