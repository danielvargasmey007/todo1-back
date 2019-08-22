package com.advm.hulkstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.advm.hulkstore.dao.ProductDAO;
import com.advm.hulkstore.model.Product;
import com.advm.hulkstore.service.ProductService;
import generic.impl.GenericServiceImpl;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public CrudRepository<Product, Long> Dao() {
        return productDAO;
    }

}
