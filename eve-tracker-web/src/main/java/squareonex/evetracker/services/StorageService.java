package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Item;

import java.util.Optional;

public interface StorageService {

    /**
     * Add a specific quantity of an item to the storage
     * @param item item to add to storage
     * @param quantityToAdd quantity
     * @return quantity in storage after success
     */
    long add(Item item, long quantityToAdd);

    /**
     * Remove quantity of an item from the storage
     * @param item item to remove from storage
     * @param quantityToRemove quantity
     * @return quantity in storage after success
     */
    Optional<Long> remove(Item item, long quantityToRemove);

    /**
     * Check the quantity of an item in the storage
     * @param item item to check for
     * @return quantity of this item in the storage
     */
    Optional<Long> check(Item item);

    /**
     * Check if the storage contains the specified quantity of an item
     * @param item item
     * @param quantity quantity
     * @return true if the storage contains at least the specified quantity of the item, or false otherwise
     */
    boolean isAvailable(Item item, long quantity);
}