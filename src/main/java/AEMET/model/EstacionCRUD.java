package AEMET.model;


import static AEMET.model.IntegracionDatos.provincia;
import AEMET.model.entities.EstMeteorologia;
import AEMET.model.entities.Estacion;
import AEMET.model.entities.Provincia;
import java.util.ArrayList;
import java.util.Hashtable;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author cortiz3
 */
public class EstacionCRUD {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

/**
 * Método que devuelve el ID
 * 
 * @param id
 * @return 
 */
    public static Estacion findById(String id) {
        Session session = SESSION_FACTORY.openSession();

       Query query = session.getNamedQuery("Estacion.findById").setString("id", id);
        ArrayList<Estacion> estaciones = (ArrayList<Estacion>) query.list();

        session.close();

        return ((estaciones != null)&&(estaciones.size()>0)) ? estaciones.get(0) : null;
        //Si estaciones no es null y es mayor a 0, que coja de estaciones y los añada.
        
    }
    /**
     * Método para insertar datos de Estación.
     * 
     * @param e 
     */
    public static void insert(Estacion e) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();

    }
    /**
     * Método para insertar datos en forma de arrayList Estación desde la API.
     * 
     * @param est 
     */
    public static void insert(ArrayList<Estacion> est){
        
         Hashtable<String, Estacion> EstacionHashtable = new Hashtable<String, Estacion>();

        // Con hashTable guardo las estaciones,
        // En el for busco en la clase integración y busca en el crud el nombre.
        // si el nombre está en la base de datos que no añada nada.
        //Si  es == null hace un getId de estaciones en el Hastable.
        for (Estacion estaciones : est) {

            if (EstacionCRUD.findById(estaciones.getId()) == null) {
                EstacionHashtable.put(estaciones.getId(), estaciones);
              
            }

        }

        //Con este for, en el hastable haga que cada linea inserte el id y el valor.
       EstacionHashtable.forEach((id, estacion) -> {
            
           //Se crea un objeto provincia que es igual al objeto de idProvincia que tiene estación.
           //Se crea otro objeto provincia que tiene el objeto de idprovincia de la estacion.
            Provincia prov = estacion.getIDprovincia();
            Provincia provId = ProvinciaCRUD.findByNombre(prov.getNombre());
            
            
            estacion.setIDprovincia(provId);
            
           insert(estacion);
            
           
        });
    }
    /**
     * Método para actualizar los datos.
     * 
     * @param e 
     */
     public static void update(Estacion e) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        session.close();
    }
/**
 * Método para eliminar datos.
 * 
 * @param e 
 */
     public static void delete(Estacion e) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.delete(e);
        session.getTransaction().commit();
        session.close();
    }
     /**
      * Método para eleminar la ID.
      * 
      * @param id 
      */
      public static void delete(String id) {
      Estacion e = EstacionCRUD.findById(id);
       EstacionCRUD.delete(e);

    }
      /**
       * Método que devuelve una lista en ArrayList los datos.
       * 
       * @return 
       */
      public static ArrayList<Estacion> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Estacion.findAll");
        ArrayList<Estacion> estaciones = (ArrayList<Estacion>) query.list();
        session.close();
        return estaciones;
    }
      /**
       * Devuelve una lista de las provincias.
       * 
       * @param p
       * @return 
       */
      public static ArrayList<Estacion> lista(Provincia p) {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT e FROM Estacion e WHERE e.iDprovincia = :p").setParameter("p", p);
        ArrayList<Estacion> estaciones = (ArrayList<Estacion>) query.list();
        session.close();
        return estaciones;
    }
      
    
      
      
      
}
