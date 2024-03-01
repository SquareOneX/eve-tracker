package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import squareonex.evetrackerdata.model.ids.BlueprintActionId;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"products", "materials"})
@Entity
@Table(name = "blueprint_actions")
public class BlueprintAction {
    @EqualsAndHashCode.Include
    @EmbeddedId
    private BlueprintActionId id = new BlueprintActionId();
    @Column(name = "duration")
    private Duration duration;
    @OneToMany(mappedBy = "id.blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintProduct> products = new HashSet<>();
    @OneToMany(mappedBy = "id.blueprint", cascade = CascadeType.ALL)
    private Set<BlueprintMaterial> materials = new HashSet<>();

    public BlueprintAction(BlueprintActionId id) {
        this.id = id;
    }

    public void setProducts(Set<BlueprintProduct> products) {
        for (BlueprintProduct product : products) {
            product.setBlueprintAction(this);
        }
        this.products = products;
    }

    public void setMaterials(Set<BlueprintMaterial> materials) {
        for (BlueprintMaterial material : materials) {
            material.setBlueprint(this);
        }
        this.materials = materials;
    }

    public Blueprint getBlueprint() {
        return this.id.getBlueprint();
    }

    public void setBlueprint(Blueprint blueprint) {
        this.id.setBlueprint(blueprint);
    }

    public Activity getActivity() {
        return this.id.getActivity();
    }

    public void setActivity(Activity activity) {
        this.id.setActivity(activity);
    }
}
