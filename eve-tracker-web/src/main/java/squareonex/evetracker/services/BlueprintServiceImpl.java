package squareonex.evetracker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.converters.BlueprintCopyToBlueprintCopyCommand;
import squareonex.evetracker.converters.ItemCommandToItem;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class implements the BlueprintService interface and provides the implementation of various methods to retrieve blueprint-related data.
 */
@Service
public class BlueprintServiceImpl implements BlueprintService {
    private final BlueprintRepository blueprintRepository;
    private final ItemRepository itemRepository;
    private final ItemCommandToItem itemCommandToItem;
    private final BlueprintCopyToBlueprintCopyCommand bpcToBpcCommand;

    public BlueprintServiceImpl(BlueprintRepository blueprintRepository, ItemRepository itemRepository, ItemCommandToItem itemCommandToItem, BlueprintCopyToBlueprintCopyCommand blueprintCopyToBlueprintCopyCommand) {
        this.blueprintRepository = blueprintRepository;
        this.itemRepository = itemRepository;
        this.itemCommandToItem = itemCommandToItem;
        this.bpcToBpcCommand = blueprintCopyToBlueprintCopyCommand;
    }

    /**
     * Retrieves all the blueprints.
     *
     * @return a set of blueprints
     */
    @Override
    public Set<Blueprint> getBlueprints() {
        Set<Blueprint> set = new HashSet<>();
        blueprintRepository.findAll().forEach(set::add);
        return set;
    }

    /**
     * Retrieves all the blueprint actions.
     *
     * @return a set of BlueprintAction objects representing the blueprint actions.
     */
    @Override
    @Transactional
    public Set<BlueprintAction> getBlueprintActions() {
        Set<BlueprintAction> set = new HashSet<>();
        blueprintRepository.findAll().forEach(a -> set.addAll(a.getActions()));
        return set;
    }

    /**
     * Retrieves a blueprint by its ID.
     *
     * @param id the ID of the blueprint
     * @return the blueprint with the given ID, or null if not found
     */
    @Override
    public Blueprint findById(Long id) {
        return blueprintRepository.findById(id).orElse(null);
    }

    /**
     * Find a BlueprintAction by blueprint ID and activity ID.
     *
     * @param blueprintId The ID of the blueprint.
     * @param activityId  The ID of the activity.
     * @return The BlueprintAction object that matches the given blueprint ID and activity ID.
     * @throws NoSuchElementException if no matching BlueprintAction is found.
     */
    public BlueprintAction findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId) throws NoSuchElementException {
        Blueprint blueprint = blueprintRepository.findById(blueprintId).orElseThrow();

        for (BlueprintAction action : blueprint.getActions()) {
            if (action.getActivity().getId().equals(activityId))
                return action;
        }
        throw new NoSuchElementException();
    }

    /**
     * Retrieves all the blueprint copies associated with a given item.
     *
     * @param product the item for which to retrieve the blueprint copies
     * @return a set of blueprint copies
     */
    @Override
    public Set<BlueprintCopy> findBlueprintCopiesByItem(Item product) {
        Set<BlueprintCopy> copies = new HashSet<>();
        product.getBlueprints().forEach(val -> {
            copies.addAll(val.getBlueprintAction().getBlueprint().getCopies());
        });
        return copies;
    }

    /**
     * Retrieves a set of BlueprintCopyCommand objects by the given ItemCommand.
     *
     * @param itemCommand the ItemCommand for which to retrieve the BlueprintCopyCommands
     * @return a set of BlueprintCopyCommand objects
     * @throws IllegalArgumentException if no item with the given id is found in the database
     */
    @Override
    public Set<BlueprintCopyCommand> findBlueprintCopyCommandsByItemCommand(ItemCommand itemCommand) {
        Item item = itemCommandToItem.convert(itemCommand);
        item = itemRepository.findById(item.getId()).orElseThrow(() -> new IllegalArgumentException("No item with id=" + itemCommand.getId() + " found in database"));
        Set<BlueprintCopyCommand> bpcs = new HashSet<>();
        findBlueprintCopiesByItem(item).forEach((val) -> bpcs.add(bpcToBpcCommand.convert(val)));
        return bpcs;
    }

    @Override
    public Page<Blueprint> findPaginated(Pageable pageable) {
        return blueprintRepository.findAll(pageable);
    }
}
