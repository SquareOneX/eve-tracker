package squareonex.evetrackerdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"transactions"})
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
