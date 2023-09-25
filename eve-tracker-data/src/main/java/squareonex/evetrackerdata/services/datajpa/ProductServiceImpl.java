package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.ItemRepository;
import squareonex.evetrackerdata.services.ProductService;

import java.util.HashSet;
import java.util.Set;

public class ProductServiceImpl implements ProductService {
    private final ItemRepository itemRepository;

    public ProductServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> findAll() {
        Set<Item> set = new HashSet<>();
        for (Item item : itemRepository.findAll()) {
            set.add(item);
        }
        return set;
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item save(Item object) {
        return itemRepository.save(object);
    }

    @Override
    public void delete(Item object) {
        itemRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
