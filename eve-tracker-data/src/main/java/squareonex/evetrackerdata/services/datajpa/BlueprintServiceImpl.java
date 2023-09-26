package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintKey;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.services.BlueprintService;

import java.util.HashSet;
import java.util.Set;

public class BlueprintServiceImpl implements BlueprintService {
    private final BlueprintRepository blueprintRepository;

    public BlueprintServiceImpl(BlueprintRepository blueprintRepository) {
        this.blueprintRepository = blueprintRepository;
    }

    @Override
    public Set<Blueprint> findAll() {
        Set<Blueprint> set = new HashSet<>();
        for (Blueprint blueprint : blueprintRepository.findAll()) {
            set.add(blueprint);
        }
        return set;
    }

    @Override
    public Blueprint findById(BlueprintKey id) {
        return blueprintRepository.findById(id).orElse(null);
    }

    @Override
    public Blueprint save(Blueprint object) {
        return blueprintRepository.save(object);
    }

    @Override
    public void delete(Blueprint object) {
        blueprintRepository.delete(object);
    }

    @Override
    public void deleteById(BlueprintKey id) {
        blueprintRepository.deleteById(id);
    }

    @Override
    public Iterable<Blueprint> saveAll(Iterable<Blueprint> iterator) {
        return blueprintRepository.saveAll(iterator);
    }
}
