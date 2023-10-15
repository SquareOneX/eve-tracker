package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Blueprint;

import java.util.Set;

public interface BlueprintService {
    Set<Blueprint> findAll();
}
