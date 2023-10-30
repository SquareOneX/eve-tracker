package squareonex.evetrackerdata.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import squareonex.evetrackerdata.model.ids.BlueprintMaterialId;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "blueprint_materials")
public class BlueprintMaterial {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private BlueprintMaterialId id = new BlueprintMaterialId();
    private Integer quantity;

    public BlueprintMaterial(BlueprintAction blueprint, Item material, Integer quantity) {
        this.id = new BlueprintMaterialId(blueprint, material);
        this.quantity = quantity;
    }

    public BlueprintAction getBlueprint() {
        return this.id.getBlueprint();
    }

    public void setBlueprint(BlueprintAction blueprint) {
        this.id.setBlueprint(blueprint);
    }

    public Item getMaterial() {
        return this.id.getMaterial();
    }

    public void setMaterial(Item material) {
        this.id.setMaterial(material);
    }
}