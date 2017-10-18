package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.BaseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T extends BaseEntity> {

    List<T> findAll();

    default Optional<T> findOne(UUID id) {
        return findAll().stream()
                        .filter(e -> Objects.equals(e.getId(), id))
                        .findFirst();
    }

    default void add(T entity) {
        findAll().add(entity);
    }

    default void remove(T entity) {
        findAll().remove(entity);
    }
}
