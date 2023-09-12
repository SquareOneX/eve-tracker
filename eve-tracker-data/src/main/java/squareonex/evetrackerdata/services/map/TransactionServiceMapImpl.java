package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.services.TransactionService;

import java.util.Set;

public class TransactionServiceMapImpl extends AbstractMapService<Transaction, Long> implements TransactionService {
    private Long key = 0L;

    @Override
    public Set<Transaction> findAll() {
        return super.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Transaction save(Transaction object) {
        if (object.getId() == null){
            while (map.containsKey(key))
                key++;
            object.setId(key);
            return super.save(key, object);
        }else {
            return super.save(object.getId(), object);
        }
    }

    @Override
    public void delete(Transaction object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
