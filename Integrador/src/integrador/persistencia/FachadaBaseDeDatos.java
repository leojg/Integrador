package integrador.persistencia;

//


import integrador.dominio.IPersistente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import integrador.persistencia.persistenciaAccess.FactoryPersistenceAccess;

//
//
//  @ Project : Untitled
//  @ File Name : FachadaBaseDeDatos.java
//  @ Date : 15/08/2011
//  @ Author : 
//
//




public class FachadaBaseDeDatos {
	private FactoryPersistence fabricaPers ;
	private static FachadaBaseDeDatos objGBD = null;
	public Broker obtenerBroker(IPersistente objP, Object arg) {
            return fabricaPers.createPersistence(objP, arg);
	}
	
	public static FachadaBaseDeDatos getInstance() {
            if (objGBD==null){
               objGBD = new FachadaBaseDeDatos();
            }
            return objGBD;	
	}

    private FachadaBaseDeDatos() {
        fabricaPers = new FactoryPersistenceAccess();
       }

    
    
   
        
        
        
    


        
        
}
