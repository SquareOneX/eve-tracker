package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Transaction;

import java.util.Set;

public interface TransactionService {
    Set<Transaction> findAll();
}
