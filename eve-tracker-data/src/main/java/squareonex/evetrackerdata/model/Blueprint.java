package squareonex.evetrackerdata.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Blueprint(Long id, String name, Activity activity, Integer quantity, Integer maxRuns) {
        super(id, name);
        this.activity = activity;
        this.quantity = quantity;
        this.maxRuns = maxRuns;
    }

    public Blueprint(Long id, String name, Activity activity, Set<Product> products, Map<Product, Integer> materials, Integer quantity, Integer maxRuns) {
        super(id, name);
        this.activity = activity;
        this.products = products;
        this.materials = materials;
        this.quantity = quantity;
        this.maxRuns = maxRuns;
    }
}
