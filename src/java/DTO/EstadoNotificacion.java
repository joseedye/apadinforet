/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rozo
 */
@Entity
@Table(name = "estado_notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoNotificacion.findAll", query = "SELECT e FROM EstadoNotificacion e"),
    @NamedQuery(name = "EstadoNotificacion.findByIdNotificacion", query = "SELECT e FROM EstadoNotificacion e WHERE e.idNotificacion = :idNotificacion"),
    @NamedQuery(name = "EstadoNotificacion.findByDescripcion", query = "SELECT e FROM EstadoNotificacion e WHERE e.descripcion = :descripcion")})
public class EstadoNotificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "estadoNotificacion")
    private Notificacion notificacion;

    public EstadoNotificacion() {
    }

    public EstadoNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public EstadoNotificacion(Integer idNotificacion, String descripcion) {
        this.idNotificacion = idNotificacion;
        this.descripcion = descripcion;
    }

    public Integer getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Integer idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Notificacion getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificacion != null ? idNotificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoNotificacion)) {
            return false;
        }
        EstadoNotificacion other = (EstadoNotificacion) object;
        if ((this.idNotificacion == null && other.idNotificacion != null) || (this.idNotificacion != null && !this.idNotificacion.equals(other.idNotificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.EstadoNotificacion[ idNotificacion=" + idNotificacion + " ]";
    }
    
}
