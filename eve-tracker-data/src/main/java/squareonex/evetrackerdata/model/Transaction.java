package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "is_buy")
    private Boolean isBuy;
    @Column(name = "qty")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_transactions_items"))
    private Item item;
    @Column(name = "price")
    private Float price;

    public Transaction(LocalDateTime date, Boolean isBuy, Integer quantity, Item item, Float price) {
        this.date = date;
        this.isBuy = isBuy;
        this.quantity = quantity;
        this.item = item;
        this.price = price;
    }
}
