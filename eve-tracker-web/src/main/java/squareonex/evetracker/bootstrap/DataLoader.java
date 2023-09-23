package squareonex.evetracker.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.services.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final ActivityService activityService;
    private final BlueprintService blueprintService;
    private final ProductService productService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final JobService jobService;

    public DataLoader(ActivityService activityService, BlueprintService blueprintService, ProductService productService,
                      TransactionService transactionService, UserService userService, JobService jobService) {
        this.activityService = activityService;
        this.blueprintService = blueprintService;
        this.productService = productService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.jobService = jobService;
    }

    @Override
    public void run(String... args) throws Exception {
        Blueprint blueprint1 = new Blueprint();
        Blueprint blueprint2 = new Blueprint();
        blueprint2.setId(955L);
        blueprint2.setName("Atron Blueprint");
        blueprint2.setQuantity(1);
        blueprint2.setMaxRuns(100);

        Product product1 = new Product(585L, "Slasher", blueprint1);
        Product product2 = new Product(608L, "Atron", blueprint2);

        blueprint1.setId(689L);
        blueprint1.setName("Slasher Blueprint");
        blueprint1.setMaxRuns(100);
        blueprint1.setQuantity(1);
        blueprint1.setProducts(Set.of(product1));

        blueprint2.setProducts(Set.of(product2));

        Activity activity1 = new Activity();
        activity1.setId(1);
        activity1.setName("Manufacturing");

        blueprint1.setActivity(activity1);
        blueprint2.setActivity(activity1);

        Set<BlueprintMaterial> materialSet1 = new HashSet<>();
        Set<BlueprintMaterial> materialSet2 = new HashSet<>();

        Product material1 = new Product(34L, "Tritanium",  null);
        Product material2 = new Product(35L, "Pyerite", null);
        Product material3 = new Product(36L, "Mexallon", null);
        Product material4 = new Product(37L, "Isogen", null);

        materialSet1.add(new BlueprintMaterial(blueprint1, material1, 32_000));
        materialSet1.add(new BlueprintMaterial(blueprint1, material2, 6_000));
        materialSet1.add(new BlueprintMaterial(blueprint1, material3, 2_500));
        materialSet1.add(new BlueprintMaterial(blueprint1, material4, 500));

        materialSet2.add(new BlueprintMaterial(blueprint2, material1, 32_000));
        materialSet2.add(new BlueprintMaterial(blueprint2, material2, 6_000));
        materialSet2.add(new BlueprintMaterial(blueprint2, material3, 2_500));
        materialSet2.add(new BlueprintMaterial(blueprint2, material4, 500));

        blueprint1.setMaterials(materialSet1);
        blueprint2.setMaterials(materialSet2);

        Transaction transaction1 = new Transaction( LocalDateTime.MIN, true, 64_000, material1, 273_280.0f);
        transaction1.setId(0L);
        material1.setTransactions(Set.of(transaction1));
        Transaction transaction2 = new Transaction( LocalDateTime.MIN, true, 12_000, material2, 124_320.0f);
        transaction2.setId(1L);
        material2.setTransactions(Set.of(transaction2));
        Transaction transaction3 = new Transaction(LocalDateTime.MIN, true, 5_000, material3, 245_000.0f);
        transaction3.setId(2L);
        material3.setTransactions(Set.of(transaction3));
        Transaction transaction4 = new Transaction(LocalDateTime.MIN, true, 1_000, material4, 465_000.0f);
        transaction4.setId(3L);
        material4.setTransactions(Set.of(transaction4));

        User user1 = new User("Sonni Cooper");
        user1.setId(0L);

        Job job1 = new Job(product1, 10L, user1, false);
        job1.setId(0L);
        job1.setStartedTime(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 13, 28));
        job1.setFinishedTime(LocalDateTime.now().plusHours(2));

        Job job2 = new Job(product2, 10L, user1, false);
        job2.setId(1L);

        activityService.save(activity1);

        productService.save(product1);
        productService.save(product2);
        productService.save(material1);
        productService.save(material2);
        productService.save(material3);
        productService.save(material4);

        blueprintService.save(blueprint1);
        blueprintService.save(blueprint2);

        transactionService.save(transaction1);
        transactionService.save(transaction2);
        transactionService.save(transaction3);
        transactionService.save(transaction4);

        userService.save(user1);

        jobService.save(job1);
        jobService.save(job2);

        System.out.println("............Bootstrap data loaded............");
    }
}
