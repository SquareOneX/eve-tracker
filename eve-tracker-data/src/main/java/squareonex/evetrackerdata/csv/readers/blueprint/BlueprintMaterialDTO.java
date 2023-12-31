package squareonex.evetrackerdata.csv.readers.blueprint;

import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class BlueprintMaterialDTO extends BlueprintDTO {
    @EqualsAndHashCode.Include
    @CsvBindByName(column = "materialTypeId")
    private Long materialId;
    @CsvBindByName(column = "quantity")
    private Integer quantity;
}
