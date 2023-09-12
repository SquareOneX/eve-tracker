package squareonex.evetrackerdata.model;

import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Blueprint extends BaseItem {
    @EqualsAndHashCode.Include
    Activity activity;
    Set<Product> products;
    Map<Product, Integer> materials;
    Integer quantity;
    Integer maxRuns;

    public Blueprint(Long id, String name, Activity activity) {
        super(id, name);
        this.activity = activity;
    }

    public Blueprint(Long id, String name, Activity activity, Set<Item> products, Map<Item, Integer> materials) {
        super(id, name);
        this.activity = activity;
        this.products = products;
        this.materials = materials;
    }
}
