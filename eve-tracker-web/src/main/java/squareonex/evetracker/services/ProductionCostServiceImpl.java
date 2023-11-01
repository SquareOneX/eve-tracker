package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Item;

@Service
public class ProductionCostServiceImpl implements ProductionCostService {
    private final StorageService storageService;
    public ProductionCostServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }
    @Override
    public Double getCost(Item product) {
/*
        Blueprint blueprint = product.get();
        if (blueprint == null || blueprint.getMaterials() == null || blueprint.getMaterials().isEmpty())
            return null;
        else{
            double cost = 0;
            for (BlueprintMaterial material : product.getBlueprintAction().getMaterials()) {
                if(!storageService.isAvailable(material.getMaterial(), material.getQuantity()))
                    return null;
                cost += material.getQuantity() * material.getMaterial().getAvgCost();
            }
            return cost;
        }
*/
        return null;
    }
}
