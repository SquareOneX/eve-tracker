package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blueprint_originals")
public class BlueprintOriginal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "blueprint_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_blueprintoriginals_blueprints"))
    private Blueprint blueprint;
    @NonNull private Float materialModifier = 1.0F;
    @NonNull private Float timeModifier = 1.0F;
    private Float blueprintCost;

    public BlueprintOriginal(Blueprint blueprint, Float blueprintCost) {
        this.blueprint = blueprint;
        this.blueprintCost = blueprintCost;
    }

    public void setBlueprint(Blueprint blueprint) {
        if (blueprint != null)
            blueprint.getOriginals().add(this);
        else if (this.getBlueprint() != null)
            this.getBlueprint().getOriginals().remove(this);
        this.blueprint = blueprint;
    }
}
