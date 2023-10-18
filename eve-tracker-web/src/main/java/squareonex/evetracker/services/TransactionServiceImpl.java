package squareonex.evetracker.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import squareonex.evetracker.commands.TransactionCommand;
import squareonex.evetracker.converters.TransactionCommandToTransaction;
import squareonex.evetracker.converters.TransactionToTransactionCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.TransactionRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionToTransactionCommand transactionToTransactionCommand;
    private final TransactionCommandToTransaction transactionCommandToTransaction;
    protected TransactionServiceImpl(
            TransactionRepository transactionRepository,
            TransactionToTransactionCommand transactionToTransactionCommand,
            TransactionCommandToTransaction transactionCommandToTransaction
    ) {
        this.transactionRepository = transactionRepository;
        this.transactionToTransactionCommand = transactionToTransactionCommand;
        this.transactionCommandToTransaction = transactionCommandToTransaction;
    }

    @Override
    public Set<Transaction> findAll() {
        Set<Transaction> set = new HashSet<>();
        transactionRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    @Transactional
    public TransactionCommand saveOrUpdateCommand(TransactionCommand transactionCommand) {
        Transaction transaction = transactionCommandToTransaction.convert(transactionCommand);
        transaction = saveOrUpdate(transaction);
        TransactionCommand converted = transactionToTransactionCommand.convert(transaction);
        return converted;
    }

    @Override
    @Transactional
    public Transaction saveOrUpdate(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
