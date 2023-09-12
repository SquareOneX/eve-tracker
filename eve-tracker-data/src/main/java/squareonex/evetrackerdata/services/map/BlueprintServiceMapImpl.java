package squareonex.evetrackerdata.services.map;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ItemService;

import java.util.Set;

@Service
public class BlueprintServiceMapImpl extends AbstractMapService<Blueprint, Long> implements BlueprintService {
    private final ItemService itemService;
    private final ActivityService activityService;

    public BlueprintServiceMapImpl(ItemService itemService, ActivityService activityService) {
        this.itemService = itemService;
        this.activityService = activityService;
    }

    @Override
    public Set<Blueprint> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        itemService.deleteById(id);
        super.deleteById(id);
    }

    @Override
    public void delete(Blueprint object) {
        itemService.deleteById(object.getId());
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
