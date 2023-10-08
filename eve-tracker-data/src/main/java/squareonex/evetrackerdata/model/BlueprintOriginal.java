package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @NonNull private Blueprint blueprint;
    @NonNull private Float materialModifier = 1.0F;
    @NonNull private Float timeModifier = 1.0F;
    @NonNull private Float blueprintCost;
}
