package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import squareonex.evetrackerdata.model.ids.BlueprintId;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"products", "materials", "copies"})
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
    @OneToMany(mappedBy = "blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintCopy> copies = new HashSet<>();

    public Blueprint() {
        this.id.setItemInfo(new Item());
        this.id.setActivity(new Activity());
    }

    /**
     * Convenience method to return this objects key
     * @return instance of the id class for this object
     */
    public BlueprintId getKey(){
        return this.id;
    }

    public Item getItemInfo() {
        return this.id.getItemInfo();
    }

    public Activity getActivity() {
        return this.id.getActivity();
    }

    public void setItemInfo(Item object) {
        this.id.setItemInfo(object);
    }

    public void setActivity(Activity object) {
        this.id.setActivity(object);
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

    public void setCopies(Set<BlueprintCopy> copies) {
        for (BlueprintCopy copy : copies) {
            copy.setBlueprint(this);
        }
        this.copies = copies;
    }
}
