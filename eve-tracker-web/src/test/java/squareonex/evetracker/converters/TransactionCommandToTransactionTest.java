package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Transaction;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransactionCommandToTransactionTest extends ConverterTestTemplate {
    private static final Long TRANS_ID = 0L;
    private static final Long ITEM_ID = 0L;
    private static final String ITEM_NAME = "Item";
    private static final Integer TRANS_QTY = 100;
    private static final LocalDateTime TRANS_DATE = LocalDateTime.now();
    private static final Float TRANS_PRICE = 1000F;
    private static final Boolean TRANS_IS_BUY = true;
    TransactionCommandToTransaction converter;
    @BeforeEach
    void setUp() {
        converter = new TransactionCommandToTransaction(new ItemCommandToItem());
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new Transaction(), converter.convert(new TransactionCommand()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        TransactionCommand source = createSource();
        Transaction converted = converter.convert(source);

        assertEquals(TRANS_ID, converted.getId());
        assertEquals(ITEM_ID, converted.getItem().getId());
        assertEquals(ITEM_NAME, converted.getItem().getName());
        assertEquals(TRANS_DATE, converted.getDate());
        assertEquals(TRANS_PRICE, converted.getPrice());
        assertEquals(TRANS_QTY, converted.getQuantity());
        assertEquals(TRANS_IS_BUY, converted.getIsBuy());
    }

    private TransactionCommand createSource() {
        TransactionCommand source = new TransactionCommand();
        source.setId(TRANS_ID);
        ItemCommand itemCommand = new ItemCommand();
        itemCommand.setId(ITEM_ID);
        itemCommand.setName(ITEM_NAME);
        source.setItemCommand(itemCommand);

        source.setDate(TRANS_DATE);
        source.setPrice(TRANS_PRICE);
        source.setQuantity(TRANS_QTY);
        source.setIsBuy(TRANS_IS_BUY);
        return source;
    }
}