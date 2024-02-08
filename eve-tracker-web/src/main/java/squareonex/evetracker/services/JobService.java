package squareonex.evetracker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Job;

import java.util.Set;

public interface JobService extends PageableService<Job> {
    Set<Job> findAll();
    Job findById(Long id);
    JobCommand findCommandById(Long id);
    JobCommand saveOrUpdateCommand(JobCommand command) throws IllegalArgumentException, NullPointerException;
    Job startJob(BlueprintCopy blueprintCopy, Activity activity, Job job);
}
