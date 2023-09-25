package squareonex.evetrackerdata.services.datajpa;

import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.repositories.JobRepository;
import squareonex.evetrackerdata.services.JobService;

import java.util.HashSet;
import java.util.Set;

public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Set<Job> findAll() {
        Set<Job> set = new HashSet<>();
        for (Job job : jobRepository.findAll()) {
            set.add(job);
        }
        return set;
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job save(Job object) {
        return jobRepository.save(object);
    }

    @Override
    public void delete(Job object) {
        jobRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }
}
