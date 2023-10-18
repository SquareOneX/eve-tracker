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
public class BlueprintProductToBlueprintProductCommand implements Converter<BlueprintProduct, BlueprintProductCommand> {
    private final Converter<Item, ItemCommand> itemToItemCommand;

    public BlueprintProductToBlueprintProductCommand(Converter<Item, ItemCommand> itemToItemCommand) {
        this.itemToItemCommand = itemToItemCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public BlueprintProductCommand convert(BlueprintProduct source) {
        if (source == null)
            return null;
        final BlueprintProductCommand target = new BlueprintProductCommand();
        target.setProduct(itemToItemCommand.convert(source.getProduct()));
        target.setQuantity(source.getQuantity());
        target.setProbability(source.getProbability());
        return target;
    }
}
