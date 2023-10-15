package squareonex.evetracker.services;

import squareonex.evetrackerdata.model.Job;

import java.util.Set;

public interface JobService {
    Set<Job> findAll();
}
