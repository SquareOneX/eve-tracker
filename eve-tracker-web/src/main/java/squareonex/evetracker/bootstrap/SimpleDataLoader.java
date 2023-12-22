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
import java.util.Optional;
import java.util.Set;

@Service
@Profile({"default", "simpleBootstrapData"})
@Slf4j
public class SimpleDataLoader implements BootstrapLoader {
    private final ActivityRepository activityService;
    private final BlueprintRepository blueprintService;
    private final ItemRepository itemService;
    private final TransactionRepository transactionService;
    private final UserRepository userService;
    private final JobRepository jobService;
    private final BlueprintCopyRepository blueprintCopyService;
    private final BlueprintOriginalRepository blueprintOriginalService;

    public SimpleDataLoader(ActivityRepository activityService, BlueprintRepository blueprintService, ItemRepository itemService, TransactionRepository transactionService, UserRepository userService, JobRepository jobService, BlueprintCopyRepository blueprintCopyService, BlueprintOriginalRepository blueprintOriginalService) {
        this.activityService = activityService;
        this.blueprintService = blueprintService;
        this.itemService = itemService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.jobService = jobService;
        this.blueprintCopyService = blueprintCopyService;
        this.blueprintOriginalService = blueprintOriginalService;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        loadData();

        int[] activityIds = new int[]{1, 5, 8};
        for (int id : activityIds) {
            Optional<Activity> activity = activityService.findById(id);
            if (activity.isEmpty()) {
                throw new RuntimeException("Expected Activity with id=" + id);
            }
        }

        if (userService.findByNameIgnoreCase("Sonni Cooper").isEmpty())
            throw new RuntimeException("Expected User with name='Sonni Cooper");

        long[] itemIds = new long[]{34L, 35L, 36L, 37L, 585L, 608L, 689L, 995L, 11197L, 11198L, 20172L, 20411L};
        for (long id : itemIds) {
            Optional<Item> item = itemService.findById(id);
            if (item.isEmpty())
                throw new RuntimeException("Expected Item with id=" + id);

            if (item.get().getName() == null)
                throw new RuntimeException("Expected non-null name for Item with id=" + id);
        }

        long[][] blueprintActions = new long[][]{
                new long[]{689L, 1, 5, 8},  //Slasher (Manufacturing, Copying, Invention)
                new long[]{995L, 1},        //Atron (Manufacturing)
                new long[]{11197L, 1}       //Stiletto (Manufacturing)
        };
        for (long[] blueprintAction : blueprintActions) {
            Optional<Blueprint> optionalBlueprint = blueprintService.findById(blueprintAction[0]);
            if (optionalBlueprint.isEmpty())
                throw new RuntimeException("Expected Blueprint with id=" + blueprintAction[0]);

            Blueprint blueprint = optionalBlueprint.get();

            if (blueprint.getActions().size() != blueprintAction.length - 1)
                throw new RuntimeException(
                        "Expected Blueprint with id=" + blueprintAction[0] + " to have " + (blueprintAction.length - 1)
                                + " actions, but was" + blueprint.getActions().size());
        }

        Item slasher = itemService.findById(585L).orElseThrow();
        if (slasher.getBlueprints().isEmpty())
            throw new RuntimeException("Expected Item with id=" + 689L + " to have a BlueprintProduct");

        log.info("Bootstrap data loaded");
    }

