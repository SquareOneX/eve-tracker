package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(exclude = {"transactions", "blueprints"})
@Entity
@Table(name = "items")
public class Item {
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String name;
    private Boolean published;
    @OneToMany(mappedBy = "id.product", cascade = CascadeType.ALL)
    private Set<BlueprintProduct> blueprints = new HashSet<>();
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();
    private Float avgCost;

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @PreUpdate
    void preUpdate() {
        calcAvgCost();
    }

    @PrePersist()
    void prePersist() {
        calcAvgCost();
    }

    void calcAvgCost() {
        Double price = 0D;
        Long qty = 0L;
        for (Transaction transaction : this.transactions) {
            if (transaction.getIsBuy()) {
                price += transaction.getPrice();
                qty += transaction.getQuantity();
            }
        }
        if (qty > 0) {
            this.avgCost = (float) (price / qty);
            log.debug("Updating Item avgCost");
        }
    }
}
