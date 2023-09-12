package squareonex.evetrackerdata.services.map;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ProductService;

import java.util.Set;

@Service
public class BlueprintServiceMapImpl extends AbstractMapService<Blueprint, Long> implements BlueprintService {
    private final ProductService productService;
    private final ActivityService activityService;

    public BlueprintServiceMapImpl(ProductService productService, ActivityService activityService) {
        this.productService = productService;
        this.activityService = activityService;
    }

    @Override
    public Set<Blueprint> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productService.deleteById(id);
        super.deleteById(id);
    }

    @Override
    public void delete(Blueprint object) {
        productService.deleteById(object.getId());
        super.delete(object);
    }

    @Override
    public Blueprint save(Blueprint object) {
        if (object.getId() == null || object.getActivity() == null)
            return null;
        else {
            activityService.save(object.getActivity());
            return super.save(object.getId(), object);
        }
    }

    @Override
    public Blueprint findById(Long id) {
        return super.findById(id);
    }
}
