package squareonex.evetrackerdata.services;

import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Product;

import java.util.Map;

public class ProductionCostServiceImpl implements ProductionCostService {
    private final StorageService storageService;
    public ProductionCostServiceImpl(StorageService storageService) {
        this.storageService = storageService;
    }
    @Override
    public Double getCost(Product product) {
        Blueprint blueprint = product.getBlueprint();
        if (blueprint == null || blueprint.getMaterials() == null || blueprint.getMaterials().isEmpty())
            return null;
        else{
            double cost = 0;
            for (Map.Entry<Product, Integer> materialEntry : blueprint.getMaterials().entrySet()) {
                if(!storageService.isAvailable(materialEntry.getKey(), materialEntry.getValue()))
                    return null;
                cost += materialEntry.getValue() * materialEntry.getKey().getAvgCost();
            }
            return cost;
        }
    }
}
