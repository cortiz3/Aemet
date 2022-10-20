/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.API;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author david Importar certificat amb sudo keytool -import -trustcacerts
 * -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -alias Root
 * -import -file {rutaNetbeans}/AEMET/\*aemetes.crt introduir clau d'usuari i
 * dir yes a "trust this certificate?"
 */
public class APIUtils {

    private static final String key = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaW50aWlhLjM0OUBnbWFpbC5jb20iLCJqdGkiOiI1MTQ0MWExNy03OThmLTRiZTMtYjY5Yi1kY2I2OTBlYTc0MDUiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTU1MzUzODE3OSwidXNlcklkIjoiNTE0NDFhMTctNzk4Zi00YmUzLWI2OWItZGNiNjkwZWE3NDA1Iiwicm9sZSI6IiJ9.8uqqbHydVcckkhxi2Yk9WNkjn4Si7aVNv8-ekrOMPzE";

    private static final String URLWebService = "https://opendata.aemet.es/opendata/api/";

    private static final String URLInventarioEstaciones = "/valores/climatologicos/inventarioestaciones/todasestaciones";

    private static final String URLObservacionEstacion = "/observacion/convencional/datos/estacion/";

    public static final ArrayList<Estacion> getStations() throws Exception {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URLWebService)
                .path(URLInventarioEstaciones)
                .queryParam("api_key", key);

        Gson gson = new Gson();
        String stationsJson = obtenirDadesDeTarget(client, target);
        return gson.fromJson(stationsJson,
                new TypeToken<ArrayList<Estacion>>() {
                }.getType());

    }

    public static final ArrayList<ObservacionEstacion> getObservacionEstacion(String idema) throws Exception {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URLWebService)
                .path(URLObservacionEstacion)
                .path(idema)
                .queryParam("api_key", key);

        Gson gson = new Gson();
        String stationsJson = obtenirDadesDeTarget(client, target);
        return gson.fromJson(stationsJson,
                new TypeToken<ArrayList<ObservacionEstacion>>() {
                }.getType());
    }

    private static String obtenirDadesDeTarget(Client client, WebTarget target) throws Exception {
        Invocation invocation = target.request().buildGet();
        Response res = invocation.invoke();
        if (res.getStatus() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = res.readEntity(String.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
            });
            String estado = (String) map.get("estado");
            if ("404".equals(estado)) {
                throw new Exception("API no retorna dades per a la peticio");
            }
            String URLDatos = (String) map.get("datos");

            target = client.target(URLDatos);
            invocation = target.request().buildGet();
            res = invocation.invoke();
            if (res.getStatus() == 200) {
                String jsonDatos = res.readEntity(String.class);
                return jsonDatos;

            } else {
                throw new Exception("No s'han pogut descarregar les dades");
            }

        } else {
            throw new Exception("No s'ha pogut connectar a API");
        }

    }

}
