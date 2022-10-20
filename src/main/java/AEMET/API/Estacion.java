/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.API;

/**
 *
 * @author david
 * 
 * AEMET MetaData
 * 
 * {
  "unidad_generadora": "Servicio del Banco de Datos Nacional de Climatología",
  "periodicidad": "1 vez al día",
  "descripcion": "Inventario de estaciones para el apartado Valores Climatología",
  "formato": "application/json",
  "copyright": "© AEMET. Autorizado el uso de la información y su reproducción citando a AEMET como autora de la misma.",
  "notaLegal": "http://www.aemet.es/es/nota_legal",
  "campos": [
  	{"id":"latitud",
	"descripcion": "latitud de la estación",
	"tipo_datos": "string",
    	"requerido": true
    },
	{"id":"provincia",
	"descripcion": "provincia donde reside la estación",
	"tipo_datos": "string",
    	"requerido": true
	
    },
	{"id": "indicativo",
	"descripcion": "indicativo climatológico de la estación",
	"tipo_datos": "string",
    	"requerido": true 
    },
	{"id": "altitud",
	"descripcion": "altitud de la estación ",
	"tipo_datos": "string",
    	"requerido": true
    },
	{"id": "nombre",
	"descripcion": "ubicación de la estación",
	"tipo_datos": "string",
    	"requerido": true
    },
	{"id":"indsinop",
	"descripcion": "Indicativo sinóptico",
	"tipo_datos": "string",
    	"requerido": true
    },
	{"id":"longitud",
        "descripcion": "longitud de la estación",
        "tipo_datos": "string",
        "requerido": true
    }
  ]

}
 * 
 */
public class Estacion {
    
    private String latitud;
    private String provincia;
    private String indicativo;
    private String altitud;
    private String nombre;
    private String indsinop;

    public Estacion() {
    }

    public String getLatitud() {
        return latitud;
    }

    @Override
    public String toString() {
        return "Estacion{" + "latitud=" + latitud + ", provincia=" + provincia + ", indicativo=" + indicativo + ", altitud=" + altitud + ", nombre=" + nombre + ", indsinop=" + indsinop + ", longitud=" + longitud + '}';
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIndsinop() {
        return indsinop;
    }

    public void setIndsinop(String indsinop) {
        this.indsinop = indsinop;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    private String longitud;
    
    
}
