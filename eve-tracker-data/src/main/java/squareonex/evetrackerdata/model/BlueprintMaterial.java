package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "blueprint_materials")
public class BlueprintMaterial {
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
    @JoinColumn(name = "material_id")
    private Item material;
    private Integer quantity;
}
