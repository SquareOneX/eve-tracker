package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Item;

import java.util.Set;

public interface ItemService {
    Set<Item> findAll();
}
