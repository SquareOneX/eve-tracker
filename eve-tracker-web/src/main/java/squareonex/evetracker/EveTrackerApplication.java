package squareonex.evetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "squareonex.evetrackerdata")
public class EveTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EveTrackerApplication.class, args);
    }

}
