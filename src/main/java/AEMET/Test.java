package AEMET;

import AEMET.model.HibernateUtil;
import AEMET.model.entities.EstMeteorologia;
import AEMET.model.entities.Estacion;
import AEMET.model.entities.Provincia;
import AEMET.model.logica.Controlador;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author cortiz3
 */
public class Test {
    public static void main(String[] args) {
        

        ArrayList<Estacion> estaciones = Controlador.AllEstattions();
        for (Estacion estacione : estaciones) {
            try {
                Controlador.insertEst_Met(estacione.getId());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No hay datos para esa estación.", "Error al insertar estaciones", JOptionPane.ERROR_MESSAGE);
            }finally{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        System.out.println("-----------------FIN---------------------------");
        
    }
}
