package squareonex.evetracker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import squareonex.evetrackerdata.model.Item;

import java.util.Set;

public interface ItemService {
    Set<Item> findAll();
    Page<Item> findPaginated(Pageable pageable);
}
