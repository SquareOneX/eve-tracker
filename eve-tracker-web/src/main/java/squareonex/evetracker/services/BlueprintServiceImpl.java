package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class BlueprintServiceImpl implements BlueprintService {
    private final BlueprintRepository blueprintRepository;

    public BlueprintServiceImpl(BlueprintRepository blueprintRepository) {
        this.blueprintRepository = blueprintRepository;
    }

    @Override
    public Set<Blueprint> getBlueprints() {
        Set<Blueprint> set = new HashSet<>();
        blueprintRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    @Transactional
    public Set<BlueprintAction> getBlueprintActions() {
        Set<BlueprintAction> set = new HashSet<>();
        blueprintRepository.findAll().forEach(a -> set.addAll(a.getActions()));
        return set;
    }

    @Override
    public Blueprint findById(Long id) {
        return blueprintRepository.findById(id).orElse(null);
    }

    public BlueprintAction findByBlueprintIdAndActivityId(Long blueprintId, Integer activityId) throws NoSuchElementException {
        Blueprint blueprint = blueprintRepository.findById(blueprintId).orElseThrow();

        for (BlueprintAction action : blueprint.getActions()) {
            if (action.getActivity().getId().equals(activityId))
                return action;
        }
        throw new NoSuchElementException();
    }
}
