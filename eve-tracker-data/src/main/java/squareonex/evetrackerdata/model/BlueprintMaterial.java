package squareonex.evetrackerdata.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BlueprintMaterial {
    @NonNull
    @EqualsAndHashCode.Include
    private Blueprint blueprint;
    @NonNull
    @EqualsAndHashCode.Include
    private Item material;
    private Integer quantity;
}
