package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.ItemRepository;

@Service
public class ItemServiceImpl extends AbstractCrudService<Item, Long> implements ItemService {
    public ItemServiceImpl(ItemRepository itemRepository) {
        super(itemRepository);
    }
}
