package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.repositories.JobRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Set<Job> findAll() {
        Set<Job> set = new HashSet<>();
        jobRepository.findAll().forEach(set::add);
        return set;
    }
}
