package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"products", "materials"})
@Entity
@Table(name = "blueprints")
@IdClass(BlueprintKey.class)
public class Blueprint {
    @EqualsAndHashCode.Include
    @Id
    @OneToOne
    @JoinColumn(name = "blueprint_id")
    private Item itemInfo;
    @EqualsAndHashCode.Include
    @Id
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    @OneToMany(mappedBy = "blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintProduct> products;
    @OneToMany(mappedBy = "blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintMaterial> materials;
    private Integer quantity;
    private Integer maxRuns;

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
