package squareonex.evetrackerdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Blueprint extends BlueprintKey {
    Set<Product> products;
    Set<BlueprintMaterial> materials;
    Integer quantity;
    Integer maxRuns;

    /**
     * Convenience method to return this objects key
     * @return instance of the id class for this object
     */
    public BlueprintKey getKey(){
        return new BlueprintKey(this.getId(), this.getName(), this.getActivity());
    }
}
