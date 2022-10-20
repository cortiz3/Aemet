/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cintia Ortiz
 */
@Entity
@Table(name = "EST_METEOROLOGIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstMeteorologia.findAll", query = "SELECT e FROM EstMeteorologia e"),
//    //FechaHora query
//    @NamedQuery(name = "EstMeteorologia.findByDate", query = "SELECT e FROM EstMeteorologia e WHERE e.estMeteorologiaPK = :fechaHora"),
//    
    @NamedQuery(name = "EstMeteorologia.findByID", query = "SELECT e FROM EstMeteorologia e WHERE e.estMeteorologiaPK = :id"), //Se crea una nueva query con findById para que pueda buscar las PK.
    @NamedQuery(name = "EstMeteorologia.findByIDEstacion", query = "SELECT e FROM EstMeteorologia e WHERE e.estMeteorologiaPK.iDEstacion = :iDEstacion"),
    @NamedQuery(name = "EstMeteorologia.findByUbicacion", query = "SELECT e FROM EstMeteorologia e WHERE e.ubicacion = :ubicacion"),
    @NamedQuery(name = "EstMeteorologia.findByLongitud", query = "SELECT e FROM EstMeteorologia e WHERE e.longitud = :longitud"),
    @NamedQuery(name = "EstMeteorologia.findByLatitud", query = "SELECT e FROM EstMeteorologia e WHERE e.latitud = :latitud"),
    @NamedQuery(name = "EstMeteorologia.findByAltitud", query = "SELECT e FROM EstMeteorologia e WHERE e.altitud = :altitud"),
    @NamedQuery(name = "EstMeteorologia.findByFechaHora", query = "SELECT e FROM EstMeteorologia e WHERE e.estMeteorologiaPK.fechaHora = :fechaHora"),
    @NamedQuery(name = "EstMeteorologia.findByPrecAcumulada", query = "SELECT e FROM EstMeteorologia e WHERE e.precAcumulada = :precAcumulada"),
    @NamedQuery(name = "EstMeteorologia.findByTempMinAire", query = "SELECT e FROM EstMeteorologia e WHERE e.tempMinAire = :tempMinAire"),
    @NamedQuery(name = "EstMeteorologia.findByTempMaxAire", query = "SELECT e FROM EstMeteorologia e WHERE e.tempMaxAire = :tempMaxAire"),
    @NamedQuery(name = "EstMeteorologia.findByPresion", query = "SELECT e FROM EstMeteorologia e WHERE e.presion = :presion"),
    @NamedQuery(name = "EstMeteorologia.findByHumedad", query = "SELECT e FROM EstMeteorologia e WHERE e.humedad = :humedad"),
    @NamedQuery(name = "EstMeteorologia.findByVeloMaxViento", query = "SELECT e FROM EstMeteorologia e WHERE e.veloMaxViento = :veloMaxViento"),
    @NamedQuery(name = "EstMeteorologia.findByVeloMediaViento", query = "SELECT e FROM EstMeteorologia e WHERE e.veloMediaViento = :veloMediaViento"),
    @NamedQuery(name = "EstMeteorologia.findByDireccViento", query = "SELECT e FROM EstMeteorologia e WHERE e.direccViento = :direccViento"),
    @NamedQuery(name = "EstMeteorologia.findByTempInstantanea", query = "SELECT e FROM EstMeteorologia e WHERE e.tempInstantanea = :tempInstantanea"),
    @NamedQuery(name = "EstMeteorologia.findByDuracionInsolacion", query = "SELECT e FROM EstMeteorologia e WHERE e.duracionInsolacion = :duracionInsolacion")})
