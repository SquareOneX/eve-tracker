package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class BlueprintServiceImpl extends AbstractCrudService<Blueprint, BlueprintId> implements BlueprintService {
    private final ItemRepository itemRepository;
    private final ActivityRepository activityRepository;
    public BlueprintServiceImpl(BlueprintRepository blueprintRepository, ItemRepository itemRepository, ActivityRepository activityRepository) {
        super(blueprintRepository);
        this.itemRepository = itemRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Set<Blueprint> getBlueprints() {
        return super.findAll();
    }

    @Override
    public Blueprint findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId) {
        Optional<Item> optionalItem = itemRepository.findById(blueprintId);
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);

        if (optionalItem.isEmpty())
            throw new IllegalArgumentException("Blueprint with item id = " + blueprintId + " not found");
        if (optionalActivity.isEmpty())
            throw new IllegalArgumentException("Activity with item id = " + activityId + " not found");
        BlueprintId id = new BlueprintId(optionalItem.get(), optionalActivity.get());
        Optional<Blueprint> blueprint = repository.findById(id);
        return blueprint.orElse(null);
    }
}
