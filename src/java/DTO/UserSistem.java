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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rozo
 */
@Entity
@Table(name = "user_sistem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSistem.findAll", query = "SELECT u FROM UserSistem u"),
    @NamedQuery(name = "UserSistem.findByIdUsis", query = "SELECT u FROM UserSistem u WHERE u.idUsis = :idUsis"),
    @NamedQuery(name = "UserSistem.findByIdPersona", query = "SELECT u FROM UserSistem u WHERE u.idPersona = :idPersona")})
public class UserSistem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_usis")
    private Integer idUsis;
    @Basic(optional = false)
    @Column(name = "id_persona")
    private int idPersona;

    public UserSistem() {
    }

    public UserSistem(Integer idUsis) {
        this.idUsis = idUsis;
    }

    public UserSistem(Integer idUsis, int idPersona) {
        this.idUsis = idUsis;
        this.idPersona = idPersona;
    }

    public Integer getIdUsis() {
        return idUsis;
    }

    public void setIdUsis(Integer idUsis) {
        this.idUsis = idUsis;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsis != null ? idUsis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSistem)) {
            return false;
        }
        UserSistem other = (UserSistem) object;
        if ((this.idUsis == null && other.idUsis != null) || (this.idUsis != null && !this.idUsis.equals(other.idUsis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.UserSistem[ idUsis=" + idUsis + " ]";
    }
    
}
