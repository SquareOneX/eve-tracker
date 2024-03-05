package squareonex.evetracker.bootstrap;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import squareonex.evetrackerdata.csv.SdeImport;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.model.ids.BlueprintActionId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@EnableJpaRepositories(basePackages = "squareonex.evetrackerdata.repositories")
public class SdeImportIT {
    SdeImport sdeImport;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BlueprintRepository blueprintRepository;
    @Mock
    BlueprintReader blueprintReaderMock;
    @Mock
    ActivityReader activityReaderMock;
    @Mock
    ItemReader itemReaderMock;

    @BeforeEach
    void setUp() {
        this.sdeImport = new SdeImport(
                activityRepository,
                itemRepository,
                blueprintRepository,
                blueprintReaderMock,
                activityReaderMock,
                itemReaderMock
        );
    }

    @Test
    void name() throws FileNotFoundException {
        //ACTIVITY
        Activity manufacturing = new Activity(1, "Manufacturing", true);
        Activity copying = new Activity(1, "Manufacturing", true);

        Map<Integer, Activity> activityMap = new HashMap<>();
        activityMap.put(manufacturing.getId(), manufacturing);
        activityMap.put(copying.getId(), copying);

        when(activityReaderMock.readAll()).thenReturn(activityMap);

        //ITEM
        Item slasherBlueprint = new Item(585L, "Slasher Blueprint");
        slasherBlueprint.setPublished(true);
        Item slasher = new Item(586L, "Slasher");
        slasher.setPublished(true);

        Map<Long, Item> itemMap = new HashMap<>();
        itemMap.put(slasherBlueprint.getId(), slasherBlueprint);
        itemMap.put(slasher.getId(), slasher);

        when(itemReaderMock.readAll()).thenReturn(itemMap);

        //BLUEPRINT
        Blueprint blueprint = new Blueprint(slasherBlueprint.getId(), slasherBlueprint.getName());
        BlueprintAction action = new BlueprintAction(new BlueprintActionId(blueprint, manufacturing));
        action.getMaterials().add(new BlueprintMaterial(action, slasher, 100));
        blueprint.getActions().add(action);

        Map<Long, Blueprint> blueprintMap = new HashMap<>();
        blueprintMap.put(blueprint.getId(), blueprint);

        when(blueprintReaderMock.readAll(anyMap(), anyMap())).thenReturn(blueprintMap);
        assertDoesNotThrow(() -> sdeImport.run());
    }
}
