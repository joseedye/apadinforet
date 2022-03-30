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
 * @author Leonardo
 */
@Entity
@Table(name = "info_bancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfoBancaria.findAll", query = "SELECT i FROM InfoBancaria i")
    , @NamedQuery(name = "InfoBancaria.findByIdUsuario", query = "SELECT i FROM InfoBancaria i WHERE i.idUsuario = :idUsuario")
    , @NamedQuery(name = "InfoBancaria.findByFormaPago", query = "SELECT i FROM InfoBancaria i WHERE i.formaPago = :formaPago")
    , @NamedQuery(name = "InfoBancaria.findByNumeroCuenta", query = "SELECT i FROM InfoBancaria i WHERE i.numeroCuenta = :numeroCuenta")
    , @NamedQuery(name = "InfoBancaria.findByTipoCuenta", query = "SELECT i FROM InfoBancaria i WHERE i.tipoCuenta = :tipoCuenta")
    , @NamedQuery(name = "InfoBancaria.findByBanco", query = "SELECT i FROM InfoBancaria i WHERE i.banco = :banco")
    , @NamedQuery(name = "InfoBancaria.findByCodBanco", query = "SELECT i FROM InfoBancaria i WHERE i.codBanco = :codBanco")
    , @NamedQuery(name = "InfoBancaria.findBySucursal", query = "SELECT i FROM InfoBancaria i WHERE i.sucursal = :sucursal")
    , @NamedQuery(name = "InfoBancaria.findByCiudad", query = "SELECT i FROM InfoBancaria i WHERE i.ciudad = :ciudad")
    , @NamedQuery(name = "InfoBancaria.findByPais", query = "SELECT i FROM InfoBancaria i WHERE i.pais = :pais")})
public class InfoBancaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "forma_pago")
    private String formaPago;
    @Basic(optional = false)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    @Basic(optional = false)
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Basic(optional = false)
    @Column(name = "banco")
    private String banco;
    @Basic(optional = false)
    @Column(name = "cod_banco")
    private String codBanco;
    @Basic(optional = false)
    @Column(name = "sucursal")
    private String sucursal;
    @Basic(optional = false)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "pais")
    private String pais;

    public InfoBancaria() {
    }

    public InfoBancaria(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public InfoBancaria(Integer idUsuario, String formaPago, String numeroCuenta, String tipoCuenta, String banco, String codBanco, String sucursal, String ciudad, String pais) {
        this.idUsuario = idUsuario;
        this.formaPago = formaPago;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.banco = banco;
        this.codBanco = codBanco;
        this.sucursal = sucursal;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfoBancaria)) {
            return false;
        }
        InfoBancaria other = (InfoBancaria) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.InfoBancaria[ idUsuario=" + idUsuario + " ]";
    }
    
}
