package squareonex.evetrackerdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Product extends BaseItem {
    Blueprint blueprint;
    Set<Transaction> transactions = new HashSet<>();

    public Product(Long id, String name, Blueprint blueprint) {
        super(id, name);
        this.blueprint = blueprint;
    }

    public Float getAvgCost(){
        if (transactions.isEmpty())
            return null;

        float cost = 0.0f;
        for (Transaction transaction : transactions) {
            if (transaction.getIsBuy()) {
                cost += transaction.getPrice() / transaction.getQuantity();
            }
        }
        return cost / transactions.size();
    }
}
