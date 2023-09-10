package squareonex.evetrackerdata.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Blueprint extends Item{
    Item product;
    Map<Item, Integer> materials;
    Long time;
}
