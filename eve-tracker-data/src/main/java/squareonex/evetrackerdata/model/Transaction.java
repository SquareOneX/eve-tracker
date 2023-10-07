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
    @Column(name = "date")
    @NonNull private LocalDateTime date;
    @Column(name = "is_buy")
    @NonNull private Boolean isBuy;
    @Column(name = "qty")
    @NonNull private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_transactions_items"))
    @NonNull private Item item;
    @Column(name = "price")
    @NonNull private Float price;
}
