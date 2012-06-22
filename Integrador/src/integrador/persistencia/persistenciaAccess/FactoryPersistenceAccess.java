package integrador.persistencia.persistenciaAccess;

//
import integrador.dominio.Convenio;
import integrador.dominio.Estacion;
import integrador.dominio.IPersistente;
import integrador.dominio.Linea;
import integrador.persistencia.Broker;

import integrador.persistencia.FactoryPersistence;

//
//
//  @ Project : Untitled
//  @ File Name : FactoryPersistenceAccess.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public class FactoryPersistenceAccess extends FactoryPersistence {

    @Override
    public Broker createPersistence(IPersistente objP, Object arg) {

        if (arg == null) {

            if (objP.getClass() == Linea.class) {
                return new BrokerLineasAccess((Linea) objP);
            } else if (objP.getClass() == Estacion.class) {
                return new BrokerEstacionesAccess((Estacion) objP);
            } else if (objP.getClass() == Convenio.class) {
                return new BrokerConveniosAccess((Convenio) objP);
            }
            return null;
        } else if (objP.getClass() == Linea.class && arg.getClass() == Estacion.class) {
            return new BrokerEstacionesLineasAccess((Linea) objP);
        }
        return null;
    }
}
