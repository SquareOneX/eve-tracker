package squareonex.evetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import squareonex.evetracker.bootstrap.SimpleDataLoader;
import squareonex.evetracker.services.ProductionCostService;
import squareonex.evetracker.services.ProductionCostServiceImpl;
import squareonex.evetracker.services.StorageService;
import squareonex.evetracker.services.StorageServiceMapImpl;
import squareonex.evetrackerdata.csv.BootstrapLoader;
import squareonex.evetrackerdata.repositories.*;
import squareonex.evetrackerdata.services.*;
import squareonex.evetrackerdata.services.datajpa.*;

@Configuration
@EnableJpaRepositories(basePackages = "squareonex.evetrackerdata.repositories")
@ComponentScan(basePackages = "squareonex.evetrackerdata")
public class EveTrackerConfig {
    @Bean(name = "dataLoader")
    @Profile({"default", "simpleBootstrapData"})
    BootstrapLoader simpleDataLoader(ActivityService activityService, BlueprintService blueprintService,
                                     ProductService productService, TransactionService transactionService,
                                     UserService userService, JobService jobService, BlueprintCopyService blueprintCopyService,
                                     BlueprintOriginalService blueprintOriginalService) {
        return new SimpleDataLoader(activityService, blueprintService, productService, transactionService, userService, jobService, blueprintCopyService, blueprintOriginalService);
    }
    @Bean
    @Profile({"default", "jpa"})
    ActivityService activityService(ActivityRepository activityRepository) {
        return new ActivityServiceImpl(activityRepository);
    }
    @Bean
    @Profile({"default", "jpa"})
    BlueprintService blueprintService(BlueprintRepository blueprintRepository) {
        return new BlueprintServiceImpl(blueprintRepository);
    }
    @Bean
    @Profile({"default", "jpa"})
    BlueprintCopyService blueprintCopyService(BlueprintCopyRepository blueprintCopyRepository) {
        return new BlueprintCopyServiceImpl(blueprintCopyRepository);
    }
    @Bean
    @Profile({"default", "jpa"})
    BlueprintOriginalService blueprintOriginalService(BlueprintOriginalRepository bpoRepository) {
        return new BlueprintOriginalServiceImpl(bpoRepository);
    }
    @Bean
    @Profile({"default", "jpa"})
    ProductService productService(ItemRepository itemRepository) {
        return new ProductServiceImpl(itemRepository);
    }
    @Bean(name = "storageService")
    StorageService storageMapService() {
        return new StorageServiceMapImpl();
    }
    @Bean
    ProductionCostService productionCostService(StorageService storageService) {
        return new ProductionCostServiceImpl(storageService);
    }
    @Bean
    @Profile({"default", "jpa"})
    TransactionService transactionService(TransactionRepository transactionRepository) {
        return new TransactionServiceImpl(transactionRepository);
    }
    @Bean
    @Profile({"default", "jpa"})
    JobService jobService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }
    @Bean
    @Profile({"default", "jpa"})
    UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }
}
