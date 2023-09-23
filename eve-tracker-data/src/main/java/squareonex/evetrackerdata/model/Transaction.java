package squareonex.evetrackerdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull private LocalDateTime time;
    @NonNull private Boolean isBuy;
    @NonNull private Integer quantity;
    @NonNull private Product item;
    @NonNull private Float price;
}
