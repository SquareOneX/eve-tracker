package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.services.BlueprintCopyService;

import java.util.HashSet;
import java.util.Set;

public class BlueprintCopyServiceMapImpl extends AbstractMapService<BlueprintCopy, Long> implements BlueprintCopyService {
    private Long key = 0L;

    @Override
    public Set<BlueprintCopy> findAll() {
        return super.findAll();
    }

    @Override
    public BlueprintCopy findById(Long id) {
        return super.findById(id);
    }

    @Override
    public BlueprintCopy save(BlueprintCopy object) {
        if (object.getId() == null){
            while (map.containsKey(key))
                key++;
            object.setId(key);
            return super.save(key, object);
        }else {
            return super.save(object.getId(), object);
        }
    }

    @Override
    public void delete(BlueprintCopy object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Iterable<BlueprintCopy> saveAll(Iterable<BlueprintCopy> iterator) {
        Set<BlueprintCopy> set = new HashSet<>();
        for (BlueprintCopy copy : iterator) {
            set.add(this.save(copy));
        }
        return set;
    }
}
