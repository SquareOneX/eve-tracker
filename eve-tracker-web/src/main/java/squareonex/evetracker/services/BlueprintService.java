package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;

import java.util.NoSuchElementException;
import java.util.Set;

public interface BlueprintService {
    Set<Blueprint> getBlueprints();
    Set<BlueprintAction> getBlueprintActions();
    Blueprint findById(Long blueprintId);
    BlueprintAction findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId) throws NoSuchElementException;
}