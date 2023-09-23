package squareonex.evetrackerdata.services;

import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintMaterial;
import squareonex.evetrackerdata.model.Item;

public class ProductionCostServiceImpl implements ProductionCostService {
    private final StorageService storageService;
    public ProductionCostServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }
    @Override
    public Double getCost(Item product) {
        Blueprint blueprint = product.getBlueprint();
        if (blueprint == null || blueprint.getMaterials() == null || blueprint.getMaterials().isEmpty())
            return null;
        else{
            double cost = 0;
            for (BlueprintMaterial material : product.getBlueprint().getMaterials()) {
                if(!storageService.isAvailable(material.getMaterial(), material.getQuantity()))
                    return null;
                cost += material.getQuantity() * material.getMaterial().getAvgCost();
            }
            return cost;
        }
    }
}
