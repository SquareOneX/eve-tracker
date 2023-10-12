package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

public class TransactionToTransactionCommand implements Converter<Transaction, TransactionCommand> {
    private final Converter<Item, ItemCommand> itemToItemCommand;

    public TransactionToTransactionCommand(Converter<Item, ItemCommand> itemToItemCommand) {
        this.itemToItemCommand = itemToItemCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public TransactionCommand convert(Transaction source) {
        if (source == null)
            return null;
        TransactionCommand target = new TransactionCommand();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setPrice(source.getPrice());
        target.setQuantity(source.getQuantity());
        target.setIsBuy(source.getIsBuy());
        target.setItemCommand(itemToItemCommand.convert(source.getItem()));
        return target;
    }
}
