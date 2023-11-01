package squareonex.evetracker.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.converters.JobCommandToJob;
import squareonex.evetracker.converters.JobToJobCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.model.User;
import squareonex.evetrackerdata.repositories.ItemRepository;
import squareonex.evetrackerdata.repositories.JobRepository;
import squareonex.evetrackerdata.repositories.UserRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {
    private final JobToJobCommand jobToJobCommand;
    private final JobCommandToJob jobCommandToJob;
    private final JobRepository jobRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public JobServiceImpl(JobToJobCommand jobToJobCommand, JobCommandToJob jobCommandToJob, JobRepository jobRepository, ItemRepository itemRepository,
                          UserRepository userRepository) {
        this.jobToJobCommand = jobToJobCommand;
        this.jobCommandToJob = jobCommandToJob;
        this.jobRepository = jobRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
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

    /**
     * Verifies that all required details of the job are set and creates it
     * @param command command object representing a job
     * @return the resulting job
     */
    @Override
    @Transactional
    public JobCommand saveOrUpdateCommand(@NonNull JobCommand command) {
        Job job = jobCommandToJob.convert(command);

        Objects.requireNonNull(job.getQuantity());
        if (job.getQuantity() <= 0)
            throw new IllegalArgumentException("Illegal quantity");

        Item item = job.getProduct();
        Objects.requireNonNull(item.getName());

        if (item.getId() == null){
            item = itemRepository.findByNameIgnoreCase(item.getName()).orElseThrow();
            job.setProduct(item);
        }

        if (job.getProduct().getBlueprints().isEmpty())
            throw new IllegalArgumentException("The specified Item is missing a blueprint to produce it");

        User user = job.getUser();
        Objects.requireNonNull(user.getName());

        if (user.getId() == null)
            job.setUser(userRepository.findByNameIgnoreCase(user.getName()).orElseThrow());

        Job save = jobRepository.save(job);
        return jobToJobCommand.convert(save);
    }
}
