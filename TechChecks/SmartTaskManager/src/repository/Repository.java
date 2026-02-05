package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T add(T item);

    T remove(T item);

    Optional<T> findById(long id);

    List<T> findALL();
}
