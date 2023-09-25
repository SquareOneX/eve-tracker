package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString(exclude = {"transactions"})
@Entity
@Table(name = "items")
public class Item {
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String name;
    private Boolean published;
    @OneToOne(mappedBy = "itemInfo", cascade = CascadeType.ALL)
    private Blueprint blueprint;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Float getAvgCost(){
        if (transactions.isEmpty())
            return null;

        float cost = 0.0f;
        int buyCount = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getIsBuy()) {
                cost += transaction.getPrice() / transaction.getQuantity();
                buyCount++;
            }
        }
        return cost / buyCount;
    }
}
