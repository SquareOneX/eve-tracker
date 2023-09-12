package squareonex.evetracker.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ItemService;

import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {
    private final ActivityService activityService;
    private final BlueprintService blueprintService;
    private final ItemService itemService;

    public DataLoader(ActivityService activityService, BlueprintService blueprintService, ItemService itemService) {
        this.activityService = activityService;
        this.blueprintService = blueprintService;
        this.itemService = itemService;
    }

    @Override
    public void run(String... args) throws Exception {
        Item material1 = new Item(1L, "material1");
        Item blueprintItem = new Item(3L, "item");
        Activity activity = new Activity(1, "activity1");
        Item product1 = new Item(4L, "product1");

        Blueprint blueprint = new Blueprint(blueprintItem, activity, product1, Map.of(material1, 100), null);
        itemService.save(material1);
        itemService.save(blueprintItem);
        activityService.save(activity);
        itemService.save(product1);
        blueprintService.save(blueprint);
        System.out.println("Bootstrap data loaded...");
    }
}
