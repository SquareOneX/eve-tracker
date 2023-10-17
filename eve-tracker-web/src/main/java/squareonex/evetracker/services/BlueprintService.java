package squareonex.evetracker.services;

import squareonex.evetracker.commands.BlueprintCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.ids.BlueprintId;

import java.util.Set;

public interface BlueprintService {
    Set<Blueprint> getBlueprints();
    Blueprint findById(BlueprintId blueprintId);
    Blueprint findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId);
}