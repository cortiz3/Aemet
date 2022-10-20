package AEMET.model;

import AEMET.model.entities.Estacion;
import AEMET.API.APIUtils;
import AEMET.model.entities.EstMeteorologia;
import AEMET.model.entities.EstMeteorologiaPK;
import AEMET.model.entities.Provincia;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Cintia Ortiz
 */

   //Clase Integración para coger los datos desde la API y vincularlos en nuestras clases para después llamarlos desde el Controlador.

public class IntegracionDatos {

    /**
     * Métodos que crea Arrays para coger desde la API los datos y introducirlos
     * en nuestras clases.
     *
     * @return
     * @throws Exception
     */
    
    //Estacion es = Estacion de entidad.
    //Aemet.api.estacion
    //Se crea un arrayList con la entidad.
    public static ArrayList<Estacion> estaciones() throws Exception {

        //Se crea un arrayList cogiendo desde la API.
        ArrayList<AEMET.API.Estacion> estacionesAPI = APIUtils.getStations();

        //Se crea mi propio arrayList para mis entidad.
        ArrayList<AEMET.model.entities.Estacion> estacion = new ArrayList<>();

        //Se crea un for para cara ir añadiendo mi arrayList con el arrayList de la API.
        for (AEMET.API.Estacion estaciones : estacionesAPI) {
            Estacion e = new Estacion();

            e.setId(estaciones.getIndicativo());
            e.setNombre(estaciones.getNombre());

            //Se crea un nuevo objeto de provincia con el getProvincia de la api.
            e.setIDprovincia(new Provincia(estaciones.getProvincia()));

            estacion.add(e);

        }

        return estacion;

    }

    public static ArrayList<EstMeteorologia> obsMeteorologica(String id) throws Exception {

        ArrayList<AEMET.API.ObservacionEstacion> ObEstaciones = APIUtils.getObservacionEstacion(id);
        ArrayList<AEMET.model.entities.EstMeteorologia> EstMeteo = new ArrayList<>();

        for (AEMET.API.ObservacionEstacion observacionesEstaciones : ObEstaciones) {

            EstMeteorologia estM = new EstMeteorologia();

            estM.setEstMeteorologiaPK(new EstMeteorologiaPK(observacionesEstaciones.getIdema(), fecha(observacionesEstaciones.getFint())));
            estM.setUbicacion(observacionesEstaciones.getUbi());
            estM.setLongitud(new BigDecimal(observacionesEstaciones.getLon()));
            estM.setLatitud(new BigDecimal(observacionesEstaciones.getLat()));
            estM.setAltitud(new BigDecimal(observacionesEstaciones.getAlt()));
            estM.setPrecAcumulada(new BigDecimal(observacionesEstaciones.getPrec()));
            estM.setTempMinAire(new BigDecimal(observacionesEstaciones.getTamin()));
            estM.setTempMaxAire(new BigDecimal(observacionesEstaciones.getTamax()));
            estM.setPresion(new BigDecimal(observacionesEstaciones.getPres()));
            estM.setHumedad(new BigDecimal(observacionesEstaciones.getHr()));
            estM.setVeloMaxViento(new BigDecimal(observacionesEstaciones.getVmax()));
            estM.setVeloMediaViento(new BigDecimal(observacionesEstaciones.getVv()));
            estM.setDireccViento(new BigDecimal(observacionesEstaciones.getDv()));
            estM.setTempInstantanea(new BigDecimal(observacionesEstaciones.getTa()));
            estM.setDuracionInsolacion(new BigDecimal(observacionesEstaciones.getInso()));

            EstMeteo.add(estM);

        }

        return EstMeteo;

    }

    public static ArrayList<Provincia> provincia() throws Exception {

        ArrayList<AEMET.API.Estacion> estacionesAPI = APIUtils.getStations();
        ArrayList<AEMET.model.entities.Provincia> provincias = new ArrayList<>();

        for (AEMET.API.Estacion estaciones : estacionesAPI) {
            Provincia prov = new Provincia();
            prov.setNombre(estaciones.getProvincia());
            provincias.add(prov);
        }
        return provincias;
    }

    /**
     * Método para pasar de String a formato Date la Fecha/hora.
     *
     * @param fecha
     * @return
     * @throws ParseException
     */
    //Método para la fecha.
    public static Date fecha(String fecha) throws ParseException {

        fecha = fecha + "z";
//        fecha = fecha.replaceAll("T", " ");
//        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ZZZ");
//        Date fechaDate = null;

        if (fecha == null) {

            return Date.from(Instant.now());

        } else {
//            fechaDate = formato.parse(fecha);
            return Date.from(Instant.parse(fecha));
        }

    }
}
