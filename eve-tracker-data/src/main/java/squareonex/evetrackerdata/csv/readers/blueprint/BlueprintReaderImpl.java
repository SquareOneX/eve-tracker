package squareonex.evetrackerdata.csv.readers.blueprint;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BlueprintReaderImpl implements BlueprintReader {
    private final ActivityRepository activityRepository;
    private final ItemRepository itemRepository;

    public BlueprintReaderImpl(ActivityRepository activityRepository, ItemRepository itemRepository) {
        this.activityRepository = activityRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Blueprint> readAll() throws FileNotFoundException {
        Map<BlueprintKey, Blueprint> blueprints = readBlueprintsToMap(
                new FileReader(getClass().getClassLoader().getResource("industryactivity.csv").getPath()));

        Map<BlueprintKey, List<BlueprintMaterialDTO>> materials = readMaterialsToMap(
                new FileReader(getClass().getClassLoader().getResource("industryactivitymaterials.csv").getPath()));

        Map<BlueprintKey, List<BlueprintProductDTO>> products = readProductsToMap(
                new FileReader(getClass().getClassLoader().getResource("industryactivityproducts.csv").getPath()));

        Map<Integer, Activity> activities= new HashMap<>();
        activityRepository.findAll().forEach((a) -> activities.put(a.getId(), a));

        Map<Long, Item> items = new HashMap<>();
        itemRepository.findAll().forEach((i) -> items.put(i.getId(), i));

        Map<BlueprintKey, Blueprint> result = new HashMap<>();

        for (Map.Entry<BlueprintKey, Blueprint> entry : blueprints.entrySet()) {
            BlueprintKey blueprintKey = entry.getKey();
            Blueprint blueprint = entry.getValue();

            Activity activity = activities.get(blueprintKey.activityId);
            Item item = items.get(blueprintKey.id);

            if (activity == null || item == null)
                continue;

            blueprint.setActivity(activity);
            blueprint.setItemInfo(item);

            List<BlueprintMaterialDTO> mats = materials.get(blueprintKey);
            boolean valid = true;
            if(mats != null) {
                for (BlueprintMaterialDTO dto : mats) {
                    Item material = items.get(dto.getMaterialId());
                    if (material == null) {
                        valid = false;
                        blueprint.getMaterials().clear();
                        break;
                    } else {
                        blueprint.getMaterials().add(new BlueprintMaterial(blueprint, material, dto.getQuantity()));
                    }
                }
            }

            List<BlueprintProductDTO> prods = products.get(blueprintKey);
            if(prods != null) {
                for (BlueprintProductDTO dto : prods) {
                    Item product = items.get(dto.getProductId());
                    if (product == null) {
                        valid = false;
                        blueprint.getProducts().clear();
                        break;
                    } else {
                        blueprint.getProducts().add(new BlueprintProduct(blueprint, product, dto.getQuantity()));
                    }
                }
            }

            if(valid)
                result.put(blueprintKey, blueprint);
        }
        return new LinkedList<>(result.values());
    }

    private Map<BlueprintKey, Blueprint> readBlueprintsToMap(FileReader reader) {
        CsvToBean<BlueprintDTO> csvToBean = new CsvToBeanBuilder<BlueprintDTO>(reader)
                .withType(BlueprintDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withVerifier(new BlueprintDTO.Verifier())
                .build();

        Map<BlueprintKey, Blueprint> blueprints = new HashMap<>();
        for (BlueprintDTO dto : csvToBean) {
            Blueprint blueprint = new Blueprint();

            blueprints.put(dto.getKey(), blueprint);
        }
        return blueprints;
    }

    private Map<BlueprintKey, List<BlueprintMaterialDTO>> readMaterialsToMap(FileReader reader) {
        CsvToBean<BlueprintMaterialDTO> materialBean = new CsvToBeanBuilder<BlueprintMaterialDTO>(reader)
                .withType(BlueprintMaterialDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Map<BlueprintKey, List<BlueprintMaterialDTO>> materials = new HashMap<>();
        for (BlueprintMaterialDTO dto : materialBean) {
            if (!materials.containsKey(dto.getKey()))
                materials.put(dto.getKey(), new LinkedList<>());
            materials.get(dto.getKey()).add(dto);
        }
        return materials;
    }

    private Map<BlueprintKey, List<BlueprintProductDTO>> readProductsToMap(FileReader reader) {
        CsvToBean<BlueprintProductDTO> productBean = new CsvToBeanBuilder<BlueprintProductDTO>(reader)
                .withType(BlueprintProductDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        Map<BlueprintKey, List<BlueprintProductDTO>> products = new HashMap<>();
        for (BlueprintProductDTO dto : productBean) {
            if (!products.containsKey(dto.getKey()))
                products.put(dto.getKey(), new LinkedList<>());
            products.get(dto.getKey()).add(dto);
        }
        return products;
    }
}
