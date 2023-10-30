package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blueprint_copies")
public class BlueprintCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "blueprint_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_blueprintcopies_blueprints"))
    private Blueprint blueprint;
    private Float materialModifier = 1.0F;
    private Float timeModifier = 1.0F;
    private Integer maxRuns;
    private Float blueprintCost;

    public BlueprintCopy(Blueprint blueprint, Integer maxRuns, Float blueprintCost) {
        this.blueprint = blueprint;
        this.maxRuns = maxRuns;
        this.blueprintCost = blueprintCost;
    }
}
