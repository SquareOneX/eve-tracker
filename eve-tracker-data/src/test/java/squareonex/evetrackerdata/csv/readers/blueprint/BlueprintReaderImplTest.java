package squareonex.evetrackerdata.csv.readers.blueprint;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.io.FileNotFoundException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class BlueprintReaderImplTest {

    private BlueprintReaderImpl unit;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Item itemReference;
    @Mock
    private Activity activityReference;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        when(entityManager.getReference(eq(Item.class), anyLong())).thenReturn(itemReference);
        when(entityManager.getReference(eq(Activity.class), anyInt())).thenReturn(activityReference);

        this.unit = new BlueprintReaderImpl(entityManager);
    }

    @Test
    void readAll() {
        Assertions.assertDoesNotThrow(() -> unit.readAll());
    }
}