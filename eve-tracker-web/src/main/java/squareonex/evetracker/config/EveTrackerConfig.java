package squareonex.evetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import squareonex.evetrackerdata.services.*;
import squareonex.evetrackerdata.services.map.*;

@Configuration
public class EveTrackerConfig {
    @Bean
    ActivityService activityService(){
        return new ActivityServiceMapImpl();
    }
    @Bean
    BlueprintService blueprintService(ActivityService activityService){
        return new BlueprintServiceMapImpl(activityService);
    }
    @Bean
    ProductService itemService(){
        return new ProductServiceMapImpl();
    }
    @Bean
    StorageService storageService() {
        return new StorageServiceMapImpl();
    }
    @Bean
    ProductionCostService productionCostService(StorageService storageService) {
        return new ProductionCostServiceImpl(storageService);
    }
    @Bean
    TransactionService transactionService(){
        return new TransactionServiceMapImpl();
    }
    @Bean
    JobService jobService() {
        return new JobServiceImpl();
    }
    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }
}
