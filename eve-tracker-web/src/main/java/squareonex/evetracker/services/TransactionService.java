package squareonex.evetracker.services;

import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

import java.util.Set;

/**
 * Search and modify transaction objects
 */
public interface TransactionService extends PageableService<Transaction>{
    /**
     * Return a set of all transactions
     * @return transactions
     */
    Set<Transaction> findAll();

    /**
     * Update an existing transaction command object or create a new one
     * @param transaction transaction
     * @return transaction command as it is in the database
     */
    TransactionCommand saveOrUpdateCommand(TransactionCommand transaction);

    /**
     * Update an existing transaction object or create a new one
     * @param transaction transaction
     * @return the instance as it is in the database
     */
    Transaction saveOrUpdate(Transaction transaction);
}
