package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Retrieves all items from the item repository.
     *
     * @return a set of Item objects containing all the items
     */
    @Override
    public Set<Item> findAll() {
        Set<Item> set = new HashSet<>();
        itemRepository.findAll().forEach(set::add);
        return set;
    }
}
