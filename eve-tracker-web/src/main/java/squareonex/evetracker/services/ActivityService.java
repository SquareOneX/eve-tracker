package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Activity;

import java.util.Set;

/**
 * Interface for retrieving activities.
 */
public interface ActivityService {
    Set<Activity> findAll();
}
