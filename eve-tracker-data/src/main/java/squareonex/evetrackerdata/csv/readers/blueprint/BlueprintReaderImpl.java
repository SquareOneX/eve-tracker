package squareonex.evetrackerdata.csv.readers.blueprint;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.model.ids.BlueprintActionId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.util.*;

public class BlueprintReaderImpl implements BlueprintReader {
    @Override
    public Map<Long, Blueprint> readAll(Map<Long, Item> items, Map<Integer, Activity> activities) throws FileNotFoundException {
        Map<BlueprintKey, BlueprintDTO> blueprints = readBlueprintsToMap();
        Map<BlueprintKey, List<BlueprintMaterialDTO>> materials = readMaterialsToMap();
        Map<BlueprintKey, Set<BlueprintProductDTO>> products = readProductsToMap();

        Map<Long, Blueprint> result = new HashMap<>();

        for (Map.Entry<BlueprintKey, BlueprintDTO> bpEntry : blueprints.entrySet()) {
            BlueprintKey key = bpEntry.getKey();
            BlueprintDTO value = bpEntry.getValue();

            Blueprint blueprint;
            if (result.containsKey(key.id)){
                blueprint = result.get(key.id);
            } else {
                Item itemInfo = items.get(key.id);
                if (itemInfo == null) continue; // Invalid Blueprint

                blueprint = new Blueprint(key.id, itemInfo.getName());
                blueprint.setPublished(itemInfo.getPublished());
            }

            Activity activity = activities.get(key.activityId);
            BlueprintAction action = new BlueprintAction(new BlueprintActionId(blueprint, activity));
            action.setDuration(Duration.ofSeconds(value.getTime()));

            boolean valid = true;

            if (materials.containsKey(key)) {
                for (BlueprintMaterialDTO dto : materials.get(key)) {
                    Item material = items.get(dto.getMaterialId());
                    if (material == null){
                        valid = false;
                        break;
                    } else {
                        action.getMaterials().add(new BlueprintMaterial(action, material, dto.getQuantity()));
                    }
                }
            }

            if (!valid)
                continue;

            if (products.containsKey(key)) {
                for (BlueprintProductDTO dto : products.get(key)) {
                    Item product = items.get(dto.getProductId());
                    if (product == null) {
                        valid = false;
                        break;
                    }

                    action.getProducts().add(new BlueprintProduct(action, product, dto.getQuantity(), dto.getProbability()));
                }
            }

            if (!valid)
                continue;

            blueprint.getActions().add(action);
            result.put(key.id, blueprint);
        }

        return result;
    }

    private Map<BlueprintKey, BlueprintDTO> readBlueprintsToMap() throws FileNotFoundException {
        FileReader reader = new FileReader(getClass().getClassLoader().getResource("industryactivity.csv").getPath());
        CsvToBean<BlueprintDTO> blueprintBeans = new CsvToBeanBuilder<BlueprintDTO>(reader).withType(BlueprintDTO.class).withIgnoreLeadingWhiteSpace(true).withVerifier(new BlueprintDTO.Verifier()).build();

        Map<BlueprintKey, BlueprintDTO> blueprints = new HashMap<>();
        for (BlueprintDTO dto : blueprintBeans) {
            blueprints.put(dto.getKey(), dto);
        }
        return blueprints;
    }

    private Map<BlueprintKey, List<BlueprintMaterialDTO>> readMaterialsToMap() throws FileNotFoundException {
        FileReader reader = new FileReader(getClass().getClassLoader().getResource("industryactivitymaterials.csv").getPath());
        CsvToBean<BlueprintMaterialDTO> materialBean = new CsvToBeanBuilder<BlueprintMaterialDTO>(reader).withType(BlueprintMaterialDTO.class).withIgnoreLeadingWhiteSpace(true).build();

        Map<BlueprintKey, List<BlueprintMaterialDTO>> materials = new HashMap<>();
        for (BlueprintMaterialDTO dto : materialBean) {
            if (!materials.containsKey(dto.getKey())) materials.put(dto.getKey(), new LinkedList<>());
            materials.get(dto.getKey()).add(dto);
        }
        return materials;
    }

    private Map<BlueprintKey, Set<BlueprintProductDTO>> readProductsToMap() throws FileNotFoundException {
        FileReader reader = new FileReader(getClass().getClassLoader().getResource("industryactivityproducts.csv").getPath());
        CsvToBean<BlueprintProductDTO> productBean = new CsvToBeanBuilder<BlueprintProductDTO>(reader).withType(BlueprintProductDTO.class).withIgnoreLeadingWhiteSpace(true).build();

        Map<BlueprintKey, Set<BlueprintProductDTO>> products = new HashMap<>();
        for (BlueprintProductDTO dto : productBean) {
            if (!products.containsKey(dto.getKey())) products.put(dto.getKey(), new HashSet<>());
            products.get(dto.getKey()).add(dto);
        }

        reader = new FileReader(getClass().getClassLoader().getResource("industryactivityprobabilities.csv").getPath());
        productBean = new CsvToBeanBuilder<BlueprintProductDTO>(reader).withType(BlueprintProductDTO.class).withIgnoreLeadingWhiteSpace(true).build();
        for (BlueprintProductDTO dto : productBean) {
            if (!products.containsKey(dto.getKey())) products.put(dto.getKey(), new HashSet<>());
            products.get(dto.getKey()).add(dto);
        }
        return products;
    }
}
