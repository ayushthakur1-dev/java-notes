package app.repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    T add(T item);

    T remove(T item);

    Optional<T> findById(long id);

    List<T> findALL();
}
