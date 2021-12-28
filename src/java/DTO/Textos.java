/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rozo
 */
@Entity
@Table(name = "textos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Textos.findAll", query = "SELECT t FROM Textos t"),
    @NamedQuery(name = "Textos.findByIdTexto", query = "SELECT t FROM Textos t WHERE t.idTexto = :idTexto")})
public class Textos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_texto")
    private Integer idTexto;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    public Textos() {
    }

    public Textos(Integer idTexto) {
        this.idTexto = idTexto;
    }

    public Integer getIdTexto() {
        return idTexto;
    }

    public void setIdTexto(Integer idTexto) {
        this.idTexto = idTexto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTexto != null ? idTexto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Textos)) {
            return false;
        }
        Textos other = (Textos) object;
        if ((this.idTexto == null && other.idTexto != null) || (this.idTexto != null && !this.idTexto.equals(other.idTexto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Textos[ idTexto=" + idTexto + " ]";
    }
    
}
