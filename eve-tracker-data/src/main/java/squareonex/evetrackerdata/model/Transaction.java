package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private LocalDateTime time;
    @NonNull private Boolean isBuy;
    @NonNull private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "item_id")
    @NonNull private Item item;
    @NonNull private Float price;
}
