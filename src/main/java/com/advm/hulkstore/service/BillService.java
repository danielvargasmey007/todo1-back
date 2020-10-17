package com.advm.hulkstore.service;

import org.springframework.data.repository.CrudRepository;

import com.advm.hulkstore.model.Bill;

import generic.GenericService;

public interface BillService extends GenericService<Bill, Long> {

	public CrudRepository<Bill, Long> Dao();
}
