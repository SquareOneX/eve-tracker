package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Item;

public interface ProductionCostService {
    Double getCost(Item product);
}
