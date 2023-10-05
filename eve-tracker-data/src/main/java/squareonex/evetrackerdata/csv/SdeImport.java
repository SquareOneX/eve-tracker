package squareonex.evetrackerdata.csv;

import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ProductService;

import java.io.FileNotFoundException;
import java.util.List;

public class SdeImport implements BootstrapLoader{
    private final ActivityService activityService;
    private final BlueprintReader blueprintReader;
    private final ActivityReader activityReader;
    private final ItemReader itemReader;
    private final ProductService itemService;
    private final BlueprintService blueprintService;

    public SdeImport(ActivityService activityService, BlueprintReader blueprintReader, ActivityReader activityReader, ItemReader itemReader, ProductService itemService, BlueprintService blueprintService) {
        this.activityService = activityService;
        this.blueprintReader = blueprintReader;
        this.activityReader = activityReader;
        this.itemReader = itemReader;
        this.itemService = itemService;
        this.blueprintService = blueprintService;
    }

    public List<Activity> readActivities() throws FileNotFoundException {
        return activityReader.readAll();
    }

    public List<Item> readItems() throws FileNotFoundException {
        return itemReader.readAll();
    }

    public List<Blueprint> readBlueprints(){
        try {
            return blueprintReader.readAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("............Attempting to load boostrap data............");

        activityService.saveAll(activityReader.readAll());
        System.out.println("Loaded Activities....");

        itemService.saveAll(itemReader.readAll());
        System.out.println("Loaded Items....");

        blueprintService.saveAll(blueprintReader.readAll());
        System.out.println("Loaded Blueprints....");

        System.out.println("............Successfully loaded boostrap data............");
    }
}
