package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintKey;

import java.util.Optional;

@Repository
public interface BlueprintRepository extends CrudRepository<Blueprint, BlueprintKey> {
    Optional<Blueprint> findByActivity_IdAndItemInfo_Id(Integer activityId, Long itemId);
}
