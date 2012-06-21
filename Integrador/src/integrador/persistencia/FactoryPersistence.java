package integrador.persistencia;



import integrador.dominio.IPersistente;




//
//
//  @ Project : Untitled
//  @ File Name : FactoryPersistence.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//
public abstract class FactoryPersistence {  
    private BaseDeDatos objBD;

    public BaseDeDatos getObjBD() {
        return objBD;
    }
    public abstract Broker createPersistence(IPersistente objP, Object arg); 
}
