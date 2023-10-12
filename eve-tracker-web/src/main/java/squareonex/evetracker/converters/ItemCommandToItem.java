package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.BlueprintCommand;
import squareonex.evetracker.commands.BlueprintProductCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ItemCommandToItem implements Converter<ItemCommand, Item> {
    @Synchronized
    @Nullable
    @Override
    public Item convert(ItemCommand source) {
        if (source == null)
            return null;
        final Item target = new Item();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPublished(source.getPublished());
        return target;
    }
}
