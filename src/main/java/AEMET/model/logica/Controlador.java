package AEMET.model.logica;

import AEMET.model.EstMeteorologiaCRUD;
import AEMET.model.EstacionCRUD;
import AEMET.model.IntegracionDatos;

import AEMET.model.ProvinciaCRUD;
import AEMET.model.entities.EstMeteorologia;
import AEMET.model.entities.Estacion;
import AEMET.model.entities.Provincia;
import java.util.ArrayList;
import java.util.Date;



/**
 *
 * @author Cintia Ortiz
 */

// Clase Controlador que coge métodos del CRUD llamados a la integración para insertar los datos.
// Estos métodos se llamarán en el Main.
public class Controlador {
  
    
    //Los métodos están conectados a integración.
    
    /**
     * Métodos que llaman desde el CRUD para insertar los datos desde los métodos de la integación.
     * 
     * Método de insertar las Provincias.
     * 
     * @throws Exception 
     */
    
    public static void insertProvincias() throws Exception{
        
        ProvinciaCRUD.insert(IntegracionDatos.provincia());
      
        
    }
    
    /**
     * Método para insertar las Estaciones.
     * 
     * @throws Exception 
     */
    
    public static void insertEstaciones() throws Exception{
        
        EstacionCRUD.insert(IntegracionDatos.estaciones());
    }
    
    /**
     * Método que devuelve el ID de EstaMeteorologías.
     * 
     * @param id
     * @throws Exception 
     */
    
    public static void insertEst_Met(String id) throws  Exception{
        
        EstMeteorologiaCRUD.insert(IntegracionDatos.obsMeteorologica(id), id);

        
    }

    /**
     * Método para hacer una query que haga una lista de las provincias.
     * 
     */
    public static ArrayList<Provincia> listarProvincias(){
    
      return ProvinciaCRUD.lista();
        
       

    }
    
    /**
     * Devuelve una lista de estaciones.
     * 
     * @param p
     * @return 
     */
    public static ArrayList<Estacion>ListarEstaciones(Provincia p){
        return EstacionCRUD.lista(p);
        
    }
    
    /**
     * Devuelve una lista de la estación meteorología.
     * 
     * @return 
     */
//    public static ArrayList<EstMeteorologia>ListarDatos(){
//    return EstMeteorologiaCRUD.findAll();
//}

    /**
     * Devuelve una lista de los datos de las estaciones.
     * 
     * @param 
     * @return 
     */
   
    public static ArrayList<EstMeteorologia>ListarDatosEstaciones(Estacion e){
        return EstMeteorologiaCRUD.datosEstaciones(e);
    }
    
    /**
     * Lista todas las estaciones
     * 
     * @return 
     */
    public static ArrayList<Estacion>AllEstattions(){
        return EstacionCRUD.findAll();
    }
    
    /**
     * Coge párametro de fechas.
     * 
     * @param fecha1
     * @param fecha2
     * @param e
     * @return 
     */
    public static ArrayList<EstMeteorologia>Fechas(Date fecha1,Date fecha2, Estacion e){
        return EstMeteorologiaCRUD.Fecha(fecha1, fecha2, e);
    }
    
}
