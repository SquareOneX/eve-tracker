package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;
import squareonex.evetrackerdata.model.ids.BlueprintProductId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "blueprint_products")
@ToString
public class BlueprintProduct {
    @EmbeddedId
    private BlueprintProductId id = new BlueprintProductId();
    private Integer quantity;
    private Float probability = 1F;

    public BlueprintProduct(Blueprint blueprint, Item item, int qty, float probability) {
        this.id = new BlueprintProductId(blueprint, item);
        this.quantity = qty;
        this.probability = probability;
    }
    public BlueprintProduct(Blueprint blueprint, Item item, int qty) {
        this.id = new BlueprintProductId(blueprint, item);
        this.quantity = qty;
    }

    public Blueprint getBlueprint() {
        return this.id.getBlueprint();
    }

    public void setBlueprint(Blueprint blueprint) {
        this.id.setBlueprint(blueprint);
    }

    public Item getProduct() {
        return this.id.getProduct();
    }

    public void setProduct(Item product) {
        this.id.setProduct(product);
    }
}
