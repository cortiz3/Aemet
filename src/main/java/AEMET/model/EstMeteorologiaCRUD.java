package AEMET.model;

import AEMET.model.entities.Estacion;
import AEMET.model.entities.EstMeteorologia;
import AEMET.model.entities.EstMeteorologiaPK;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Cintia Ortiz
 */


public class EstMeteorologiaCRUD {

        private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    public static EstMeteorologia findByIDEstacion(String id) {
        Session session = SESSION_FACTORY.openSession();

        Query query = session.getNamedQuery("EstMeteorologia.findByIDEstacion").setString("ID_Estacion", id);
        ArrayList<EstMeteorologia> estMeteorologias = (ArrayList<EstMeteorologia>) query.list();

        session.close();

        return ((estMeteorologias != null) && (estMeteorologias.size() > 0)) ? estMeteorologias.get(0) : null;
    }
/**
 * Método para insertar datos
 * 
 * @param em 
 */
    private static void insert(EstMeteorologia em) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(em);
        session.getTransaction().commit();
        session.close();

    }
/**
 * Método para insertar datos desde la API
 * 
 * @param estMetApi
 * @param id 
 */
    public static void insert(ArrayList<EstMeteorologia> estMetApi, String id) {

        //Se crea un for-each de Estmeteorologias cogiendo las pk de findById, si es nulo que añada datos.
        ArrayList<EstMeteorologia> meteorologia = new ArrayList<>();

        for (EstMeteorologia estMet : estMetApi) {
            if (EstMeteorologiaCRUD.findById(estMet.getEstMeteorologiaPK()) == null) {
                meteorologia.add(estMet);
            }
        }

        for (EstMeteorologia prediccion1 : meteorologia) {
            insert(prediccion1);
        }

    }
 /**
  * Método que devuelva una lista de estaMeteorología
  * 
  * @return 
  */
    public static ArrayList<EstMeteorologia> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("EstMeteorologia.findAll");
        ArrayList<EstMeteorologia> estMeteorologias = (ArrayList<EstMeteorologia>) query.list();
        session.close();
        return estMeteorologias;
    }

    /**
     * Método que devuelve la PK de EstMeteorología.
     * 
     * @param pk
     * @return 
     */
    
    //Se crea un findById con las pk de la clase PK. Con el se pasará en el insert.
    public static EstMeteorologia findById(EstMeteorologiaPK pk) {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("EstMeteorologia.findByID").setParameter("id", pk);
        EstMeteorologia estMeteorologias = (EstMeteorologia) query.uniqueResult();
        session.close();
        return estMeteorologias;
    }
    
    /**
     * Lista la estación por id.
     * 
     * @param e
     * @return 
     */
    public static ArrayList<EstMeteorologia> datosEstaciones(Estacion e){
        Session session = SESSION_FACTORY.openSession();
         Query query = session.getNamedQuery("EstMeteorologia.findByIDEstacion").setParameter("iDEstacion", e.getId());
         ArrayList<EstMeteorologia> estM = (ArrayList<EstMeteorologia>) query.list();
         session.close();
         return estM;
        
    }
    
    /**
     * Devuelve la fecha entre un rango.
     * 
     * @param fecha1
     * @param fecha2
     * @param e Id estación
     * @return 
     */
    public static ArrayList<EstMeteorologia> Fecha(Date fecha1, Date fecha2, Estacion e){
                Session session = SESSION_FACTORY.openSession();
                Query query = 
                        session.createQuery("SELECT m FROM EstMeteorologia as m WHERE date(m.estMeteorologiaPK.fechaHora) >= :fecha1 AND  date(m.estMeteorologiaPK.fechaHora) <= :fecha2 AND m.estMeteorologiaPK.iDEstacion = :e")
                        .setParameter("fecha1", fecha1)
                        .setParameter("fecha2", fecha2)
                        .setParameter("e", e.getId());
                
                ArrayList<EstMeteorologia>estM = (ArrayList<EstMeteorologia>)query.list();
                session.close();
                return estM;
    }
   
}
