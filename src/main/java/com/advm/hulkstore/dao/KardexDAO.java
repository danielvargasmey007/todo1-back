package com.advm.hulkstore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.advm.hulkstore.model.Kardex;

public interface KardexDAO extends CrudRepository<Kardex, Long> {

    List<Kardex> findByProductId(Long id);
}
