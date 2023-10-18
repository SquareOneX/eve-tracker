package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetracker.converters.ItemCommandToItem;
import squareonex.evetracker.converters.ItemToItemCommand;
import squareonex.evetracker.converters.TransactionCommandToTransaction;
import squareonex.evetracker.converters.TransactionToTransactionCommand;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
    private static final Long ITEM_ID = 0L;
    private static final String ITEM_NAME = "Item Name";
    private static final Integer TRANS_QTY = 100;
    private static final Float TRANS_PRICE = 500_000F;
    private static final LocalDateTime TRANS_DATE = LocalDateTime.of(2023, Month.OCTOBER, 17, 14, 6);
    private static final Boolean TRANS_IS_BUY = true;
    private static final Long TRANS_ID = 1L;
    TransactionServiceImpl transactionService;
    @Mock TransactionRepository transactionRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.transactionService = new TransactionServiceImpl(
                transactionRepositoryMock,
                new TransactionToTransactionCommand(new ItemToItemCommand()),
                new TransactionCommandToTransaction(new ItemCommandToItem())
        );
    }

    @Test
    void saveOrUpdateCommandShouldSaveToDataModel() {
        TransactionCommand transactionCommand = createTransactionCommand();
        doAnswer(invocation -> invocation.getArgument(0)).when(transactionRepositoryMock).save(any(Transaction.class));

        assertNull(transactionCommand.getItemCommand().getAvgCost());

        TransactionCommand savedCommand = transactionService.saveOrUpdateCommand(transactionCommand);

        assertNotNull(savedCommand);
        verify(transactionRepositoryMock, times(1)).save(any(Transaction.class));
    }

    @Test
    void saveOrUpdateShouldRecalculatePrices() {

    }

    @Test
    void findAll() {
        assertNotNull(transactionService.findAll());

        verify(transactionRepositoryMock, times(1)).findAll();
    }

    private static TransactionCommand createTransactionCommand() {
        TransactionCommand transactionCommand = new TransactionCommand();
        ItemCommand itemCommand = new ItemCommand();
        itemCommand.setId(ITEM_ID);
        itemCommand.setName(ITEM_NAME);
        transactionCommand.setItemCommand(itemCommand);
        transactionCommand.setQuantity(TRANS_QTY);
        transactionCommand.setPrice(TRANS_PRICE);
        transactionCommand.setDate(TRANS_DATE);
        transactionCommand.setIsBuy(TRANS_IS_BUY);
        return transactionCommand;
    }
}