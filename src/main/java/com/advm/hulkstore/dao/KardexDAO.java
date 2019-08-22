package com.advm.hulkstore.dao;

import org.springframework.data.repository.CrudRepository;
import com.advm.hulkstore.model.Kardex;

public interface KardexDAO extends CrudRepository<Kardex, Long> {

}
