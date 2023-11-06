package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Item;

import java.util.HashMap;
import java.util.Map;

public class StorageServiceMapImpl implements StorageService {
    private Map<Item, Long> map = new HashMap<>();

    @Override
    public void add(Item item, long amount) {
        if (map.containsKey(item)){
            amount = map.get(item) + amount;
        }
        map.put(item, amount);
    }

    @Override
    public void remove(Item item, long amount) {
        if (map.containsKey(item)) {
            Long inStorage = map.get(item);
            map.put(item, inStorage - amount);
        }
    }

    @Override
    public boolean isAvailable(Item item, Long amount) {
        return map.containsKey(item);
    }

    @Override
    public Long getStorageLevel(Item item) {
        return map.get(item);
    }
}
