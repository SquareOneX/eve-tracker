package squareonex.evetrackerdata.services.map;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintKey;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;

import java.util.Set;

@Service
public class BlueprintServiceMapImpl extends AbstractMapService<Blueprint, BlueprintKey> implements BlueprintService {
    private final ActivityService activityService;

    public BlueprintServiceMapImpl(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public Set<Blueprint> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(BlueprintKey id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Blueprint object) {
        super.delete(object);
    }

    @Override
    public Blueprint save(Blueprint object) {
        if (object.getItemInfo().getId() == null || object.getActivity() == null)
            return null;
        else {
            if (activityService.findById(object.getActivity().getId()) == null)
                activityService.save(object.getActivity());
            return super.save(new BlueprintKey(object.getItemInfo(), object.getActivity()), object);
        }
    }

    @Override
    public Blueprint findById(BlueprintKey id) {
        return super.findById(id);
    }
}
