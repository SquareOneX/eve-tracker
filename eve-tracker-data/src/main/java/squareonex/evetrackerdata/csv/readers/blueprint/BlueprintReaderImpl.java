package squareonex.evetrackerdata.csv.readers.blueprint;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class BlueprintReaderImpl implements BlueprintReader {
    private final ActivityRepository activityRepository;
    private final ItemRepository itemRepository;

    public BlueprintReaderImpl(ActivityRepository activityRepository, ItemRepository itemRepository) {
        this.activityRepository = activityRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Blueprint> readAll() throws FileNotFoundException {
        Map<BlueprintKey, BlueprintDTO> blueprints = readBlueprintsToMap();
        Map<BlueprintKey, List<BlueprintMaterialDTO>> materials = readMaterialsToMap();
        Map<BlueprintKey, Set<BlueprintProductDTO>> products = readProductsToMap();

        Map<Integer, Activity> activities = new HashMap<>();
        activityRepository.findAll().forEach((a) -> activities.put(a.getId(), a));

        Map<Long, Item> items = new HashMap<>();
        itemRepository.findAll().forEach((i) -> items.put(i.getId(), i));

        Map<BlueprintKey, Blueprint> result = new HashMap<>();

        for (Map.Entry<BlueprintKey, BlueprintDTO> entry : blueprints.entrySet()) {
            BlueprintKey blueprintKey = entry.getKey();
            Blueprint blueprint = new Blueprint();

            /*
             * Blueprints
             */
            Item item = items.get(blueprintKey.id);
            if (item == null)
                continue;

            blueprint.setId(item.getId());
            blueprint.setName(item.getName());
            blueprint.setPublished(item.getPublished());

            /*
             * BlueprintActions
             */

            BlueprintAction blueprintAction = new BlueprintAction();
            blueprintAction.setBlueprint(blueprint);

            Activity activity = activities.get(blueprintKey.activityId);
            if (activity == null)
                continue;

            blueprintAction.setActivity(activity);

            List<BlueprintMaterialDTO> mats = materials.get(blueprintKey);
            boolean valid = true;
            if (mats != null) {
                for (BlueprintMaterialDTO dto : mats) {
                    Item material = items.get(dto.getMaterialId());
                    if (material == null) {
                        valid = false;
                        blueprintAction.getMaterials().clear();
                        break;
                    } else {
                        blueprintAction.getMaterials().add(new BlueprintMaterial(blueprintAction, material, dto.getQuantity()));
                    }
                }
            }

            Set<BlueprintProductDTO> prods = products.get(blueprintKey);
            if (prods != null) {
                for (BlueprintProductDTO dto : prods) {
                    Item product = items.get(dto.getProductId());
                    if (product == null) {
                        valid = false;
                        blueprintAction.getProducts().clear();
                        break;
                    } else {
                        BlueprintProduct blueprintProduct = new BlueprintProduct();
                        blueprintProduct.setBlueprintAction(blueprintAction);
                        blueprintProduct.setProduct(product);
                        blueprintProduct.setQuantity(dto.getQuantity());
                        blueprintProduct.setProbability(dto.getProbability());
                        blueprintAction.getProducts().add(blueprintProduct);
                    }
                }
            }

            if (valid) result.put(blueprintKey, blueprint);
        }
        return new LinkedList<>(result.values());
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
