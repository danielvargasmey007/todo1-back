package generic.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import generic.GenericService;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

    @Override
    public T save(T entity) {
        return Dao().save(entity);
    }

    @Override
    public T get(ID id) {
        return Dao().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        Dao().deleteById(id);
    }

    @Override
    public List<T> getAll() {
        List<T> all = new ArrayList<>();
        Dao().findAll().forEach(t -> all.add(t));
        return all;
    }

    public abstract CrudRepository<T, ID> Dao();

}
