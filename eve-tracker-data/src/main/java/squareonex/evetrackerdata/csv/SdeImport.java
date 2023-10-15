package squareonex.evetrackerdata.csv;

import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.List;

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
    public void run(String... args) throws Exception {
        System.out.println("............Attempting to load boostrap data............");

        activityRepository.saveAll(activityReader.readAll());
        System.out.println("Loaded Activities....");

        itemRepository.saveAll(itemReader.readAll());
        System.out.println("Loaded Items....");

        List<Blueprint> blueprints = blueprintReader.readAll();
        blueprintRepository.saveAll(blueprints);

        System.out.println("Loaded Blueprints....");

        System.out.println("............Successfully loaded boostrap data............");
    }
}
