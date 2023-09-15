package squareonex.evetrackerdata.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Product;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductionCostServiceImplTest {
    @Mock
    private StorageService storageServiceMock;
    private Blueprint blueprintDummy;
    private Product productDummy;
    private ProductionCostServiceImpl unit;
    private Product material1;
    private Product material2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.unit = new ProductionCostServiceImpl(storageServiceMock);
        this.blueprintDummy = new Blueprint(0L, "blueprint", new Activity(), 10, 10);
        this.productDummy = new Product(1L, "product", blueprintDummy);

        material1 = mock(Product.class);
        when(material1.getAvgCost()).thenReturn(100.0f);
        material2 = mock(Product.class);
        when(material2.getAvgCost()).thenReturn(10.0f);
        Map<Product, Integer> materials = new HashMap<>();
        materials.put(material1, 1);
        materials.put(material2, 5);
        blueprintDummy.setMaterials(materials);

        when(storageServiceMock.getStorageLevel(material1)).thenReturn(1);
        when(storageServiceMock.isAvailable(material1, 1)).thenReturn(true);
        when(storageServiceMock.getStorageLevel(material2)).thenReturn(5);
        when(storageServiceMock.isAvailable(material2, 5)).thenReturn(true);
        System.out.println(productDummy);
        System.out.println(blueprintDummy);
    }

    @Test
    void getCostShouldReturnCorrectCost() {
        Double cost = unit.getCost(productDummy);

        assertEquals(150.0f, cost);
    }

    @Test
    void getCostShouldReturnNullIfMissingData() {
        //arrange
        blueprintDummy.setMaterials(new HashMap<>());

        //act
        Double cost = unit.getCost(productDummy);

        //assert
        assertNull(cost);
    }

    @Test
    void getCostShouldReturnNullWhenNoMaterialsAvailable(){
        when(storageServiceMock.isAvailable(material1, 1)).thenReturn(false);
        when(storageServiceMock.getStorageLevel(material1)).thenReturn(0);

        Double cost = unit.getCost(productDummy);

        assertNull(cost);
    }
}