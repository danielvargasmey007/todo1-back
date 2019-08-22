package com.advm.hulkstore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Kardex implements Serializable {

    private static final long serialVersionUID = -1057787520902452558L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double stock;

    @Column
    private double stockInventory;

    @Column
    private double unitPrice;

    @Column
    private double unitPriceInventory;

    @Column
    private double totalPrice;

    @Column
    private double totalPriceInventory;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "billId")
    private Bill bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getStockInventory() {
        return stockInventory;
    }

    public void setStockInventory(double stockInventory) {
        this.stockInventory = stockInventory;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitPriceInventory() {
        return unitPriceInventory;
    }

    public void setUnitPriceInventory(double unitPriceInventory) {
        this.unitPriceInventory = unitPriceInventory;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceInventory() {
        return totalPriceInventory;
    }

    public void setTotalPriceInventory(double totalPriceInventory) {
        this.totalPriceInventory = totalPriceInventory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @PrePersist
    public void prePersist() {
        this.date = new Date();
    }

}
