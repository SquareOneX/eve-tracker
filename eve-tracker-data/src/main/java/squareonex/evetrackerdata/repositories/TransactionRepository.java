package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
