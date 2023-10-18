package squareonex.evetracker.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;

import java.util.HashSet;
import java.util.Set;

class ProductionCostServiceImplTest {
    @Mock
    private StorageService storageServiceMock;
    private Blueprint blueprintDummy;
    private Item productDummy;
    private ProductionCostServiceImpl unit;
    @Mock private Item material1;
    @Mock private Item material2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.unit = new ProductionCostServiceImpl(storageServiceMock);
        this.blueprintDummy = new Blueprint();
        blueprintDummy.getItemInfo().setId(0L);
        blueprintDummy.getItemInfo().setName("blueprint");
        blueprintDummy.setActivity(new Activity());
        this.productDummy = new Item(1L, "product");
        this.productDummy.setBlueprints(Set.of(new BlueprintProduct(blueprintDummy, this.productDummy, 1)));

        Mockito.when(material1.getAvgCost()).thenReturn(100.0f);
        Mockito.when(material2.getAvgCost()).thenReturn(10.0f);
//        Set<BlueprintMaterial> materials = Set.of(
//                new BlueprintMaterial(blueprintDummy, material1, 1),
//                new BlueprintMaterial(blueprintDummy, material2, 5)
//        );
        //blueprintDummy.setMaterials(materials);

        Mockito.when(storageServiceMock.getStorageLevel(material1)).thenReturn(1);
        Mockito.when(storageServiceMock.isAvailable(material1, 1)).thenReturn(true);
        Mockito.when(storageServiceMock.getStorageLevel(material2)).thenReturn(5);
        Mockito.when(storageServiceMock.isAvailable(material2, 5)).thenReturn(true);
    }

    @Test
    void getCostShouldReturnCorrectCost() {
        Double cost = unit.getCost(productDummy);

        //TODO fix this test
        //assertEquals(150.0f, cost);
    }

    @Test
    void getCostShouldReturnNullIfMissingData() {
        //arrange
        blueprintDummy.setMaterials(new HashSet<>());

        //act
        Double cost = unit.getCost(productDummy);

        //assert
        Assertions.assertNull(cost);
    }

    @Test
    void getCostShouldReturnNullWhenNoMaterialsAvailable(){
        Mockito.when(storageServiceMock.isAvailable(material1, 1)).thenReturn(false);
        Mockito.when(storageServiceMock.getStorageLevel(material1)).thenReturn(0);

        Double cost = unit.getCost(productDummy);

        Assertions.assertNull(cost);
    }
}