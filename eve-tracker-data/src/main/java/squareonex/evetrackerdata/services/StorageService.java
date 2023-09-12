package squareonex.evetrackerdata.services;

import squareonex.evetrackerdata.model.BaseItem;

public interface StorageService{
    void add(BaseItem item, int amount);
    void remove(BaseItem item, int amount);
    boolean isAvailable(BaseItem item, Integer amount);
    Integer getStorageLevel(BaseItem item);
}
