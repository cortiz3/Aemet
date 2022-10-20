package AEMET.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 *
 * @author cortiz3
 * 
 * Clase para abrir sessión con hibernate.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
     
    static {
        Configuration conf = new Configuration();
        conf.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        try {
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Error a la creacio del SessionFactory." + e);
            throw new ExceptionInInitializerError(e);
        }       
    }
     
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
