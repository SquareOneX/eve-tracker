package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Item;

public interface StorageService{
    void add(Item item, long amount);
    void remove(Item item, long amount);
    boolean isAvailable(Item item, Long amount);
    Long getStorageLevel(Item item);
}
