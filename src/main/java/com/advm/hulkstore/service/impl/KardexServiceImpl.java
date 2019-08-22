package com.advm.hulkstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.advm.hulkstore.dao.KardexDAO;
import com.advm.hulkstore.model.Kardex;
import com.advm.hulkstore.service.KardexService;

import generic.impl.GenericServiceImpl;

@Service
public class KardexServiceImpl extends GenericServiceImpl<Kardex, Long> implements KardexService {

    @Autowired
    private KardexDAO kardexDAO;

    @Override
    public CrudRepository<Kardex, Long> Dao() {
        return kardexDAO;
    }

    @Override
    public double calculateTotalPrice(double stock, double price) {
        double totalPrice = stock * price;

        return totalPrice;
    }

    @Override
    public double calculateNewUnitPrice(Long productId, double stock, double totalPrice, String type) {

        Kardex lastKardex = this.getLastKardex(productId);
        double newUnitPrice = 0;
        if (lastKardex != null) {
            if (type.equals("Buy")) {
                newUnitPrice = (lastKardex.getTotalPrice() + totalPrice) / (stock);
            } else if (type.equals("Sale")) {
                newUnitPrice = lastKardex.getUnitPrice();
            }

        }

        return newUnitPrice;
    }

    @Override
    public Kardex getLastKardex(Long productId) {
        List<Kardex> kardexs = this.getAllKardex(productId);
        Kardex lastKardex = kardexs.get(0);

        return lastKardex;
    }

    @Override
    public List<Kardex> getAllKardex(Long productId) {
        List<Kardex> kardexs = kardexDAO.findByProductId(productId);
        return kardexs;
    }

    @Override
    public double calculateNewStock(Long productId, double stock) {
        double newStock = 0;
        Kardex lastKardex = this.getLastKardex(productId);

        if (lastKardex != null) {
            newStock = lastKardex.getStockInventory() - stock;
        }
        return newStock;
    }
}
