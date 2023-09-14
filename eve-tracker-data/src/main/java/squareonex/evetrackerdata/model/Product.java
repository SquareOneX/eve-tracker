package squareonex.evetrackerdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseItem {
    Blueprint blueprint;
    Set<Transaction> transactions = new HashSet<>();

    public Product(Long id, String name, Blueprint blueprint) {
        super(id, name);
        this.blueprint = blueprint;
    }

    public Float getAvgCost(){
        float cost = 0.0f;
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isBuy) {
                cost += transaction.getPrice() / transaction.getQuantity();
                count++;
            }
        }
        if(count == 0)
            return null;
        else
            return cost / count;
    }
}
