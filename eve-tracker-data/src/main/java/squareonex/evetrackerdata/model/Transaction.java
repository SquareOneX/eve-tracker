package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    public void setItem(Item item) {
        if (item != null)
            item.getTransactions().add(this);
        else if (this.getItem() != null)
            this.getItem().getTransactions().remove(this);
        this.item = item;
    }

    @PostPersist
    void postPersist() {
        this.item.calcAvgCost();
    }

    @PostUpdate
    void postUpdate() {
        this.item.calcAvgCost();
    }
}
