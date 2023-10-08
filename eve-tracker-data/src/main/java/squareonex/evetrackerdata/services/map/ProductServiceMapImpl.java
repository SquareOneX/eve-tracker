package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.services.ProductService;

import java.util.HashSet;
import java.util.Set;

public class ProductServiceMapImpl extends AbstractMapService<Item, Long> implements ProductService {
    private Long key = 0L;
    @Override
    public Set<Item> findAll() {
        return super.findAll();
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Item object) {
        super.delete(object);
    }

    @Override
    public Item save(Item object) {
        if (object.getId() == null){
            while (map.containsKey(key))
                key++;
            object.setId(key);
            return super.save(key, object);
        }else {
            return super.save(object.getId(), object);
        }
    }
    @Override
    public Iterable<Item> saveAll(Iterable<Item> iterator) {
        Set<Item> set = new HashSet<>();
        for (Item item : iterator) {
            set.add(this.save(item));
        }
        return set;
    }

    @Override
    public Item findById(Long id) {
        return super.findById(id);
    }
}
