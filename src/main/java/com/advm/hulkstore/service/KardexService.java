package com.advm.hulkstore.service;

import java.util.List;

import com.advm.hulkstore.model.Kardex;
import generic.GenericService;

public interface KardexService extends GenericService<Kardex, Long> {

    double calculateTotalPrice(double stock, double price);

    double calculateNewUnitPrice(Long productId, double stock, double totalPrice, String type);
    
    double calculateNewStock(Long productId, double stock);
    
    Kardex getLastKardex(Long productId);
    
    List<Kardex> getAllKardex(Long productId);
}
