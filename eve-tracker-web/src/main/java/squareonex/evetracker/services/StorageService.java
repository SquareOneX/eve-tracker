package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Item;

import java.util.Optional;

public interface StorageService {

    // Adds a specific quantity of an item to the storage
    // Returns the new total quantity of this item in the storage
    long add(Item item, long quantityToAdd);

    // Removes a specific quantity of an item from the storage
    // Returns the remaining quantity of this item in the storage, or an empty Optional if the item does not exist in the storage
    Optional<Long> remove(Item item, long quantityToRemove);

    // Checks the quantity of an item in the storage
    // Returns the quantity of this item in the storage, or an empty Optional if the item does not exist in the storage
    Optional<Long> check(Item item);
    // Checks if the storage contains the specified quantity of an item
    // Returns true if the storage contains at least the specified quantity of the item, or false otherwise
    boolean isAvailable(Item item, long quantity);
}