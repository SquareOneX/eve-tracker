package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blueprint_originals")
public class BlueprintOriginal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "blueprint_id", referencedColumnName = "blueprint_id"),
            @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    },
            foreignKey = @ForeignKey(name = "fk_blueprintoriginals_blueprints")
    )
    private Blueprint blueprint;
    @NonNull private Float materialModifier = 1.0F;
    @NonNull private Float timeModifier = 1.0F;
    private Float blueprintCost;

    public BlueprintOriginal(Blueprint blueprint, Float blueprintCost) {
        this.blueprint = blueprint;
        this.blueprintCost = blueprintCost;
    }
}
