package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"products", "materials"})
@Entity
@Table(name = "blueprints")
public class Blueprint {
    @EqualsAndHashCode.Include
    @EmbeddedId
    private BlueprintId id = new BlueprintId();
    @OneToMany(mappedBy = "id.blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintProduct> products = new HashSet<>();
    @OneToMany(mappedBy = "id.blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintMaterial> materials = new HashSet<>();

    public Blueprint() {
        this.id.itemInfo = new Item();
        this.id.activity = new Activity();
    }

    /**
     * Convenience method to return this objects key
     * @return instance of the id class for this object
     */
    public BlueprintId getKey(){
        return this.id;
    }

    public Item getItemInfo() {
        return this.id.itemInfo;
    }

    public Activity getActivity() {
        return this.id.activity;
    }

    public void setItemInfo(Item object) {
        this.id.itemInfo = object;
    }

    public void setActivity(Activity object) {
        this.id.activity = object;
    }

    public void setProducts(Set<BlueprintProduct> products) {
        for (BlueprintProduct product : products) {
            product.setBlueprint(this);
        }
        this.products = products;
    }

    public void setMaterials(Set<BlueprintMaterial> materials) {
        for (BlueprintMaterial material : materials) {
            material.setBlueprint(this);
        }
        this.materials = materials;
    }
}
