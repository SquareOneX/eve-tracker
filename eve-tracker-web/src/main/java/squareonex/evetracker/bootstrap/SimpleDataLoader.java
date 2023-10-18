package squareonex.evetracker.bootstrap;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.csv.BootstrapLoader;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.repositories.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile({"default", "simpleBootstrapData"})
@Slf4j
public class SimpleDataLoader implements BootstrapLoader {
    private final ActivityRepository activityService;
    private final BlueprintRepository blueprintService;
    private final ItemRepository productService;
    private final TransactionRepository transactionService;
    private final UserRepository userService;
    private final JobRepository jobService;
    private final BlueprintCopyRepository blueprintCopyService;
    private final BlueprintOriginalRepository blueprintOriginalService;

    public SimpleDataLoader(ActivityRepository activityService, BlueprintRepository blueprintService, ItemRepository productService,
                            TransactionRepository transactionService, UserRepository userService, JobRepository jobService,
                            BlueprintCopyRepository blueprintCopyService, BlueprintOriginalRepository blueprintOriginalService) {
        this.activityService = activityService;
        this.blueprintService = blueprintService;
        this.productService = productService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.jobService = jobService;
        this.blueprintCopyService = blueprintCopyService;
        this.blueprintOriginalService = blueprintOriginalService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        /*
            Activities
         */
        Activity manufacturing = new Activity();
        manufacturing.setId(1);
        manufacturing.setName("Manufacturing");

        Activity copying = new Activity();
        copying.setId(5);
        copying.setName("Copying");

        Activity invention = new Activity();
        invention.setId(8);
        invention.setName("Invention");

        manufacturing = activityService.save(manufacturing);
        copying = activityService.save(copying);
        invention = activityService.save(invention);

        log.info(String.format("%d Activity entities loaded", activityService.count()));

        /*
            Users
         */

        User user1 = userService.save(new User("Sonni Cooper"));

        log.info(String.format("%d User entities loaded", userService.count()));

        /*
            Blueprints
         */

        Item blueprintItem1 = new Item();
        blueprintItem1.setId(689L);
        blueprintItem1.setName("Slasher Blueprint");
        blueprintItem1.setPublished(true);

        Item blueprintItem2 = new Item();
        blueprintItem2.setId(955L);
        blueprintItem2.setName("Atron Blueprint");
        blueprintItem2.setPublished(true);

        Item item = new Item();
        item.setId(11197L);
        item.setName("Stiletto Blueprint");
        item.setPublished(true);

        blueprintItem1 = productService.save(blueprintItem1);
        blueprintItem2 = productService.save(blueprintItem2);
        item = productService.save(item);

        Blueprint blueprint1 = new Blueprint();
        Blueprint blueprint2 = new Blueprint();
        Blueprint blueprint3 = new Blueprint();
        Blueprint blueprint4 = new Blueprint();

        blueprint1.setItemInfo(blueprintItem1);
        blueprint1.setActivity(manufacturing);
        blueprint1.setDuration(Duration.ofSeconds(6000));

        blueprint2.setItemInfo(blueprintItem2);
        blueprint2.setActivity(manufacturing);
        blueprint2.setDuration(Duration.ofSeconds(6000));

        blueprint3.setItemInfo(blueprintItem1);
        blueprint3.setActivity(copying);
        blueprint3.setDuration(Duration.ofSeconds(4800));

        blueprint4.setItemInfo(blueprintItem1);
        blueprint4.setActivity(invention);
        blueprint4.setDuration(Duration.ofSeconds(63900));

        blueprint1 = blueprintService.save(blueprint1);
        blueprint2 = blueprintService.save(blueprint2);
        blueprint3 = blueprintService.save(blueprint3);
        blueprint4 = blueprintService.save(blueprint4);

        BlueprintOriginal blueprintOriginal = blueprintOriginalService.save(new BlueprintOriginal(blueprint3, 5.0E07f));
        blueprintOriginal.setTimeModifier(1F);
        blueprintOriginal.setMaterialModifier(1F);

        BlueprintCopy blueprintCopy1 = blueprintCopyService.save(new BlueprintCopy(blueprint1, 100, 60_000F));
        blueprintCopy1.setMaterialModifier(0.9F);
        BlueprintCopy blueprintCopy2 = blueprintCopyService.save(new BlueprintCopy(blueprint1, 100, 50_000F));
        blueprintCopy2.setMaterialModifier(0.95F);

        log.info(String.format("%d Blueprint entities loaded", blueprintService.count()));

        /*
            Items
        */

        Item item1 = new Item();
        item1.setId(585L);
        item1.setName("Slasher");
        item1.setPublished(true);

        Item item2 = new Item();
        item2.setId(608L);
        item2.setName("Atron");
        item2.setPublished(true);

        item1 = productService.save(item1);
        item2 = productService.save(item2);

        BlueprintProduct bpProduct1 = new BlueprintProduct(blueprint1, item1, 1);
        BlueprintProduct bpProduct2 = new BlueprintProduct(blueprint2, item2, 1);
        BlueprintProduct bpProduct3 = new BlueprintProduct(blueprint4, item, 1, 0.3F);
        item1.setBlueprints(Set.of(bpProduct1));
        item2.setBlueprints(Set.of(bpProduct2));
        item.setBlueprints(Set.of(bpProduct3));

        blueprint1.setProducts(Set.of(bpProduct1));
        blueprint2.setProducts(Set.of(bpProduct2));
        blueprint4.setProducts(Set.of(bpProduct3));

        Set<BlueprintMaterial> materialSet1 = new HashSet<>();
        Set<BlueprintMaterial> materialSet2 = new HashSet<>();
        Set<BlueprintMaterial> materialSet3 = new HashSet<>();

        Item material1 = new Item(34L, "Tritanium");
        material1.setPublished(true);
        Item material2 = new Item(35L, "Pyerite");
        material2.setPublished(true);
        Item material3 = new Item(36L, "Mexallon");
        material3.setPublished(true);
        Item material4 = new Item(37L, "Isogen");
        material4.setPublished(true);
        Item material5 = new Item(20172L, "Datacore - Minmatar Starship Engineering");
        material5.setPublished(true);
        Item material6 = new Item(20411L, "Datacore - High Energy Physics");
        material6.setPublished(true);

        materialSet1.add(new BlueprintMaterial(blueprint1, material1, 32_000));
        materialSet1.add(new BlueprintMaterial(blueprint1, material2, 6_000));
        materialSet1.add(new BlueprintMaterial(blueprint1, material3, 2_500));
        materialSet1.add(new BlueprintMaterial(blueprint1, material4, 500));

        materialSet2.add(new BlueprintMaterial(blueprint2, material1, 32_000));
        materialSet2.add(new BlueprintMaterial(blueprint2, material2, 6_000));
        materialSet2.add(new BlueprintMaterial(blueprint2, material3, 2_500));
        materialSet2.add(new BlueprintMaterial(blueprint2, material4, 500));

        materialSet3.add(new BlueprintMaterial(blueprint4, material5, 2));
        materialSet3.add(new BlueprintMaterial(blueprint4, material6, 2));

        blueprint1.setMaterials(materialSet1);
        blueprint2.setMaterials(materialSet2);
        blueprint4.setMaterials(materialSet3);

        material1 = productService.save(material1);
        material2 = productService.save(material2);
        material3 = productService.save(material3);
        material4 = productService.save(material4);
        material5 = productService.save(material5);
        material6 = productService.save(material6);

        log.info(String.format("%d Item entities loaded", productService.count()));

        /*
            Transactions
         */

        LocalDateTime time = LocalDateTime.of(2023, Month.OCTOBER, 5, 13, 8);

        Transaction transaction1 = transactionService.save(new Transaction(time, true, 64_000, material1, 273_280.0f));
        Transaction transaction2 = transactionService.save(new Transaction(time, true, 12_000, material2, 124_320.0f));
        Transaction transaction3 = transactionService.save(new Transaction(time, true, 5_000, material3, 245_000.0f));
        Transaction transaction4 = transactionService.save(new Transaction(time, true, 1_000, material4, 465_000.0f));

        material1.setTransactions(Set.of(transaction1));
        material2.setTransactions(Set.of(transaction2));
        material3.setTransactions(Set.of(transaction3));
        material4.setTransactions(Set.of(transaction4));

        log.info(String.format("%d Transaction entities loaded", transactionService.count()));

        /*
            Jobs
         */

        Job job1 = new Job(item1, 10L, user1, false);
        job1.setStartedTime(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 13, 28));
        job1.setFinishedTime(LocalDateTime.now().plusHours(2));

        Job job2 = new Job(item2, 10L, user1, false);

        job1 = jobService.save(job1);
        job2 = jobService.save(job2);

        log.info(String.format("%d Job entities loaded", jobService.count()));
    }
}
