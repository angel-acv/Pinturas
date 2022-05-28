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
@Table(name = "entregas_pinturas", catalog = "pinturasbd", schema = "")
@NamedQueries({
    @NamedQuery(name = "EntregasPinturas.findAll", query = "SELECT e FROM EntregasPinturas e"),
    @NamedQuery(name = "EntregasPinturas.findByEntrId", query = "SELECT e FROM EntregasPinturas e WHERE e.entrId = :entrId"),
    @NamedQuery(name = "EntregasPinturas.findByCodigo", query = "SELECT e FROM EntregasPinturas e WHERE e.codigo = :codigo"),
    @NamedQuery(name = "EntregasPinturas.findByTama\u00f1o", query = "SELECT e FROM EntregasPinturas e WHERE e.tama\u00f1o = :tama\u00f1o"),
    @NamedQuery(name = "EntregasPinturas.findByPeso", query = "SELECT e FROM EntregasPinturas e WHERE e.peso = :peso")})
public class EntregasPinturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "entr_id", nullable = false)
    private Integer entrId;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 11)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "tama\u00f1o", nullable = false)
    private int tamaño;
    @Basic(optional = false)
    @Column(name = "peso", nullable = false)
    private int peso;

    public EntregasPinturas() {
    }

    public EntregasPinturas(Integer entrId) {
        this.entrId = entrId;
    }

    public EntregasPinturas(Integer entrId, String codigo, int tamaño, int peso) {
        this.entrId = entrId;
        this.codigo = codigo;
        this.tamaño = tamaño;
        this.peso = peso;
    }

    public Integer getEntrId() {
        return entrId;
    }

    public void setEntrId(Integer entrId) {
        this.entrId = entrId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entrId != null ? entrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntregasPinturas)) {
            return false;
        }
        EntregasPinturas other = (EntregasPinturas) object;
        if ((this.entrId == null && other.entrId != null) || (this.entrId != null && !this.entrId.equals(other.entrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pinturasbd.models.EntregasPinturas[ entrId=" + entrId + " ]";
    }
    
}
