package squareonex.evetracker.services;

import org.springframework.stereotype.Service;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.repositories.JobRepository;

@Service
public class JobServiceImpl extends AbstractCrudService<Job, Long> implements JobService {
    public JobServiceImpl(JobRepository repository) {
        super(repository);
    }
}
