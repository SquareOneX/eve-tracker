package squareonex.evetrackerdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Blueprint;

@Repository
public interface BlueprintRepository extends JpaRepository<Blueprint, Long> {
}
