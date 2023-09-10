package squareonex.evetrackerdata.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Blueprint{
    @EqualsAndHashCode.Include
    Item itemInfo;
    @EqualsAndHashCode.Include
    Activity activity;
    Item product;
    Map<Item, Integer> materials;
    Long time;
}
