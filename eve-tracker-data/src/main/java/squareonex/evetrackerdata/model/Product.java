package squareonex.evetrackerdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseItem {
    Blueprint blueprint;
    Set<Transaction> transactions;

    public Product(Long id, String name, Blueprint blueprint) {
        super(id, name);
        this.blueprint = blueprint;
    }

    public Float getAvgCost(){
        //TODO Issue #7
        return null;
    }
}
