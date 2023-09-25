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
        Item blueprintItem2 = new Item(955L, "Atron Blueprint");
        blueprintItem2.setPublished(true);
        Blueprint blueprint1 = new Blueprint();
        Blueprint blueprint2 = new Blueprint();
        blueprint2.setItemInfo(blueprintItem2);
        blueprint2.setQuantity(1);
        blueprint2.setMaxRuns(100);

        Item item1 = new Item(585L, "Slasher");
        item1.setPublished(true);
        item1.setBlueprint(blueprint1);
        Item item2 = new Item(608L, "Atron");
        item2.setPublished(true);
        item2.setBlueprint(blueprint2);

        Item blueprintItem1 = new Item(689L, "Slasher Blueprint");
        blueprintItem1.setPublished(true);
        blueprint1.setItemInfo(blueprintItem1);
        blueprint1.setMaxRuns(100);
        blueprint1.setQuantity(1);

        blueprint1.setProducts(Set.of(new BlueprintProduct(blueprint1, item1, 1)));

        blueprint2.setProducts(Set.of(new BlueprintProduct(blueprint2, item2, 1)));

        Activity activity1 = new Activity();
        activity1.setId(1);
        activity1.setName("Manufacturing");

        blueprint1.setActivity(activity1);
        blueprint2.setActivity(activity1);

        Set<BlueprintMaterial> materialSet1 = new HashSet<>();
        Set<BlueprintMaterial> materialSet2 = new HashSet<>();

        Item material1 = new Item(34L, "Tritanium");
        material1.setPublished(true);
        Item material2 = new Item(35L, "Pyerite");
        material2.setPublished(true);
        Item material3 = new Item(36L, "Mexallon");
        material3.setPublished(true);
        Item material4 = new Item(37L, "Isogen");
        material4.setPublished(true);

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

        Job job1 = new Job(item1, 10L, user1, false);
        job1.setId(0L);
        job1.setStartedTime(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 13, 28));
        job1.setFinishedTime(LocalDateTime.now().plusHours(2));

        Job job2 = new Job(item2, 10L, user1, false);
        job2.setId(1L);

        activityService.save(activity1);

        productService.save(blueprintItem1);
        productService.save(blueprintItem2);

        productService.save(item1);
        productService.save(item2);
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
