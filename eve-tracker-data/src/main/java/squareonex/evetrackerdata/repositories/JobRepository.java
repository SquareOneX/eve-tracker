package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
}
