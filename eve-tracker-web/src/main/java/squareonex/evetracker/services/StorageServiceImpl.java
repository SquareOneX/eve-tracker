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

    /**
     * Adds a specific quantity of an item to the storage.
     *
     * @param item The item to add.
     * @param quantityToAdd The quantity to add.
     * @return The new total quantity of the item in the storage.
     */
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

    /**
     * Removes a specific quantity of an item from the storage.
     *
     * @param item The item to remove.
     * @param quantityToRemove The quantity to remove.
     * @return The remaining quantity of this item in the storage, or an empty Optional if the item does not exist in the storage or the quantity to remove exceeds the current quantity
     * in storage.
     */
    @Override
    @Transactional
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

    /**
     * Checks the quantity of an item in the storage.
     *
     * @param item The item to check.
     * @return The quantity of this item in the storage, or an empty Optional if the item does not exist in the storage.
     */
    @Override
    public Optional<Long> check(Item item) {
        Optional<Storage> storageEntry = storageRepository.findByItem(item);
        if (storageEntry.isEmpty())
            return Optional.empty();
        else
            return Optional.of(storageEntry.get().getQuantity());
    }

    /**
     * Checks if the storage contains the specified quantity of an item.
     *
     * @param item The item to check.
     * @param quantity The quantity to check.
     * @return True if the storage contains at least the specified quantity of the item, false otherwise.
     */
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
