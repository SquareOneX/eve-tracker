package squareonex.evetracker.converters.text;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StringToTransactionTest {
    @Mock
    ItemRepository itemRepositoryMock;
    StringToTransaction stringToTransaction;
    private static final LocalDateTime TRANSACTION_TIME = LocalDateTime.of(LocalDate.of(2022, Month.DECEMBER, 8), LocalTime.of(18, 22));;
    private static final int TRANSACTION_QTY = 500;
    private static final String TRANSACTION_ITEM = "Isogen";
    private static final Float TRANSACTION_PRICE_TOTAL = -249_950.0F;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.stringToTransaction = new StringToTransaction(itemRepositoryMock);
    }

    @Test
    public void convertShouldThrowExceptions(){
        when(itemRepositoryMock.findByNameIgnoreCase(TRANSACTION_ITEM)).thenReturn(Optional.of(new Item(0L, TRANSACTION_ITEM)));
        Transaction convert = stringToTransaction.convert("2022.12.08 18:22\t500\tIsogen\t499 ISK\t-249.950 ISK\tISK Tycoon\tJita IV - Moon 4 - Caldari Navy Assembly Plant");

        assertNotNull(convert);
        assertEquals(TRANSACTION_ITEM, convert.getItem().getName());
        verify(itemRepositoryMock, times(1)).findByNameIgnoreCase(TRANSACTION_ITEM);

        assertEquals(TRANSACTION_QTY, convert.getQuantity());
        assertEquals(TRANSACTION_TIME, convert.getDate());
        assertEquals(TRANSACTION_PRICE_TOTAL, convert.getPrice());
    }

    @Test
    void convertingNullShouldReturnNull() {
        assertNull(stringToTransaction.convert(null));
    }

    @Test
    void convertingEmptyStringShouldReturnEmptyObject() {
        assertEquals(new Transaction(), stringToTransaction.convert(""));
    }
}