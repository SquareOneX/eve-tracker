package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
