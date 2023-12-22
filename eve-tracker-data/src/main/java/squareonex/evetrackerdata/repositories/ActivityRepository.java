package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Activity;

import java.util.Optional;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {
    Activity getActivityById(Integer id);

    Optional<Activity> findActivityByName(String name);
}
