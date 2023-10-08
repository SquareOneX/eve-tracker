package squareonex.evetrackerdata.services.map;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;

import java.util.HashSet;
import java.util.Set;

@Service
public class BlueprintServiceMapImpl extends AbstractMapService<Blueprint, BlueprintId> implements BlueprintService {
    private final ActivityService activityService;

    public BlueprintServiceMapImpl(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public Set<Blueprint> findAll() {
        return super.findAll();
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public void deleteById(BlueprintId id) {
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
            return super.save(new BlueprintId(object.getItemInfo(), object.getActivity()), object);
        }
    }

    @Override
    public Iterable<Blueprint> saveAll(Iterable<Blueprint> iterator) {
        Set<Blueprint> set = new HashSet<>();
        for (Blueprint blueprint : iterator) {
            set.add(this.save(blueprint));
        }
        return set;
    }

    @Override
    public Blueprint findById(BlueprintId id) {
        return super.findById(id);
    }
}
