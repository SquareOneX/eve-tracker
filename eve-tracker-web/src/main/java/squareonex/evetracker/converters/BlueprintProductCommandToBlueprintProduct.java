package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintProductCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;

@Component
public class BlueprintProductCommandToBlueprintProduct implements Converter<BlueprintProductCommand, BlueprintProduct> {
    private final Converter<ItemCommand, Item> itemCommandToItem;

    public BlueprintProductCommandToBlueprintProduct(Converter<ItemCommand, Item> itemCommandToItem) {
        this.itemCommandToItem = itemCommandToItem;
    }

    @Nullable
    @Synchronized
    @Override
    public BlueprintProduct convert(BlueprintProductCommand source) {
        if (source == null)
            return null;
        final BlueprintProduct target = new BlueprintProduct();
        target.setProduct(itemCommandToItem.convert(source.getProduct()));
        target.setQuantity(source.getQuantity());
        target.setProbability(source.getProbability());

        return target;
    }
}
