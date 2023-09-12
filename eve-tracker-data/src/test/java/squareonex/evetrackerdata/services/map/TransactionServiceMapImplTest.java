package squareonex.evetrackerdata.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetrackerdata.model.Transaction;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceMapImplTest {

    private Transaction dummyTransaction;
    private TransactionServiceMapImpl unit;

    @BeforeEach
    void setUp() {
        this.dummyTransaction = new squareonex.evetrackerdata.model.Transaction();
        this.unit = new TransactionServiceMapImpl();
    }

    @Test
    void findAll() {
        Long id = dummyTransaction.getId();
        unit.map.put(dummyTransaction.getId(), dummyTransaction);

        assertTrue(unit.map.containsValue(dummyTransaction));
        assertTrue(unit.map.containsKey(id));
        assertEquals(id, dummyTransaction.getId());
    }

    @Test
    void deleteById() {
        unit.map.put(dummyTransaction.getId(), dummyTransaction);

        unit.deleteById(dummyTransaction.getId());
        assertTrue(unit.map.isEmpty());
    }

    @Test
    void delete() {
        unit.map.put(dummyTransaction.getId(), dummyTransaction);

        unit.delete(dummyTransaction);
        assertTrue(unit.map.isEmpty());
    }

    @Test
    void saveWithoutAnIdShouldAssignAnId() {
        dummyTransaction.setId(null);

        assertTrue(unit.map.isEmpty());
        unit.save(dummyTransaction);
        assertNotNull(dummyTransaction);
        assertFalse(unit.map.isEmpty());
    }

    @Test
    void saveWithoutAnIdShouldNotOverride(){
        dummyTransaction.setId(null);

        assertTrue(unit.map.isEmpty());
        unit.map.put(0L, new Transaction());

        Transaction save = unit.save(dummyTransaction);

        assertNotNull(save.getId());
        assertNotEquals(0L, save.getId());
        assertEquals(2, unit.map.size());
    }

    @Test
    void saveWithAnIdShouldOverride(){
        Transaction item = new Transaction();
        item.setQuantity(100);
        unit.map.put(0L, item);

        unit.save(dummyTransaction);
        assertEquals(100, unit.map.get(0L).getQuantity());
    }

    @Test
    void findById() {
        unit.map.put(dummyTransaction.getId(), dummyTransaction);

        Transaction transaction = unit.findById(dummyTransaction.getId());
        assertEquals(dummyTransaction, transaction);
    }
}