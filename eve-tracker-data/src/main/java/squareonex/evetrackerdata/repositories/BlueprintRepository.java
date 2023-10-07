package squareonex.evetrackerdata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.ids.BlueprintId;

import java.util.Optional;

@Repository
public interface BlueprintRepository extends CrudRepository<Blueprint, BlueprintId> {
    Optional<Blueprint> findBlueprintById_Activity_IdAndId_ItemInfo_Id(Integer id_activity_id, Long id_itemInfo_id);
}
