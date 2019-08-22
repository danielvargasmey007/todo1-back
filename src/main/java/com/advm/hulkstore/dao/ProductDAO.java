package com.advm.hulkstore.dao;

import org.springframework.data.repository.CrudRepository;
import com.advm.hulkstore.model.Product;

public interface ProductDAO extends CrudRepository<Product, Long> {

}
