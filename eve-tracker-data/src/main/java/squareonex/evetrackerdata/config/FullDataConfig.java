package squareonex.evetrackerdata.config;

import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import squareonex.evetrackerdata.csv.BootstrapLoader;
import squareonex.evetrackerdata.csv.SdeImport;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.csv.readers.activity.ActivityReaderImpl;
import squareonex.evetrackerdata.csv.readers.blueprint.BlueprintReaderImpl;
import squareonex.evetrackerdata.csv.readers.item.ItemReaderImpl;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

@Configuration
@Profile("fullBootstrapData")
public class FullDataConfig {
    @Bean
    BootstrapLoader bootstrapLoader(BlueprintReader blueprintReader, ActivityReader activityReader, ItemReader itemReader, ActivityRepository activityService, ItemRepository itemService, BlueprintRepository blueprintService) {
        return new SdeImport(activityService, itemService, blueprintService, blueprintReader, activityReader, itemReader);
    }

    @Bean
    BlueprintReader blueprintReader() {
        return new BlueprintReaderImpl();
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
