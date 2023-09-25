package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.TransactionRepository;
import squareonex.evetrackerdata.services.TransactionService;

import java.util.HashSet;
import java.util.Set;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Set<Transaction> findAll() {
        Set<Transaction> set = new HashSet<>();
        for (Transaction transaction : transactionRepository.findAll()) {
            set.add(transaction);
        }
        return set;
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction save(Transaction object) {
        return transactionRepository.save(object);
    }

    @Override
    public void delete(Transaction object) {
        transactionRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}
