package squareonex.evetrackerdata.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {
    @EqualsAndHashCode.Include
    Long id;
    LocalDateTime time;
    boolean isBuy;
    Integer quantity;
    Item item;
    float price;
}