    private void loadData() {
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

        /*
            Users
         */

        User user = userService.save(new User("Sonni Cooper"));

        /*
            Items
        */

        Item slasher = new Item(585L, "Slasher");
        Item atron = new Item(608L, "Atron");
        Item stiletto = new Item(11198L, "Stiletto");

        slasher = itemService.save(slasher);
        atron = itemService.save(atron);
        stiletto = itemService.save(stiletto);

        Item tritanium = new Item(34L, "Tritanium");
        Item pyerite = new Item(35L, "Pyerite");
        Item mexallon = new Item(36L, "Mexallon");
        Item isogen = new Item(37L, "Isogen");
        Item datacore1 = new Item(20172L, "Datacore - Minmatar Starship Engineering");
        Item datacore2 = new Item(20411L, "Datacore - High Energy Physics");

        tritanium = itemService.save(tritanium);
        pyerite = itemService.save(pyerite);
        mexallon = itemService.save(mexallon);
        isogen = itemService.save(isogen);
        datacore1 = itemService.save(datacore1);
        datacore2 = itemService.save(datacore2);

        /*
            Blueprints
         */

        Blueprint slasherBP = new Blueprint(689L, "Slasher Blueprint");
        Blueprint atronBP = new Blueprint(995L, "Atron Blueprint");
        Blueprint stilettoBP = new Blueprint(11197L, "Stiletto Blueprint");

        BlueprintAction slasherBPManufacturing = new BlueprintAction();
        BlueprintAction atronBPManufactuing = new BlueprintAction();
        BlueprintAction slasherBPCopying = new BlueprintAction();
        BlueprintAction slasherBPInvention = new BlueprintAction();
        BlueprintAction stilettoBPManufacturing = new BlueprintAction();

        slasherBPManufacturing.setBlueprint(slasherBP);
        slasherBPManufacturing.setActivity(manufacturing);
        slasherBPManufacturing.setDuration(Duration.ofSeconds(6_000));

        atronBPManufactuing.setActivity(manufacturing);
        atronBPManufactuing.setBlueprint(atronBP);
        atronBPManufactuing.setDuration(Duration.ofSeconds(6_000));

        slasherBPCopying.setBlueprint(slasherBP);
        slasherBPCopying.setActivity(copying);
        slasherBPCopying.setDuration(Duration.ofSeconds(4_800));

        slasherBPInvention.setBlueprint(slasherBP);
        slasherBPInvention.setActivity(invention);
        slasherBPInvention.setDuration(Duration.ofSeconds(63_900));

        stilettoBPManufacturing.setBlueprint(stilettoBP);
        stilettoBPManufacturing.setActivity(manufacturing);
        stilettoBPManufacturing.setDuration(Duration.ofSeconds(120_000));

        slasherBP.getActions().add(slasherBPManufacturing);
        slasherBP.getActions().add(slasherBPCopying);
        slasherBP.getActions().add(slasherBPInvention);
        atronBP.getActions().add(atronBPManufactuing);
        stilettoBP.getActions().add(stilettoBPManufacturing);

        // Materials

        Set<BlueprintMaterial> slasherManufacturingMats = new HashSet<>();
        Set<BlueprintMaterial> atronManufacturingMats = new HashSet<>();
        Set<BlueprintMaterial> slasherInventionMats = new HashSet<>();
        Set<BlueprintMaterial> stilettoManufacturingMats = new HashSet<>(); //TODO Add materials

        slasherManufacturingMats.add(new BlueprintMaterial(slasherBPManufacturing, tritanium, 32_000));
        slasherManufacturingMats.add(new BlueprintMaterial(slasherBPManufacturing, pyerite, 6_000));
        slasherManufacturingMats.add(new BlueprintMaterial(slasherBPManufacturing, mexallon, 2_500));
        slasherManufacturingMats.add(new BlueprintMaterial(slasherBPManufacturing, isogen, 500));

        atronManufacturingMats.add(new BlueprintMaterial(atronBPManufactuing, tritanium, 32_000));
        atronManufacturingMats.add(new BlueprintMaterial(atronBPManufactuing, pyerite, 6_000));
        atronManufacturingMats.add(new BlueprintMaterial(atronBPManufactuing, mexallon, 2_500));
        atronManufacturingMats.add(new BlueprintMaterial(atronBPManufactuing, isogen, 500));

        slasherInventionMats.add(new BlueprintMaterial(slasherBPInvention, datacore1, 2));
        slasherInventionMats.add(new BlueprintMaterial(slasherBPInvention, datacore2, 2));

        slasherBPManufacturing.setMaterials(slasherManufacturingMats);
        atronBPManufactuing.setMaterials(atronManufacturingMats);
        slasherBPInvention.setMaterials(slasherInventionMats);

        //Saving

        slasherBP = blueprintService.save(slasherBP);
        atronBP = blueprintService.save(atronBP);
        stilettoBP = blueprintService.save(stilettoBP);

        // Products
        BlueprintProduct slasherBPProduct = new BlueprintProduct();
        slasherBPProduct.setBlueprintAction(slasherBPManufacturing);
        slasherBPProduct.setProduct(slasher);
        slasherBPProduct.setQuantity(1);
        BlueprintProduct atronBPProduct = new BlueprintProduct();
        atronBPProduct.setBlueprintAction(atronBPManufactuing);
        atronBPProduct.setProduct(atron);
        atronBPProduct.setQuantity(1);
        BlueprintProduct stilettoBPInventionProduct = new BlueprintProduct();
        stilettoBPInventionProduct.setBlueprintAction(slasherBPInvention);
        stilettoBPInventionProduct.setProduct(stilettoBP);
        stilettoBPInventionProduct.setQuantity(1);
        stilettoBPInventionProduct.setProbability(0.3F);
        BlueprintProduct stilettoBPManufacturingProduct = new BlueprintProduct();
        stilettoBPManufacturingProduct.setBlueprintAction(stilettoBPManufacturing);
        stilettoBPManufacturingProduct.setProduct(stiletto);
        stilettoBPManufacturingProduct.setQuantity(1);


        BlueprintOriginal slasherBPO = blueprintOriginalService.save(new BlueprintOriginal(slasherBP, 5.0E07f));
        slasherBPO.setTimeModifier(1F);
        slasherBPO.setMaterialModifier(1F);

        BlueprintCopy slasherBPC1 = blueprintCopyService.save(new BlueprintCopy(slasherBP, 100, 60_000F));
        slasherBPC1.setMaterialModifier(0.9F);
        BlueprintCopy slasherBPC2 = blueprintCopyService.save(new BlueprintCopy(slasherBP, 100, 50_000F));
        slasherBPC2.setMaterialModifier(0.95F);
        BlueprintCopy stilettoBPC = blueprintCopyService.save(new BlueprintCopy(stilettoBP, 10, 750_000F));

        /*
            Transactions
         */

        LocalDateTime time = LocalDateTime.of(2023, Month.OCTOBER, 5, 13, 8);

        Transaction transaction1 = transactionService.save(new Transaction(time, true, 64_000, tritanium, 273_280.0f));
        Transaction transaction2 = transactionService.save(new Transaction(time, true, 12_000, pyerite, 124_320.0f));
        Transaction transaction3 = transactionService.save(new Transaction(time, true, 5_000, mexallon, 245_000.0f));
        Transaction transaction4 = transactionService.save(new Transaction(time, true, 1_000, isogen, 465_000.0f));

        tritanium.setTransactions(Set.of(transaction1));
        pyerite.setTransactions(Set.of(transaction2));
        mexallon.setTransactions(Set.of(transaction3));
        isogen.setTransactions(Set.of(transaction4));

        /*
            Jobs
         */

        Job job1 = new Job(slasher, 10L, user, false);
        job1.setActivity(manufacturing);
        job1.setStartedTime(LocalDateTime.of(2023, Month.SEPTEMBER, 20, 13, 28));
        job1.setFinishedTime(LocalDateTime.now().plusHours(2));

        Job job2 = new Job(atron, 10L, user, false);
        job2.setActivity(manufacturing);

        job1 = jobService.save(job1);
        job2 = jobService.save(job2);

        itemService.save(slasher);
        }
}
