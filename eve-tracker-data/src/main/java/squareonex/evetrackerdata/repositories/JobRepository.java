package squareonex.evetrackerdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
