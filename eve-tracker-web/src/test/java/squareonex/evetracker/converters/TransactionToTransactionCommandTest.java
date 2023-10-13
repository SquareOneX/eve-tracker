package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TransactionToTransactionCommandTest extends ConverterTestTemplate {
    private static final Long TRANS_ID = 0L;
    private static final Long ITEM_ID = 0L;
    private static final String ITEM_NAME = "Item";
    private static final Integer TRANS_QTY = 100;
    private static final LocalDateTime TRANS_DATE = LocalDateTime.now();
    private static final Float TRANS_PRICE = 1000F;
    private static final Boolean TRANS_IS_BUY = true;
    TransactionToTransactionCommand converter;

    @BeforeEach
    void setUp() {
        this.converter = new TransactionToTransactionCommand(new ItemToItemCommand());
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new TransactionCommand(), converter.convert(new Transaction()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        Transaction source = createSource();

        TransactionCommand converted = converter.convert(source);
        assertEquals(TRANS_ID, converted.getId());
        assertEquals(TRANS_QTY, converted.getQuantity());
        assertEquals(TRANS_PRICE, converted.getPrice());
        assertEquals(TRANS_DATE, converted.getDate());
        assertEquals(ITEM_ID, converted.getItemCommand().getId());
        assertEquals(ITEM_NAME, converted.getItemCommand().getName());
        assertEquals(TRANS_IS_BUY, converted.getIsBuy());
    }

    private Transaction createSource() {
        Transaction target = new Transaction();
        target.setId(TRANS_ID);
        target.setItem(new Item(ITEM_ID, ITEM_NAME));
        target.setQuantity(TRANS_QTY);
        target.setDate(TRANS_DATE);
        target.setPrice(TRANS_PRICE);
        target.setIsBuy(TRANS_IS_BUY);
        return target;
    }
}