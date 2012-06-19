/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class FormatoLineaIncorrectoException extends Exception {

    public FormatoLineaIncorrectoException() {
        super("El nombre de Linea debe contar de un caracter alfabetico seguido de dos digitos. EJ: A12");
    }

    public FormatoLineaIncorrectoException(String string) {
        super(string);
    }
    
    
    
}
