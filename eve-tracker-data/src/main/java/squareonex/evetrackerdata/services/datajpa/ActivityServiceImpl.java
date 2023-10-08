package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.services.ActivityService;

import java.util.HashSet;
import java.util.Set;

public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Set<Activity> findAll() {
        Set<Activity> set = new HashSet<>();
        for (Activity activity : activityRepository.findAll()) {
            set.add(activity);
        }
        return set;
    }

    @Override
    public Activity findById(Integer id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public Activity save(Activity object) {
        return activityRepository.save(object);
    }

    @Override
    public void delete(Activity object) {
        activityRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Iterable<Activity> saveAll(Iterable<Activity> iterator) {
        return activityRepository.saveAll(iterator);
    }

    @Override
    public long count() {
        return activityRepository.count();
    }
}
