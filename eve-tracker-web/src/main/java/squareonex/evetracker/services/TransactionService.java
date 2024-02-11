package squareonex.evetracker.services;

import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;

import java.util.Set;

public interface TransactionService extends PageableService<Transaction>{
    Set<Transaction> findAll();
    TransactionCommand saveOrUpdateCommand(TransactionCommand transaction);
    Transaction saveOrUpdate(Transaction transaction);
}
