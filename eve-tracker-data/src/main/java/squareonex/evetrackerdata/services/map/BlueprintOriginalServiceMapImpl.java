package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.BlueprintOriginal;
import squareonex.evetrackerdata.services.BlueprintOriginalService;

import java.util.HashSet;
import java.util.Set;

public class BlueprintOriginalServiceMapImpl extends AbstractMapService<BlueprintOriginal, Long> implements BlueprintOriginalService {
    private Long key = 0L;

    @Override
    public Set<BlueprintOriginal> findAll() {
        return super.findAll();
    }

    @Override
    public BlueprintOriginal findById(Long id) {
        return super.findById(id);
    }

    @Override
    public BlueprintOriginal save(BlueprintOriginal object) {
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
    public void delete(BlueprintOriginal object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Iterable<BlueprintOriginal> saveAll(Iterable<BlueprintOriginal> iterator) {
        Set<BlueprintOriginal> set = new HashSet<>();
        for (BlueprintOriginal original : iterator) {
            set.add(save(original));
        }
        return set;
    }
}
