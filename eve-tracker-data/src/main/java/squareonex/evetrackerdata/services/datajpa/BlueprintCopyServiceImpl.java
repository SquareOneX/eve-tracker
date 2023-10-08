package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.repositories.BlueprintCopyRepository;
import squareonex.evetrackerdata.services.BlueprintCopyService;

import java.util.HashSet;
import java.util.Set;

public class BlueprintCopyServiceImpl implements BlueprintCopyService {
    private final BlueprintCopyRepository blueprintCopyRepository;

    public BlueprintCopyServiceImpl(BlueprintCopyRepository blueprintCopyRepository) {
        this.blueprintCopyRepository = blueprintCopyRepository;
    }

    @Override
    public Set<BlueprintCopy> findAll() {
        Set<BlueprintCopy> set = new HashSet<>();
        blueprintCopyRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public BlueprintCopy findById(Long id) {
        return blueprintCopyRepository.findById(id).orElse(null);
    }

    @Override
    public BlueprintCopy save(BlueprintCopy object) {
        return blueprintCopyRepository.save(object);
    }

    @Override
    public void delete(BlueprintCopy object) {
        blueprintCopyRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        blueprintCopyRepository.deleteById(id);
    }

    @Override
    public Iterable<BlueprintCopy> saveAll(Iterable<BlueprintCopy> iterator) {
        Set<BlueprintCopy> set = new HashSet<>();
        blueprintCopyRepository.saveAll(iterator).forEach(set::add);
        return set;
    }

    @Override
    public long count() {
        return blueprintCopyRepository.count();
    }
}
