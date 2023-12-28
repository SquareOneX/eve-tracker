package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Storage;
import squareonex.evetrackerdata.repositories.StorageRepository;

import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @Transactional
    @Override
    public long add(Item item, long quantityToAdd) {
        Optional<Storage> optional = storageRepository.findByItem(item);
        if (optional.isEmpty()) {
            return storageRepository.save(new Storage(item, quantityToAdd)).getQuantity();
        } else {
            optional.get().setQuantity(optional.get().getQuantity() + quantityToAdd);
            return optional.get().getQuantity();
        }
    }

    @Override
    public Optional<Long> remove(Item item, long quantityToRemove) {
        Optional<Storage> optional = storageRepository.findByItem(item);
        if (optional.isEmpty()) {
            return Optional.empty();
        } else {
            Storage storage = optional.get();
            if (storage.getQuantity() < quantityToRemove) {
                return Optional.empty();
            }else {
                Long newQuantity = storage.getQuantity() - quantityToRemove;
                storage.setQuantity(newQuantity);
                storage = storageRepository.save(storage);

                return Optional.of(storage.getQuantity());
            }
        }
    }

    @Override
    public Optional<Long> check(Item item) {
        Optional<Storage> storageEntry = storageRepository.findByItem(item);
        if (storageEntry.isEmpty())
            return Optional.empty();
        else
            return Optional.of(storageEntry.get().getQuantity());
    }

    @Override
    public boolean isAvailable(Item item, long quantity) {
        Optional<Long> quantityInStorage = check(item);
        if (quantityInStorage.isEmpty()){
            return false;
        } else {
            return quantity <= quantityInStorage.get();
        }
    }
}
