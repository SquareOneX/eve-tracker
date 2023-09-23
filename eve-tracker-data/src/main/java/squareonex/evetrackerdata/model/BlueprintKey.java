package squareonex.evetrackerdata.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class BlueprintKey extends BaseItem{
    @EqualsAndHashCode.Include
    Activity activity;

    public BlueprintKey() {
        super();
    }

    public BlueprintKey(Long id, String name, Activity activity) {
        super(id, name);
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "BlueprintKey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "activity=" + activity +
                '}';
    }
}
