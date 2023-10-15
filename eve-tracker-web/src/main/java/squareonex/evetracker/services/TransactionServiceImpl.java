package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl extends AbstractCrudService<Transaction, Long> implements TransactionService {
    protected TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
