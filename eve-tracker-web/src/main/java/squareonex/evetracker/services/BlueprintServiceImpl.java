package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BlueprintServiceImpl implements BlueprintService {
    private final BlueprintRepository blueprintRepository;
    private final ItemRepository itemRepository;
    private final ActivityRepository activityRepository;
    public BlueprintServiceImpl(BlueprintRepository blueprintRepository, ItemRepository itemRepository, ActivityRepository activityRepository) {
        this.blueprintRepository = blueprintRepository;
        this.itemRepository = itemRepository;
        this.activityRepository = activityRepository;
    }

    public Set<Blueprint> getBlueprints() {
        Set<Blueprint> set = new HashSet<>();
        blueprintRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Blueprint findById(BlueprintId blueprintId) {
        return blueprintRepository.findById(blueprintId).orElse(null);
    }

    public Blueprint findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId) {
        Optional<Item> optionalItem = itemRepository.findById(blueprintId);
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);

        if (optionalItem.isEmpty())
            throw new IllegalArgumentException("Blueprint with item id = " + blueprintId + " not found");
        if (optionalActivity.isEmpty())
            throw new IllegalArgumentException("Activity with item id = " + activityId + " not found");
        BlueprintId id = new BlueprintId(optionalItem.get(), optionalActivity.get());
        Optional<Blueprint> blueprint = blueprintRepository.findById(id);
        return blueprint.orElse(null);
    }
}
