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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "documento_propio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoPropio.findAll", query = "SELECT d FROM DocumentoPropio d")
    , @NamedQuery(name = "DocumentoPropio.findByIdDocumento", query = "SELECT d FROM DocumentoPropio d WHERE d.idDocumento = :idDocumento")
    , @NamedQuery(name = "DocumentoPropio.findByFechaDeSubida", query = "SELECT d FROM DocumentoPropio d WHERE d.fechaDeSubida = :fechaDeSubida")
    , @NamedQuery(name = "DocumentoPropio.findByNombre", query = "SELECT d FROM DocumentoPropio d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DocumentoPropio.findByRuta", query = "SELECT d FROM DocumentoPropio d WHERE d.ruta = :ruta")})
public class DocumentoPropio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_documento")
    private Integer idDocumento;
    @Basic(optional = false)
    @Column(name = "fecha_de_subida")
    @Temporal(TemporalType.DATE)
    private Date fechaDeSubida;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "ruta")
    private String ruta;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public DocumentoPropio() {
    }

    public DocumentoPropio(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public DocumentoPropio(Integer idDocumento, Date fechaDeSubida, String nombre, String ruta) {
        this.idDocumento = idDocumento;
        this.fechaDeSubida = fechaDeSubida;
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Date getFechaDeSubida() {
        return fechaDeSubida;
    }

    public void setFechaDeSubida(Date fechaDeSubida) {
        this.fechaDeSubida = fechaDeSubida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoPropio)) {
            return false;
        }
        DocumentoPropio other = (DocumentoPropio) object;
        if ((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DocumentoPropio[ idDocumento=" + idDocumento + " ]";
    }
    
}
