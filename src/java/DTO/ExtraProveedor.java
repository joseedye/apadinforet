/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rozo
 */
@Entity
@Table(name = "extra_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtraProveedor.findAll", query = "SELECT e FROM ExtraProveedor e"),
    @NamedQuery(name = "ExtraProveedor.findByIdProveedor", query = "SELECT e FROM ExtraProveedor e WHERE e.idProveedor = :idProveedor"),
    @NamedQuery(name = "ExtraProveedor.findByFechaRetenedor", query = "SELECT e FROM ExtraProveedor e WHERE e.fechaRetenedor = :fechaRetenedor"),
    @NamedQuery(name = "ExtraProveedor.findByResolucionRetenedor", query = "SELECT e FROM ExtraProveedor e WHERE e.resolucionRetenedor = :resolucionRetenedor"),
    @NamedQuery(name = "ExtraProveedor.findByFechaGranContr", query = "SELECT e FROM ExtraProveedor e WHERE e.fechaGranContr = :fechaGranContr"),
    @NamedQuery(name = "ExtraProveedor.findByResolucionGranContri", query = "SELECT e FROM ExtraProveedor e WHERE e.resolucionGranContri = :resolucionGranContri"),
    @NamedQuery(name = "ExtraProveedor.findByIca", query = "SELECT e FROM ExtraProveedor e WHERE e.ica = :ica")})
public class ExtraProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Column(name = "fecha_retenedor")
    @Temporal(TemporalType.DATE)
    private Date fechaRetenedor;
    @Column(name = "resolucion_retenedor")
    private String resolucionRetenedor;
    @Column(name = "fecha_gran_contr")
    @Temporal(TemporalType.DATE)
    private Date fechaGranContr;
    @Column(name = "resolucion_gran_contri")
    private String resolucionGranContri;
    @Column(name = "ica")
    private String ica;

    public ExtraProveedor() {
    }

    public ExtraProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaRetenedor() {
        return fechaRetenedor;
    }

    public void setFechaRetenedor(Date fechaRetenedor) {
        this.fechaRetenedor = fechaRetenedor;
    }

    public String getResolucionRetenedor() {
        return resolucionRetenedor;
    }

    public void setResolucionRetenedor(String resolucionRetenedor) {
        this.resolucionRetenedor = resolucionRetenedor;
    }

    public Date getFechaGranContr() {
        return fechaGranContr;
    }

    public void setFechaGranContr(Date fechaGranContr) {
        this.fechaGranContr = fechaGranContr;
    }

    public String getResolucionGranContri() {
        return resolucionGranContri;
    }

    public void setResolucionGranContri(String resolucionGranContri) {
        this.resolucionGranContri = resolucionGranContri;
    }

    public String getIca() {
        return ica;
    }

    public void setIca(String ica) {
        this.ica = ica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtraProveedor)) {
            return false;
        }
        ExtraProveedor other = (ExtraProveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ExtraProveedor[ idProveedor=" + idProveedor + " ]";
    }
    
}
