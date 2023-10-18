package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.Item;

@Component
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
