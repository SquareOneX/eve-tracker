package squareonex.evetracker.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import squareonex.evetracker.config.EveTrackerConfig;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.ItemRepository;
import squareonex.evetrackerdata.repositories.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@ExtendWith(SpringExtension.class )
@DataJpaTest
@Import(EveTrackerConfig.class)
// Exclude the default test database + the default EntityManager in purpose to use my configurations instead.
@AutoConfigureTestDatabase( connection = H2, replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED )
class TransactionRepositoryIT {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    void persistingTransactionForItemWithoutPriorTransactionsShouldCalculateAvgCost() {
        long itemId = 689L;
        Item item = itemRepository.findById(itemId).orElseThrow();

        Float avgCostBefore = item.getAvgCost();

        Transaction transaction = new Transaction();
        transaction.setItem(item);
        transaction.setQuantity(10);
        transaction.setPrice(1_000_000F);
        transaction.setDate(LocalDateTime.now());
        transaction.setIsBuy(true);
        transaction = transactionRepository.save(transaction);

        Set<Transaction> transactions = itemRepository.findById(itemId).orElseThrow().getTransactions();
        assertEquals(1, transactions.size());
        assertNotEquals(avgCostBefore, item.getAvgCost());
    }

    @Test
    void persistingTransactionShouldUpdateItemAvgCost() {
        long itemId = 34L;
        Item item = itemRepository.findById(itemId).orElseThrow();

        assertNotNull(item.getAvgCost());

        Transaction transaction = new Transaction();
        transaction.setItem(item);
        transaction.setQuantity(10);
        transaction.setPrice(1_000_000F);
        transaction.setDate(LocalDateTime.now());
        transaction.setIsBuy(true);
        transaction = transactionRepository.save(transaction);

        Set<Transaction> transactions = itemRepository.findById(itemId).orElseThrow().getTransactions();
        assertEquals(2, transactions.size());
        assertNotEquals(null, item.getAvgCost());
    }
}