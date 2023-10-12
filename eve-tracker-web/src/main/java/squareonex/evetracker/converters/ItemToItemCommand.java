package squareonex.evetracker.converters;

import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.BlueprintCommand;
import squareonex.evetracker.commands.BlueprintProductCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

public class ItemToItemCommand implements Converter<Item, ItemCommand> {
    @Override
    public ItemCommand convert(Item source) {
        if(source == null)
            return null;
        ItemCommand target = new ItemCommand();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPublished(source.getPublished());
        return target;
    }
}
