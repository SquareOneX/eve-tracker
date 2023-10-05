package squareonex.evetrackerdata.csv;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.csv.readers.activity.ActivityReaderImpl;
import squareonex.evetrackerdata.csv.readers.blueprint.BlueprintReaderImpl;
import squareonex.evetrackerdata.csv.readers.item.ItemReaderImpl;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ProductService;

@Configuration
@Profile("fullBootstrapData")
public class FullDataConfig {
    @Bean
    BootstrapLoader bootstrapLoader(BlueprintReader blueprintReader, ActivityReader activityReader, ItemReader itemReader, ActivityService activityService, ProductService itemService, BlueprintService blueprintService) {
        return new SdeImport(activityService, blueprintReader, activityReader, itemReader, itemService, blueprintService);
    }

    @Bean
    BlueprintReader blueprintReader(EntityManager entityManager, ActivityRepository activityRepository, ItemRepository itemRepository) {
        return new BlueprintReaderImpl(activityRepository, itemRepository);
    }

    @Bean
    ActivityReader activityReader() {
        return new ActivityReaderImpl();
    }

    @Bean
    ItemReader itemReader() {
        return new ItemReaderImpl();
    }
}
