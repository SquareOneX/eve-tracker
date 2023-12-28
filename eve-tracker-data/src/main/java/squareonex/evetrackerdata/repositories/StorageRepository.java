package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Storage;

import java.util.Optional;

@Repository
public interface StorageRepository extends CrudRepository<Storage, Long> {
    Optional<Storage> findByItem(Item item);
}