/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author cortiz3
 */
@Entity
@Table(name = "ESTACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estacion.findAll", query = "SELECT e FROM Estacion e"),
    @NamedQuery(name = "Estacion.findById", query = "SELECT e FROM Estacion e WHERE e.id = :id"),
    @NamedQuery(name = "Estacion.findByNombre", query = "SELECT e FROM Estacion e WHERE e.nombre = :nombre")})
public class Estacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Nombre")
    private String nombre;
    @JoinColumn(name = "ID_provincia", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Provincia iDprovincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private Collection<EstMeteorologia> estMeteorologiaCollection;

    public Estacion() {
    }

    public Estacion(String id) {
        this.id = id;
    }

    public Estacion(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getIDprovincia() {
        return iDprovincia;
    }

    public void setIDprovincia(Provincia iDprovincia) {
        this.iDprovincia = iDprovincia;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<EstMeteorologia> getEstMeteorologiaCollection() {
        return estMeteorologiaCollection;
    }

    public void setEstMeteorologiaCollection(Collection<EstMeteorologia> estMeteorologiaCollection) {
        this.estMeteorologiaCollection = estMeteorologiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacion)) {
            return false;
        }
        Estacion other = (Estacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
