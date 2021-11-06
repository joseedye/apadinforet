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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rozo
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Persona.findByApellido1", query = "SELECT p FROM Persona p WHERE p.apellido1 = :apellido1"),
    @NamedQuery(name = "Persona.findByApellido2", query = "SELECT p FROM Persona p WHERE p.apellido2 = :apellido2"),
    @NamedQuery(name = "Persona.findByFechaNac", query = "SELECT p FROM Persona p WHERE p.fechaNac = :fechaNac"),
    @NamedQuery(name = "Persona.findByTipoDoc", query = "SELECT p FROM Persona p WHERE p.tipoDoc = :tipoDoc"),
    @NamedQuery(name = "Persona.findByNumeroDoc", query = "SELECT p FROM Persona p WHERE p.numeroDoc = :numeroDoc"),
    @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero"),
    @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Persona.findByTelefono1", query = "SELECT p FROM Persona p WHERE p.telefono1 = :telefono1"),
    @NamedQuery(name = "Persona.findByTelefono2", query = "SELECT p FROM Persona p WHERE p.telefono2 = :telefono2"),
    @NamedQuery(name = "Persona.findByEmail", query = "SELECT p FROM Persona p WHERE p.email = :email"),
    @NamedQuery(name = "Persona.findByPais", query = "SELECT p FROM Persona p WHERE p.pais = :pais"),
    @NamedQuery(name = "Persona.findByComentario", query = "SELECT p FROM Persona p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "Persona.findByTipoCliente", query = "SELECT p FROM Persona p WHERE p.tipoCliente = :tipoCliente"),
    @NamedQuery(name = "Persona.findByRazonSocial", query = "SELECT p FROM Persona p WHERE p.razonSocial = :razonSocial"),
    @NamedQuery(name = "Persona.findByRepresentanteLegal", query = "SELECT p FROM Persona p WHERE p.representanteLegal = :representanteLegal")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellido1")
    private String apellido1;
    @Basic(optional = false)
    @Column(name = "apellido2")
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "fecha_nac")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Basic(optional = false)
    @Column(name = "tipo_doc")
    private String tipoDoc;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_doc")
    private String numeroDoc;
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono1")
    private String telefono1;
    @Basic(optional = false)
    @Column(name = "telefono2")
    private String telefono2;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "pais")
    private String pais;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "tipo_cliente")
    private String tipoCliente;
    @Column(name = "razon_social")
    private String razonSocial;
    @Column(name = "representante_legal")
    private String representanteLegal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Usuario> usuarioList;

    public Persona() {
    }

    public Persona(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Persona(String numeroDoc, String nombres, String apellido1, String apellido2, Date fechaNac, String tipoDoc, String direccion, String telefono1, String telefono2, String email) {
        this.numeroDoc = numeroDoc;
        this.nombres = nombres;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNac = fechaNac;
        this.tipoDoc = tipoDoc;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.email = email;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDoc != null ? numeroDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.numeroDoc == null && other.numeroDoc != null) || (this.numeroDoc != null && !this.numeroDoc.equals(other.numeroDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Persona[ numeroDoc=" + numeroDoc + " ]";
    }
    
}