public class EstMeteorologia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstMeteorologiaPK estMeteorologiaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Ubicacion")
    private String ubicacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Longitud")
    private BigDecimal longitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Latitud")
    private BigDecimal latitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Altitud")
    private BigDecimal altitud;
    @Column(name = "PrecAcumulada")
    private BigDecimal precAcumulada;
    @Column(name = "TempMin_Aire")
    private BigDecimal tempMinAire;
    @Column(name = "TempMax_Aire")
    private BigDecimal tempMaxAire;
    @Column(name = "Presion")
    private BigDecimal presion;
    @Column(name = "Humedad")
    private BigDecimal humedad;
    @Column(name = "VeloMax_Viento")
    private BigDecimal veloMaxViento;
    @Column(name = "VeloMedia_Viento")
    private BigDecimal veloMediaViento;
    @Column(name = "DireccViento")
    private BigDecimal direccViento;
    @Column(name = "TempInstantanea")
    private BigDecimal tempInstantanea;
    @Column(name = "DuracionInsolacion")
    private BigDecimal duracionInsolacion;
    @JoinColumn(name = "ID_Estacion", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacion estacion;

    public EstMeteorologia() {
    }

    public EstMeteorologia(EstMeteorologiaPK estMeteorologiaPK) {
        this.estMeteorologiaPK = estMeteorologiaPK;
    }

    public EstMeteorologia(EstMeteorologiaPK estMeteorologiaPK, String ubicacion, BigDecimal longitud, BigDecimal latitud, BigDecimal altitud) {
        this.estMeteorologiaPK = estMeteorologiaPK;
        this.ubicacion = ubicacion;
        this.longitud = longitud;
        this.latitud = latitud;
        this.altitud = altitud;
    }

    public EstMeteorologia(String iDEstacion, Date fechaHora) {
        this.estMeteorologiaPK = new EstMeteorologiaPK(iDEstacion, fechaHora);
    }

    public EstMeteorologiaPK getEstMeteorologiaPK() {
        return estMeteorologiaPK;
    }

    public void setEstMeteorologiaPK(EstMeteorologiaPK estMeteorologiaPK) {
        this.estMeteorologiaPK = estMeteorologiaPK;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getAltitud() {
        return altitud;
    }

    public void setAltitud(BigDecimal altitud) {
        this.altitud = altitud;
    }

    public BigDecimal getPrecAcumulada() {
        return precAcumulada;
    }

    public void setPrecAcumulada(BigDecimal precAcumulada) {
        this.precAcumulada = precAcumulada;
    }

    public BigDecimal getTempMinAire() {
        return tempMinAire;
    }

    public void setTempMinAire(BigDecimal tempMinAire) {
        this.tempMinAire = tempMinAire;
    }

    public BigDecimal getTempMaxAire() {
        return tempMaxAire;
    }

    public void setTempMaxAire(BigDecimal tempMaxAire) {
        this.tempMaxAire = tempMaxAire;
    }

    public BigDecimal getPresion() {
        return presion;
    }

    public void setPresion(BigDecimal presion) {
        this.presion = presion;
    }

    public BigDecimal getHumedad() {
        return humedad;
    }

    public void setHumedad(BigDecimal humedad) {
        this.humedad = humedad;
    }

    public BigDecimal getVeloMaxViento() {
        return veloMaxViento;
    }

    public void setVeloMaxViento(BigDecimal veloMaxViento) {
        this.veloMaxViento = veloMaxViento;
    }

    public BigDecimal getVeloMediaViento() {
        return veloMediaViento;
    }

    public void setVeloMediaViento(BigDecimal veloMediaViento) {
        this.veloMediaViento = veloMediaViento;
    }

    public BigDecimal getDireccViento() {
        return direccViento;
    }

    public void setDireccViento(BigDecimal direccViento) {
        this.direccViento = direccViento;
    }

    public BigDecimal getTempInstantanea() {
        return tempInstantanea;
    }

    public void setTempInstantanea(BigDecimal tempInstantanea) {
        this.tempInstantanea = tempInstantanea;
    }

    public BigDecimal getDuracionInsolacion() {
        return duracionInsolacion;
    }

    public void setDuracionInsolacion(BigDecimal duracionInsolacion) {
        this.duracionInsolacion = duracionInsolacion;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estMeteorologiaPK != null ? estMeteorologiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstMeteorologia)) {
            return false;
        }
        EstMeteorologia other = (EstMeteorologia) object;
        if ((this.estMeteorologiaPK == null && other.estMeteorologiaPK != null) || (this.estMeteorologiaPK != null && !this.estMeteorologiaPK.equals(other.estMeteorologiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AEMET.model.entities.EstMeteorologia[ estMeteorologiaPK=" + estMeteorologiaPK + " ]";
    }
    
}
