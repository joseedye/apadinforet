/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "Solicitud.findByIdCliente", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.idCliente = :idCliente"),
    @NamedQuery(name = "Solicitud.findByTematica", query = "SELECT s FROM Solicitud s WHERE s.tematica = :tematica"),
    @NamedQuery(name = "Solicitud.findByDescripcion", query = "SELECT s FROM Solicitud s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Solicitud.findByFecha", query = "SELECT s FROM Solicitud s WHERE s.solicitudPK.fecha = :fecha")})
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SolicitudPK solicitudPK;
    @Basic(optional = false)
    @Column(name = "tematica")
    private String tematica;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "estatus", referencedColumnName = "id_estatus")
    @ManyToOne(optional = false)
    private EstatusSolicitud estatus;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "id_solucionador", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idSolucionador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<Documento> documentoList;

    public Solicitud() {
    }

    public Solicitud(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public Solicitud(SolicitudPK solicitudPK, String tematica, String descripcion) {
        this.solicitudPK = solicitudPK;
        this.tematica = tematica;
        this.descripcion = descripcion;
    }

    public Solicitud(int idSolicitud, int idCliente, Date fecha) {
        this.solicitudPK = new SolicitudPK(idSolicitud, idCliente, fecha);
    }

    public SolicitudPK getSolicitudPK() {
        return solicitudPK;
    }

    public void setSolicitudPK(SolicitudPK solicitudPK) {
        this.solicitudPK = solicitudPK;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstatusSolicitud getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusSolicitud estatus) {
        this.estatus = estatus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getIdSolucionador() {
        return idSolucionador;
    }

    public void setIdSolucionador(Usuario idSolucionador) {
        this.idSolucionador = idSolucionador;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudPK != null ? solicitudPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.solicitudPK == null && other.solicitudPK != null) || (this.solicitudPK != null && !this.solicitudPK.equals(other.solicitudPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Solicitud[ solicitudPK=" + solicitudPK + " ]";
    }
    
}
