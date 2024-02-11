package squareonex.evetracker.services;

import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Item;

import java.util.NoSuchElementException;
import java.util.Set;

public interface BlueprintService extends PageableService<Blueprint>{
    Set<Blueprint> getBlueprints();
    Set<BlueprintAction> getBlueprintActions();
    Blueprint findById(Long blueprintId);
    BlueprintAction findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId) throws NoSuchElementException;
    Set<BlueprintCopy> findBlueprintCopiesByItem(Item product);
    Set<BlueprintCopyCommand> findBlueprintCopyCommandsByItemCommand(ItemCommand itemCommand);
}