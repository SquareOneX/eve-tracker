package squareonex.evetrackerdata.csv.readers.item;

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
public class ItemDTO {
    @EqualsAndHashCode.Include
    @CsvBindByName(column = "typeID")
    private Long id;
    @CsvBindByName(column = "typeName")
    private String name;
    @CsvBindByName(column = "published")
    private boolean published;

    public static class Verifier implements BeanVerifier<ItemDTO> {
        @Override
        public boolean verifyBean(ItemDTO item) throws CsvConstraintViolationException {
            return item.isPublished();
        }
    }
}
