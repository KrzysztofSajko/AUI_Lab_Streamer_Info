package pl.edu.pg.StreamerInfo.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    List<E> findAll();
    Optional<E> findByKey(K id);
    void add(E entity);
    void update(E entity);
    void delete(E entity);
}
