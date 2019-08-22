package com.advm.hulkstore.dao;

import org.springframework.data.repository.CrudRepository;

import com.advm.hulkstore.model.Bill;

public interface BillDAO extends CrudRepository<Bill, Long> {

}
