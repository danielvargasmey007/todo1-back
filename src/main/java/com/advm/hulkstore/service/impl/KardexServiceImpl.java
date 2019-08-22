package com.advm.hulkstore.service.impl;

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
}
