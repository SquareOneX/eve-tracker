package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.BlueprintOriginal;
import squareonex.evetrackerdata.repositories.BlueprintOriginalRepository;
import squareonex.evetrackerdata.services.BlueprintOriginalService;

import java.util.HashSet;
import java.util.Set;

public class BlueprintOriginalServiceImpl implements BlueprintOriginalService {
    private final BlueprintOriginalRepository blueprintOriginalRepository;

    public BlueprintOriginalServiceImpl(BlueprintOriginalRepository blueprintOriginalRepository) {
        this.blueprintOriginalRepository = blueprintOriginalRepository;
    }

    @Override
    public Set<BlueprintOriginal> findAll() {
        Set<BlueprintOriginal> set = new HashSet<>();
        blueprintOriginalRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public BlueprintOriginal findById(Long id) {
        return blueprintOriginalRepository.findById(id).orElse(null);
    }

    @Override
    public BlueprintOriginal save(BlueprintOriginal object) {
        return blueprintOriginalRepository.save(object);
    }

    @Override
    public void delete(BlueprintOriginal object) {
        blueprintOriginalRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        blueprintOriginalRepository.deleteById(id);
    }

    @Override
    public Iterable<BlueprintOriginal> saveAll(Iterable<BlueprintOriginal> iterator) {
        return blueprintOriginalRepository.saveAll(iterator);
    }
}
