package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.repositories.ActivityRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Service class for retrieving activities.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /**
     * Retrieves all activities from the repository.
     *
     * @return a set of activities found in the repository
     */
    @Override
    public Set<Activity> findAll() {
        Set<Activity> set = new HashSet<>();
        activityRepository.findAll().forEach(set::add);
        return set;
    }
}
