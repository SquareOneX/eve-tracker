package squareonex.evetracker.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.converters.JobCommandToJob;
import squareonex.evetracker.converters.JobToJobCommand;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.repositories.JobRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    private final JobToJobCommand jobToJobCommand;
    private final JobCommandToJob jobCommandToJob;
    private final JobRepository jobRepository;
    public JobServiceImpl(JobToJobCommand jobToJobCommand, JobCommandToJob jobCommandToJob, JobRepository jobRepository) {
        this.jobToJobCommand = jobToJobCommand;
        this.jobCommandToJob = jobCommandToJob;
        this.jobRepository = jobRepository;
    }

    @Override
    public Set<Job> findAll() {
        Set<Job> set = new HashSet<>();
        jobRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public JobCommand saveOrUpdateCommand(@NonNull JobCommand command) {
        Job job = jobCommandToJob.convert(command);

        if (job == null)
            throw new RuntimeException();
        Job save = jobRepository.save(job);
        return jobToJobCommand.convert(job);
    }
}
