package squareonex.evetracker.services;

import squareonex.evetracker.commands.JobCommand;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Job;

import java.util.Set;

public interface JobService {
    Set<Job> findAll();
    Job findById(Long id);
    JobCommand findCommandById(Long id);
    JobCommand saveOrUpdateCommand(JobCommand command) throws IllegalArgumentException, NullPointerException;
    Job startJob(BlueprintCopy blueprintCopy, Activity activity, Job job);
}
