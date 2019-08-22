package generic;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, ID extends Serializable> {

    T save(T entity);

    T get(ID id);

    void delete(ID id);

    List<T> getAll();

}
