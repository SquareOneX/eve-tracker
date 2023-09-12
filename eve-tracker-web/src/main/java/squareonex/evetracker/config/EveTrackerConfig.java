package squareonex.evetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ItemService;
import squareonex.evetrackerdata.services.map.ActivityServiceMapImpl;
import squareonex.evetrackerdata.services.map.BlueprintServiceMapImpl;
import squareonex.evetrackerdata.services.map.ItemServiceMapImpl;

@Configuration
public class EveTrackerConfig {
    @Bean
    ActivityService activityService(){
        return new ActivityServiceMapImpl();
    }

    @Bean
    BlueprintService blueprintService(ActivityService activityService, ItemService itemService){
        return new BlueprintServiceMapImpl(itemService, activityService);
    }

    @Bean
    ItemService itemService(){
        return new ItemServiceMapImpl();
    }
}
