package squareonex.evetrackerdata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {
    private Product unit;
    @Mock
    private Blueprint blueprint;
    @Mock
    private Product item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.unit = new Product();
        this.unit.setBlueprint(blueprint);
        this.unit.setId(0L);
        this.unit.setName("product0");
    }

    @Test
    void getAvgCost() {
        Transaction transaction1 = new Transaction(
                LocalDateTime.of(2023, Month.SEPTEMBER, 14, 22, 32),
                true,
                10,
                item,
                100.0f);
        transaction1.setId(0L);
        Transaction transaction2 = new Transaction(
                LocalDateTime.of(2023, Month.SEPTEMBER, 14, 22, 32),
                true,
                10,
                item,
                50.0f);
        transaction2.setId(1L);
        Transaction transaction3 = new Transaction(
                LocalDateTime.of(2023, Month.SEPTEMBER, 14, 22, 32),
                false,
                10,
                item,
                50000.0f);
        transaction3.setId(2L);
        this.unit.setTransactions(
                Set.of(transaction1, transaction2, transaction3)
        );

        assertEquals(7.5f, unit.getAvgCost());
    }
}