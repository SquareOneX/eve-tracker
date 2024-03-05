package squareonex.evetrackerdata.csv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.beans.Transient;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class SdeImport implements BootstrapLoader{
    private final ActivityRepository activityRepository;
    private final ItemRepository itemRepository;
    private final BlueprintRepository blueprintRepository;
    private final BlueprintReader blueprintReader;
    private final ActivityReader activityReader;
    private final ItemReader itemReader;

    public SdeImport(ActivityRepository activityRepository, ItemRepository itemRepository, BlueprintRepository blueprintRepository,
                     BlueprintReader blueprintReader, ActivityReader activityReader, ItemReader itemReader) {
        this.activityRepository = activityRepository;
        this.itemRepository = itemRepository;
        this.blueprintRepository = blueprintRepository;
        this.blueprintReader = blueprintReader;
        this.activityReader = activityReader;
        this.itemReader = itemReader;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Map<Integer, Activity> activities = activityReader.readAll();

        Map<Long, Item> items = itemReader.readAll();

        Map<Long, Blueprint> blueprints = blueprintReader.readAll(items, activities);

        int activityCount = activityRepository.saveAll(activities.values()).size();
        log.debug("Loaded " + activityCount + " activities");

        int blueprintCount = blueprintRepository.saveAll(blueprints.values()).size();
        log.debug("Loaded " + blueprintCount + " blueprints");

//        items.forEach((itemId, item) -> {
//            // If the item is a blueprint (which is a kind of item) it will exist in the database already but won't have
//            // any of the item data populated, so we have to manually override it as to not get any duplicate entity errors
//            // Note that we are just overwriting data that's being loaded from the csv (name, published, ...) and not the
//            // attributes that are managed by data jpa (avgCost, blueprints, transactions)
//            itemRepository.findById(itemId).ifPresentOrElse(
//                    (existingItem) -> {
//                        existingItem.setName(item.getName());
//                        existingItem.setPublished(item.getPublished());
//                    },
//                    () -> itemRepository.save(item)
//            );
//        });

        int itemCount = 0;
        for (Item value : items.values()) {
            if (!blueprints.containsKey(value.getId())){
                Item item = itemRepository.save(value);
                item.getId();
            }
            itemCount++;
        }
        log.debug("Loaded " + itemCount + " items");
    }
}
