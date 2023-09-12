package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Product;
import squareonex.evetrackerdata.services.ProductService;

import java.util.Set;

public class ProductServiceMapImpl extends AbstractMapService<Product, Long> implements ProductService {
    private Long key = 0L;
    @Override
    public Set<Product> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Product object) {
        super.delete(object);
    }

    @Override
    public Product save(Product object) {
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
    public Product findById(Long id) {
        return super.findById(id);
    }
}
