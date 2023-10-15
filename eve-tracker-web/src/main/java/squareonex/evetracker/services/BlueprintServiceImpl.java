package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

@Service
public class BlueprintServiceImpl extends AbstractCrudService<Blueprint, BlueprintId> implements BlueprintService {
    public BlueprintServiceImpl(BlueprintRepository blueprintRepository) {
        super(blueprintRepository);
    }
}
