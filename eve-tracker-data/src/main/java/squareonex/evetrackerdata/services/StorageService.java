package squareonex.evetrackerdata.services;

import squareonex.evetrackerdata.model.Item;

public interface StorageService{
    void add(Item item, int amount);
    void remove(Item item, int amount);
    boolean isAvailable(Item item, Integer amount);
    Integer getStorageLevel(Item item);
}
