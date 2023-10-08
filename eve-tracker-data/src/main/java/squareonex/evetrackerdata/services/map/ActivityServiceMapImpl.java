package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.services.ActivityService;

import java.util.HashSet;
import java.util.Set;

public class ActivityServiceMapImpl extends AbstractMapService<Activity, Integer> implements ActivityService {
    private Integer key = 0;

    @Override
    public Set<Activity> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Iterable<Activity> saveAll(Iterable<Activity> iterator) {
        Set<Activity> set = new HashSet<>();
        for (Activity activity : iterator) {
            set.add(this.save(activity));
        }
        return set;
    }

    @Override
    public void delete(Activity object) {
        super.delete(object);
    }

    @Override
    public Activity save(Activity object) {
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
    public Activity findById(Integer id) {
        return super.findById(id);
    }

    @Override
    public long count() {
        return super.count();
    }
}
