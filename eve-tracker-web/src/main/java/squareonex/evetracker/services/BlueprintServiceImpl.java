package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetracker.commands.BlueprintCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

import java.util.Set;

@Service
public class BlueprintServiceImpl extends AbstractCrudService<Blueprint, BlueprintId> implements BlueprintService {
    public BlueprintServiceImpl(BlueprintRepository blueprintRepository) {
        super(blueprintRepository);
    }

    @Override
    public Set<Blueprint> getBlueprints() {
        return super.findAll();
    }
}
