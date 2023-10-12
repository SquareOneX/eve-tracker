package squareonex.evetracker.converters;

import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

public class TransactionCommandToTransaction implements Converter<TransactionCommand, Transaction> {
    private final Converter<ItemCommand, Item> itemCommandToItem;

    public TransactionCommandToTransaction(Converter<ItemCommand, Item>  itemCommandToItem) {
        this.itemCommandToItem = itemCommandToItem;
    }

    @Override
    public Transaction convert(TransactionCommand source) {
        if (source == null)
            return null;
        Transaction target = new Transaction(
                source.getDate(),
                source.getIsBuy(),
                source.getQuantity(),
                itemCommandToItem.convert(source.getItemCommand()),
                source.getPrice()
        );
        target.setId(source.getId());
        return target;
    }
}
