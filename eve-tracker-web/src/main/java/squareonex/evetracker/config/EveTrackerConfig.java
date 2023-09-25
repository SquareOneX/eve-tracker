package squareonex.evetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import squareonex.evetracker.bootstrap.BootstrapLoader;
import squareonex.evetracker.bootstrap.SimpleDataLoader;
import squareonex.evetracker.bootstrap.csv.FullDataLoader;
import squareonex.evetrackerdata.repositories.*;
import squareonex.evetrackerdata.services.*;
import squareonex.evetrackerdata.services.datajpa.*;
import squareonex.evetrackerdata.services.map.*;

@Configuration
@EnableJpaRepositories(basePackages = "squareonex.evetrackerdata.repositories")
public class EveTrackerConfig {
    @Bean(name = "dataLoader")
    @Profile({"default", "simpleBootstrapData"})
    BootstrapLoader simpleDataLoader(ActivityService activityService, BlueprintService blueprintService,
                                     ProductService productService, TransactionService transactionService,
                                     UserService userService, JobService jobService) {
        return new SimpleDataLoader(activityService, blueprintService, productService, transactionService, userService, jobService);
    }

    @Bean(name = "dataLoader")
    @Profile("fullBootstrapData")
    BootstrapLoader fullDataLoader() {
        return new FullDataLoader();
    }

    @Bean(name = "activityService")
    @Profile({"default", "map"})
    ActivityService activityMapService() {
        return new ActivityServiceMapImpl();
    }

    @Bean(name = "activityService")
    @Profile("jpa")
    ActivityService activityJPAService(ActivityRepository activityRepository) {
        return new ActivityServiceImpl(activityRepository);
    }

    @Bean(name = "blueprintService")
    @Profile({"default", "map"})
    BlueprintService blueprintMapService(ActivityService activityService) {
        return new BlueprintServiceMapImpl(activityService);
    }

    @Bean(name = "blueprintService")
    @Profile("jpa")
    BlueprintService blueprintJPAService(BlueprintRepository blueprintRepository) {
        return new BlueprintServiceImpl(blueprintRepository);
    }

    @Bean(name = "itemService")
    @Profile({"default", "map"})
    ProductService itemMapService() {
        return new ProductServiceMapImpl();
    }

    @Bean(name = "itemService")
    @Profile("jpa")
    ProductService itemJPAService(ItemRepository itemRepository) {
        return new ProductServiceImpl(itemRepository);
    }

    @Bean
    StorageService storageService() {
        return new StorageServiceMapImpl();
    }

    @Bean
    ProductionCostService productionCostService(StorageService storageService) {
        return new ProductionCostServiceImpl(storageService);
    }

    @Bean(name = "transactionService")
    @Profile({"default", "map"})
    TransactionService transactionMapService() {
        return new TransactionServiceMapImpl();
    }

    @Bean(name = "transactionService")
    @Profile("jpa")
    TransactionService transactionJPAService(TransactionRepository transactionRepository) {
        return new TransactionServiceImpl(transactionRepository);
    }

    @Bean(name = "jobService")
    @Profile({"default", "map"})
    JobService jobMapService() {
        return new JobServiceMapImpl();
    }

    @Bean(name = "jobService")
    @Profile("jpa")
    JobService jobJPAService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }

    @Bean(name = "userService")
    @Profile({"default", "map"})
    UserService userMapService() {
        return new UserServiceMapImpl();
    }

    @Bean(name = "userService")
    @Profile("jpa")
    UserService userJPAService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
