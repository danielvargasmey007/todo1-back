package com.advm.hulkstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.advm.hulkstore.dao.BillDAO;
import com.advm.hulkstore.model.Bill;
import com.advm.hulkstore.service.BillService;

import generic.impl.GenericServiceImpl;

@Service
public class BillServiceImpl extends GenericServiceImpl<Bill, Long> implements BillService {

    @Autowired
    private BillDAO billDAO;

    @Override
    public CrudRepository<Bill, Long> Dao() {
        return billDAO;
    }

}
