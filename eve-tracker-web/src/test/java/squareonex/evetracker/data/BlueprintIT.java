package squareonex.evetracker.data;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetracker.config.EveTrackerConfig;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@ExtendWith(SpringExtension.class )
@DataJpaTest
@Import(EveTrackerConfig.class)
// Exclude the default test database and the default EntityManager, make it use my configuration instead
@AutoConfigureTestDatabase( connection = H2, replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED )
@Transactional
public class BlueprintIT {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    BlueprintRepository blueprintRepository;
    @Autowired
    EntityManager entityManager;
    @Test
    void name() {
        long itemId = 123456789L;
        Blueprint save = blueprintRepository.save(new Blueprint(itemId, ""));

        Item item = itemRepository.findById(itemId).get();

        Blueprint blueprint = (Blueprint) item;
        blueprint.getCopies().add(new BlueprintCopy(blueprint, 10, 10_000f));
        blueprint = blueprintRepository.save(blueprint);

        item.setName("Dummy Item");
        itemRepository.save(item);
        System.out.println(item);
    }
}
