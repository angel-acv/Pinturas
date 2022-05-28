/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pinturasbd.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Angel
 */
@Entity
@Table(name = "potes", catalog = "pinturasbd", schema = "")
@NamedQueries({
    @NamedQuery(name = "Potes.findAll", query = "SELECT p FROM Potes p"),
    @NamedQuery(name = "Potes.findByPotId", query = "SELECT p FROM Potes p WHERE p.potId = :potId"),
    @NamedQuery(name = "Potes.findByNombre", query = "SELECT p FROM Potes p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Potes.findByCodigo", query = "SELECT p FROM Potes p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Potes.findByCantidad", query = "SELECT p FROM Potes p WHERE p.cantidad = :cantidad"),
    @NamedQuery(name = "Potes.findByColor", query = "SELECT p FROM Potes p WHERE p.color = :color"),
    @NamedQuery(name = "Potes.findByMarca", query = "SELECT p FROM Potes p WHERE p.marca = :marca"),
    @NamedQuery(name = "Potes.findByTextura", query = "SELECT p FROM Potes p WHERE p.textura = :textura")})
public class Potes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pot_id", nullable = false)
    private Integer potId;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;
    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "color", nullable = false, length = 80)
    private String color;
    @Basic(optional = false)
    @Column(name = "marca", nullable = false, length = 80)
    private String marca;
    @Basic(optional = false)
    @Column(name = "textura", nullable = false, length = 20)
    private String textura;

    public Potes() {
    }

    public Potes(Integer potId) {
        this.potId = potId;
    }

    public Potes(Integer potId, String nombre, int codigo, int cantidad, String color, String marca, String textura) {
        this.potId = potId;
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.color = color;
        this.marca = marca;
        this.textura = textura;
    }

    public Integer getPotId() {
        return potId;
    }

    public void setPotId(Integer potId) {
        this.potId = potId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTextura() {
        return textura;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (potId != null ? potId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Potes)) {
            return false;
        }
        Potes other = (Potes) object;
        if ((this.potId == null && other.potId != null) || (this.potId != null && !this.potId.equals(other.potId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pinturasbd.models.Potes[ potId=" + potId + " ]";
    }
    
}
