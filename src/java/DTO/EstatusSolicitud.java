/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rozo
 */
@Entity
@Table(name = "estatus_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusSolicitud.findAll", query = "SELECT e FROM EstatusSolicitud e"),
    @NamedQuery(name = "EstatusSolicitud.findByIdEstatus", query = "SELECT e FROM EstatusSolicitud e WHERE e.idEstatus = :idEstatus"),
    @NamedQuery(name = "EstatusSolicitud.findByDesripcion", query = "SELECT e FROM EstatusSolicitud e WHERE e.desripcion = :desripcion"),
    @NamedQuery(name = "EstatusSolicitud.findByDescripcionDetallada", query = "SELECT e FROM EstatusSolicitud e WHERE e.descripcionDetallada = :descripcionDetallada")})
public class EstatusSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_estatus")
    private Integer idEstatus;
    @Basic(optional = false)
    @Column(name = "desripcion")
    private String desripcion;
    @Basic(optional = false)
    @Column(name = "descripcion_detallada")
    private String descripcionDetallada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatus")
    private List<Solicitud> solicitudList;

    public EstatusSolicitud() {
    }

    public EstatusSolicitud(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public EstatusSolicitud(Integer idEstatus, String desripcion, String descripcionDetallada) {
        this.idEstatus = idEstatus;
        this.desripcion = desripcion;
        this.descripcionDetallada = descripcionDetallada;
    }

    public Integer getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getDesripcion() {
        return desripcion;
    }

    public void setDesripcion(String desripcion) {
        this.desripcion = desripcion;
    }

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstatus != null ? idEstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusSolicitud)) {
            return false;
        }
        EstatusSolicitud other = (EstatusSolicitud) object;
        if ((this.idEstatus == null && other.idEstatus != null) || (this.idEstatus != null && !this.idEstatus.equals(other.idEstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.EstatusSolicitud[ idEstatus=" + idEstatus + " ]";
    }
    
}
