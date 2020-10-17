package com.advm.hulkstore.service;

import org.springframework.data.repository.CrudRepository;

import com.advm.hulkstore.model.Product;

import generic.GenericService;

public interface ProductService extends GenericService<Product, Long> {

	public CrudRepository<Product, Long> Dao();
}
