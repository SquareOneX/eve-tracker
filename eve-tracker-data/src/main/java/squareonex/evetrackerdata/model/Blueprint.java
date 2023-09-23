package squareonex.evetrackerdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"products", "materials"})
public class Blueprint {
    @EqualsAndHashCode.Include
    private Item itemInfo;
    @EqualsAndHashCode.Include
    private Activity activity;
    Set<Item> products;
    Set<BlueprintMaterial> materials;
    Integer quantity;
    Integer maxRuns;

    public Blueprint() {
        this.itemInfo = new Item();
        this.activity = new Activity();
    }

    /**
     * Convenience method to return this objects key
     * @return instance of the id class for this object
     */
    public BlueprintKey getKey(){
        return new BlueprintKey(itemInfo, activity);
    }
}
