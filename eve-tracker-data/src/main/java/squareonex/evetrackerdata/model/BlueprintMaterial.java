package squareonex.evetrackerdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlueprintMaterial {
    @NonNull
    private Blueprint blueprint;
    @NonNull
    private Product material;
    private Integer quantity;
}
