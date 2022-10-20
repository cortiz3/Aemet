/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author cortiz3
 */
@Embeddable
public class EstMeteorologiaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_Estacion")
    private String iDEstacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    public EstMeteorologiaPK() {
    }

    public EstMeteorologiaPK(String iDEstacion, Date fechaHora) {
        this.iDEstacion = iDEstacion;
        this.fechaHora = fechaHora;
    }

    public String getIDEstacion() {
        return iDEstacion;
    }

    public void setIDEstacion(String iDEstacion) {
        this.iDEstacion = iDEstacion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDEstacion != null ? iDEstacion.hashCode() : 0);
        hash += (fechaHora != null ? fechaHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstMeteorologiaPK)) {
            return false;
        }
        EstMeteorologiaPK other = (EstMeteorologiaPK) object;
        if ((this.iDEstacion == null && other.iDEstacion != null) || (this.iDEstacion != null && !this.iDEstacion.equals(other.iDEstacion))) {
            return false;
        }
        if ((this.fechaHora == null && other.fechaHora != null) || (this.fechaHora != null && !this.fechaHora.equals(other.fechaHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AEMET.model.entities.EstMeteorologiaPK[ iDEstacion=" + iDEstacion + ", fechaHora=" + fechaHora + " ]";
    }
    
}
