package squareonex.evetrackerdata.csv.readers.item;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvConstraintViolationException;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ItemDTO {
    @NonNull
    @CsvBindByName(column = "typeID")
    private Long id;
    @NonNull
    @CsvBindByName(column = "typeName")
    private String name;
    @NonNull
    @CsvBindByName(column = "published")
    private Boolean published;

    public static class Verifier implements BeanVerifier<ItemDTO> {
        @Override
        public boolean verifyBean(ItemDTO item) throws CsvConstraintViolationException {
            return item.getPublished();
        }
    }
}
