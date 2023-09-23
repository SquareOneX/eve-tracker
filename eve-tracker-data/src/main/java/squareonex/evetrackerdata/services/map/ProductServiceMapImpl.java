package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.services.ProductService;

import java.util.Set;

public class ProductServiceMapImpl extends AbstractMapService<Item, Long> implements ProductService {
    private Long key = 0L;
    @Override
    public Set<Item> findAll() {
        return super.findAll();
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
    public Item findById(Long id) {
        return super.findById(id);
    }
}
