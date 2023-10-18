package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ItemServiceImplTest {
    @InjectMocks
    ItemServiceImpl itemService;
    @Mock
    ItemRepository itemRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        itemService.findAll();

        verify(itemRepositoryMock, times(1)).findAll();
    }
}