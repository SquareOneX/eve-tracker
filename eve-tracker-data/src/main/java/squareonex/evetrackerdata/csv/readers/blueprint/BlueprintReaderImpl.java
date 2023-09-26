package squareonex.evetrackerdata.csv.readers.blueprint;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.persistence.EntityManager;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class BlueprintReaderImpl implements BlueprintReader {
    private final EntityManager entityManager;

    public BlueprintReaderImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Blueprint> readAll() throws FileNotFoundException {
        Map<BlueprintKey, BlueprintDTO> blueprints = new HashMap<>();
        Map<BlueprintKey, List<BlueprintMaterialDTO>> materials = new HashMap<>();
        Map<BlueprintKey, List<BlueprintProductDTO>> products = new HashMap<>();

        URL resource = getClass().getClassLoader().getResource("industryactivity.csv");
        FileReader reader = new FileReader(resource.getPath());
        CsvToBean<BlueprintDTO> csvToBean = new CsvToBeanBuilder<BlueprintDTO>(reader)
                .withType(BlueprintDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withVerifier(new BlueprintDTO.Verifier())
                .build();
        for (BlueprintDTO dto : csvToBean) {
            blueprints.put(new BlueprintKey(dto.getId(), dto.getActivityId()), dto);
        }

        resource = getClass().getClassLoader().getResource("industryactivitymaterials.csv");
        reader = new FileReader(resource.getPath());
        CsvToBean<BlueprintMaterialDTO> materialBean = new CsvToBeanBuilder<BlueprintMaterialDTO>(reader)
                .withType(BlueprintMaterialDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        for (BlueprintMaterialDTO dto : materialBean) {
            BlueprintDTO savedDTO = blueprints.get(dto.getKey());
            if (savedDTO != null) {
                if (!materials.containsKey(dto.getKey()))
                    materials.put(dto.getKey(), new ArrayList<>());
                materials.get(dto.getKey()).add(dto);
            }
        }

        resource = getClass().getClassLoader().getResource("industryactivityproducts.csv");
        reader = new FileReader(resource.getPath());
        CsvToBean<BlueprintProductDTO> productBean = new CsvToBeanBuilder<BlueprintProductDTO>(reader)
                .withType(BlueprintProductDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        for (BlueprintProductDTO dto : productBean) {
            if (blueprints.get(dto.getKey()) != null){
                if (!products.containsKey(dto.getKey()))
                    products.put(dto.getKey(), new ArrayList<>());
                products.get(dto.getKey()).add(dto);
            }
        }

        List<Blueprint> blueprintList = new LinkedList<>();
        for (Map.Entry<BlueprintKey, BlueprintDTO> entry : blueprints.entrySet()) {
            BlueprintDTO dto = entry.getValue();
            Blueprint blueprint = new Blueprint();

            blueprint.setActivity(entityManager.getReference(Activity.class, dto.getActivityId()));
            blueprint.setItemInfo(entityManager.getReference(Item.class, dto.getId()));

            List<BlueprintMaterialDTO> list = materials.get(dto.getKey());
            if (list != null){
                for (BlueprintMaterialDTO matDTO : list) {
                    BlueprintMaterial material = new BlueprintMaterial();
                    material.setMaterial(entityManager.getReference(Item.class, matDTO.getMaterialId()));
                    material.setQuantity(matDTO.getQuantity());
                    blueprint.getMaterials().add(material);
                }
            }

            List<BlueprintProductDTO> productList = products.get(dto.getKey());
            if (productList != null){
                for (BlueprintProductDTO productDTO : productList) {
                    BlueprintProduct product = new BlueprintProduct();
                    product.setProduct(entityManager.getReference(Item.class, productDTO.getProductId()));
                    product.setQuantity(productDTO.getQuantity());
                    blueprint.getProducts().add(product);
                }
            }

            blueprintList.add(blueprint);
        }
        return blueprintList;
    }
}
