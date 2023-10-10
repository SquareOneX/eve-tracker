package squareonex.evetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

@SpringBootApplication
@EntityScan(basePackages = "squareonex.evetrackerdata")
public class EveTrackerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(EveTrackerApplication.class, args);
        BlueprintRepository repository = ctx.getBean(BlueprintRepository.class);
        Iterable<Blueprint> all = repository.findAll();
    }

}
