package com.advm.hulkstore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

@Entity
public class Bill implements Serializable {

    private static final long serialVersionUID = -2111507056928294589L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "bill")
    private List<Kardex> kardexs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bill_product", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "bill_id", referencedColumnName = "id"))
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Kardex> getKardexs() {
        return kardexs;
    }

    public void setKardexs(List<Kardex> kardexs) {
        this.kardexs = kardexs;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @PrePersist
    public void prePersist() {
        this.date = new Date();
    }

}
