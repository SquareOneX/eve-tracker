package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "blueprint_products")
@ToString
public class BlueprintProduct {
    @NonNull
    @EqualsAndHashCode.Include
    @Id
    @ManyToOne
    @JoinColumn(name = "blueprint_id", referencedColumnName = "blueprint_id")
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    private Blueprint blueprint;
    @NonNull
    @EqualsAndHashCode.Include
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Item product;
    private Integer quantity;

    public void setBlueprint(Blueprint blueprint) {
        blueprint.getProducts().add(this);
        this.blueprint = blueprint;
    }

    public void setProduct(Item product) {
        product.getBlueprints().add(this);
        this.product = product;
    }
}
