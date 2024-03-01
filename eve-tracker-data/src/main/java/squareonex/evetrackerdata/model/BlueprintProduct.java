package squareonex.evetrackerdata.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import squareonex.evetrackerdata.model.ids.BlueprintProductId;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "blueprint_products")
@ToString
public class BlueprintProduct {
    @EmbeddedId
    @Setter(value = AccessLevel.PRIVATE)
    private BlueprintProductId id = new BlueprintProductId();
    private Integer quantity;
    private Float probability = 1F;

    public BlueprintAction getBlueprintAction() {
        return this.id.getBlueprint();
    }

    /**
     * Sets this instances blueprintAction while also updating the blueprint passed
     * @param blueprint a blueprintAction
     */
    public void setBlueprintAction(BlueprintAction blueprint) {
        this.id.setBlueprint(blueprint);
        blueprint.getProducts().add(this);
    }

    public Item getProduct() {
        return this.id.getProduct();
    }

    public void setProduct(Item product) {
        if (product != null)
            product.getBlueprints().add(this);
        else if (this.getProduct() != null) {
            this.getProduct().getBlueprints().remove(this);
        }
        this.id.setProduct(product);
    }
}
