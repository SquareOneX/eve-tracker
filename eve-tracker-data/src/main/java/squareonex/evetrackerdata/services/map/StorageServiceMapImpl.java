package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.services.StorageService;

import java.util.HashMap;
import java.util.Map;

public class StorageServiceMapImpl implements StorageService {
    private Map<Item, Integer> map = new HashMap<>();

    @Override
    public void add(Item item, int amount) {
        if (map.containsKey(item)){
            amount = map.get(item) + amount;
        }
        map.put(item, amount);
    }

    @Override
    public void remove(Item item, int amount) {
        if (map.containsKey(item)) {
            Integer inStorage = map.get(item);
            map.put(item, inStorage - amount);
        }
    }

    @Override
    public boolean isAvailable(Item item, Integer amount) {
        return map.containsKey(item);
    }

    @Override
    public Integer getStorageLevel(Item item) {
        return map.get(item);
    }
}
