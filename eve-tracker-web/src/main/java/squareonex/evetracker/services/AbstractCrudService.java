package squareonex.evetracker.services;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCrudService<T, Id> {
    protected final CrudRepository<T, Id> repository;

    protected AbstractCrudService(CrudRepository<T, Id> repository) {
        this.repository = repository;
    }

    public Set<T> findAll() {
        Set<T> set = new HashSet<>();
        repository.findAll().forEach(set::add);
        return set;
    }
}
