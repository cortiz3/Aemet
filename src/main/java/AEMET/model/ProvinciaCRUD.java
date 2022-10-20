package AEMET.model;


import AEMET.model.entities.Provincia;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Cintia Ortiz
 */
public class ProvinciaCRUD {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

/**
 * Método que devuelve el ID.
 * 
 * @param id
 * @return 
 */
    public static Provincia findById(Integer id) {
        Session session = SESSION_FACTORY.openSession();

        Query query = session.getNamedQuery("Provincia.findById").setInteger("ID", id);
        ArrayList<Provincia> provincias = (ArrayList<Provincia>) query.list();

        session.close();

        return (provincias != null) ? provincias.get(0) : null;
    }
/**
 * Método para insertar datos en Provincia.
 * 
 * @param prov 
 */
    public static void insert(Provincia prov) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(prov);
        session.getTransaction().commit();
        session.close();

    }
    
    /**
     * Método para insertar los datos desde la API en ArrayList en Provincia.
     * 
     * @param prov 
     */
    
    public static void insert(ArrayList<Provincia> prov){
        
         Hashtable<String, Provincia> ProvinciasHashtable = new Hashtable<String, Provincia>();

        // Con hashTable guardo las provincias,
        // En el for busco en la clase integración y busca en el crud el nombre.
        // si el nombre está en la base de datos que no añada nada.
        //Si  es == null hace un getNombre de provincias en el Hastable.
        for (Provincia provincias : prov) {

            if (ProvinciaCRUD.findByNombre(provincias.getNombre()) == null) {
                ProvinciasHashtable.put(provincias.getNombre(), provincias);
            }

        }

        //Con este for, en el hastable haga que cada linea inserte el id y el valor.
        ProvinciasHashtable.forEach((id, valor) -> {
            insert(valor);
        });
    }

    /**
     * Método para actualizar los datos.
     * 
     * @param prov 
     */
    public static void update(Provincia prov) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.update(prov);
        session.getTransaction().commit();
        session.close();
    }
/**
 * Método para eliminar los datos.
 * 
 * @param prov 
 */
    public static void delete(Provincia prov) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.delete(prov);
        session.getTransaction().commit();
        session.close();
    }
/**
 * Método para eliminar el ID.
 * 
 * @param id 
 */
    public static void delete(Integer id) {
        Provincia prov = ProvinciaCRUD.findById(id);
        ProvinciaCRUD.delete(prov);

    }

    
    /**
     * Método que devuelve el nombre de las Provincias en ArrayList.
     * 
     * @param Nombre
     * @return 
     */
    public static Provincia findByNombre(String Nombre) {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Provincia.findByNombre").setParameter("nombre", Nombre);
        Provincia provincias = (Provincia) query.uniqueResult();
        session.close();
        return provincias;
    }
    
    /**
     * Método que devuelve una lista de las provincias.
     * 
     * @return 
     */
    public static ArrayList<Provincia> lista(){
        Session session = SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM Provincia p ORDER BY p.nombre");
        ArrayList<Provincia> provi = (ArrayList<Provincia>) query.list();
         for(Provincia p : provi){
            
            System.out.println(p.getId()+p.getNombre());
        }
         session.close();
         return  provi;
    }
}
