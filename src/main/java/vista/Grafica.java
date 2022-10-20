package vista;

import AEMET.model.entities.EstMeteorologia;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;



/**
 *
 * @author Cintia Ortiz
 */
public class Grafica extends JFrame {

    private ArrayList<EstMeteorologia> meteorologia;

    /**
     * Constructor
     *
     * @param meteorologia
     * @throws HeadlessException
     */
    public Grafica(ArrayList<EstMeteorologia> meteorologia) throws HeadlessException {
        this.meteorologia = meteorologia;
    }

    /**
     * Método para crear la gráfica de Temperatura Instantánea.
     *
     * @return
     */
    public ChartPanel TemperaturaInstantanea() {

        TimeSeries hora = new TimeSeries("Temperatura");

        for (EstMeteorologia meteorologia1 : meteorologia) {

            hora.add(new FixedMillisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()), meteorologia1.getTempInstantanea());
            
        }
        
        TimeSeriesCollection temp = new TimeSeriesCollection(hora);
        
       JFreeChart chart = ChartFactory.createTimeSeriesChart("Temperatura Instantánea", "Hora", "ºC", temp, true, true, false);

        ChartPanel gTemperatura = new ChartPanel(chart);
        


        return gTemperatura;
    }

    /**
     * Método para crear la gráfica de Presión.
     *
     * @return
     */
    public ChartPanel Presion() {

       TimeSeries hora = new TimeSeries("Presión");

        for (EstMeteorologia meteorologia1 : meteorologia) {
       hora.add(new Millisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()),meteorologia1.getPresion());

        }

TimeSeriesCollection pres = new TimeSeriesCollection(hora);

JFreeChart chart = ChartFactory.createTimeSeriesChart("Presión", "Hora", "Pa", pres, true, true, false);

        ChartPanel gPresion = new ChartPanel(chart);
        return gPresion;

    }

    /**
     * Método para crear la gráfica de Humedad.
     *
     * @return
     */
    public ChartPanel Humedad() {
        
        TimeSeries hora = new TimeSeries("Humedad");
        
        for (EstMeteorologia meteorologia1 : meteorologia) {
               hora.add(new Millisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()),meteorologia1.getHumedad());
        }

        TimeSeriesCollection hum = new TimeSeriesCollection(hora);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Humedad", "Hora", "%m", hum, true, true, false);
        ChartPanel gHumedad = new ChartPanel(chart);
        return gHumedad;
    }

    /**
     * Método para crear la gráfica de Precipitación.
     *
     * @return
     */
    public ChartPanel Precipitacion() {
              TimeSeries hora = new TimeSeries("Precipitación Acumulada");
        for (EstMeteorologia meteorologia1 : meteorologia) {
              hora.add(new Millisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()),meteorologia1.getPrecAcumulada());
        }
        TimeSeriesCollection precA = new TimeSeriesCollection(hora);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Precipitación", "Fecha-Hora", "mm", precA,true, true, false);
        
        ChartPanel gPrecipitacion = new ChartPanel(chart);
        return gPrecipitacion;
    }

    /**
     * Método para crear la gráfica de Velocidad de viento.
     *
     * @return
     */
    public ChartPanel VelocidadViento() {
     TimeSeries hora = new TimeSeries("Velocidad del viento");
        
        for (EstMeteorologia meteorologia1 : meteorologia) {
           hora.add(new Millisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()),meteorologia1.getVeloMaxViento());
        }
        TimeSeriesCollection vV = new TimeSeriesCollection(hora);
        
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Velocidad del viento", "Fecha-Hora", "Km/h", vV, true, true, false);
        ChartPanel gVelocidadV = new ChartPanel(chart);
        return gVelocidadV;
    }

    /**
     * Método para crear la gráfica de Dirección de viento.
     *
     * @return
     */
    public ChartPanel DireccionViento() {
           TimeSeries hora = new TimeSeries("Dirección de viento");

        for (EstMeteorologia meteorologia1 : meteorologia) {
         hora.add(new Millisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()),meteorologia1.getDireccViento());
        }
        TimeSeriesCollection dV = new TimeSeriesCollection(hora);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Dirección del viento", "Fecha-Hora", "Nudos", dV,  true, true, false);
        ChartPanel gDireccionV = new ChartPanel(chart);
        return gDireccionV;
    }

    /**
     * Método para crear la gráfica de Insolación.
     *
     * @return
     */
    public ChartPanel Insolación() {
        TimeSeries hora = new TimeSeries("Insolación");
   
        for (EstMeteorologia meteorologia1 : meteorologia) {
            hora.add(new Millisecond(meteorologia1.getEstMeteorologiaPK().getFechaHora()),meteorologia1.getDuracionInsolacion());
        }
        TimeSeriesCollection ins = new TimeSeriesCollection(hora);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Insolación", "Fecha-Hora", "kWh/m2", ins, true, true, false);
        ChartPanel gInsolacion = new ChartPanel(chart);
        return gInsolacion;
    }
    
}
