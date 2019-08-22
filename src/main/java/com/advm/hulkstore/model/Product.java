package com.advm.hulkstore.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = -5240225876895947244L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Kardex> kardexs;

    @ManyToMany(mappedBy = "products")
    private List<Bill> bills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @PrePersist
    public void prePersist() {
        this.kardexs = new ArrayList<Kardex>();
        this.bills = new ArrayList<Bill>();
    }

}
