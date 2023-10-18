package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.Item;

@Component
public class ItemToItemCommand implements Converter<Item, ItemCommand> {
    @Nullable
    @Synchronized
    @Override
    public ItemCommand convert(Item source) {
        if(source == null)
            return null;
        final ItemCommand target = new ItemCommand();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPublished(source.getPublished());
        return target;
    }
}
