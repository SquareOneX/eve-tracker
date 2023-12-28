package squareonex.evetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import squareonex.evetracker.services.ProductionCostService;
import squareonex.evetracker.services.ProductionCostServiceImpl;
import squareonex.evetracker.services.StorageService;

@Configuration
@EnableJpaRepositories(basePackages = "squareonex.evetrackerdata.repositories")
@ComponentScan(basePackages = "squareonex.evetrackerdata")
public class EveTrackerConfig {
    @Bean
    ProductionCostService productionCostService(StorageService storageService) {
        return new ProductionCostServiceImpl(storageService);
    }
}
