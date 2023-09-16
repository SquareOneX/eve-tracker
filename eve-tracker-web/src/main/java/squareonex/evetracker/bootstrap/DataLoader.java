package squareonex.evetracker.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Product;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.services.ActivityService;
import squareonex.evetrackerdata.services.BlueprintService;
import squareonex.evetrackerdata.services.ProductService;
import squareonex.evetrackerdata.services.TransactionService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final ActivityService activityService;
    private final BlueprintService blueprintService;
    private final ProductService productService;
    private final TransactionService transactionService;

    public DataLoader(ActivityService activityService, BlueprintService blueprintService, ProductService productService,
                      TransactionService transactionService) {
        this.activityService = activityService;
        this.blueprintService = blueprintService;
        this.productService = productService;
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) throws Exception {
        Blueprint blueprint1 = new Blueprint();
        Product product1 = new Product(585L, "Slasher", blueprint1);
        blueprint1.setId(689L);
        blueprint1.setName("Slasher Blueprint");
        blueprint1.setMaxRuns(100);
        blueprint1.setQuantity(1);
        blueprint1.setProducts(Set.of(product1));

        Activity activity1 = new Activity(1, "Manufacturing");
        blueprint1.setActivity(activity1);

        Product material1 = new Product(34L, "Tritanium",  null);
        Product material2 = new Product(35L, "Pyerite", null);
        Product material3 = new Product(36L, "Mexallon", null);
        Product material4 = new Product(37L, "Isogen", null);
        Map<Product, Integer> materials = new HashMap<>();
        materials.put(material1, 32_000);
        materials.put(material2, 6_000);
        materials.put(material3, 2_500);
        materials.put(material4, 500);

        blueprint1.setMaterials(materials);

        Transaction transaction1 = new Transaction(0L, LocalDateTime.MIN, true, 64_000, material1, 273_280);
        material1.setTransactions(Set.of(transaction1));
        Transaction transaction2 = new Transaction(1L, LocalDateTime.MIN, true, 12_000, material2, 124_320);
        material2.setTransactions(Set.of(transaction2));
        Transaction transaction3 = new Transaction(2L, LocalDateTime.MIN, true, 5_000, material3, 245_000);
        material3.setTransactions(Set.of(transaction3));
        Transaction transaction4 = new Transaction(3L, LocalDateTime.MIN, true, 1_000, material4, 465_000);
        material4.setTransactions(Set.of(transaction4));

        activityService.save(activity1);

        productService.save(product1);
        productService.save(material1);
        productService.save(material2);
        productService.save(material3);
        productService.save(material4);

        blueprintService.save(blueprint1);

        transactionService.save(transaction1);
        transactionService.save(transaction2);
        transactionService.save(transaction3);
        transactionService.save(transaction4);

        System.out.println("............Bootstrap data loaded............");
    }
}
