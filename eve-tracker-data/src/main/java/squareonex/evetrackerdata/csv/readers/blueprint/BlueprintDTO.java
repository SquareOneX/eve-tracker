package squareonex.evetrackerdata.csv.readers.blueprint;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BlueprintDTO {
    @EqualsAndHashCode.Include
    @CsvBindByName(column = "typeId")
    private Long id;
    @CsvBindByName(column = "activityId")
    private Integer activityId;
    @CsvBindByName(column = "time")
    private Long time;

    BlueprintKey getKey() {
        return new BlueprintKey(this.id, this.activityId);
    }

    static class Verifier implements BeanVerifier<BlueprintDTO> {
        @Override
        public boolean verifyBean(BlueprintDTO bp) throws CsvConstraintViolationException {
            return bp.getId() != null && bp.getActivityId() != null;
        }
    }
}
