/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Administrador
 */
public class UsuariosAsociadosException extends Exception {

    public UsuariosAsociadosException() {
        super("Este Convenio tiene usuarios asociados. No se puede continuar");
    }

    public UsuariosAsociadosException(String string) {
        super(string);
    }
}
