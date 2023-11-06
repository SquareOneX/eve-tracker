package squareonex.evetracker.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.converters.JobCommandToJob;
import squareonex.evetracker.converters.JobToJobCommand;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.repositories.ItemRepository;
import squareonex.evetrackerdata.repositories.JobRepository;
import squareonex.evetrackerdata.repositories.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class JobServiceImpl implements JobService {
    private final JobToJobCommand jobToJobCommand;
    private final JobCommandToJob jobCommandToJob;
    private final JobRepository jobRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    public JobServiceImpl(JobToJobCommand jobToJobCommand, JobCommandToJob jobCommandToJob, JobRepository jobRepository, ItemRepository itemRepository,
                          UserRepository userRepository, StorageService storageService) {
        this.jobToJobCommand = jobToJobCommand;
        this.jobCommandToJob = jobCommandToJob;
        this.jobRepository = jobRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
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
     *
     * @param command command object representing a job
     * @return the resulting job
     */
    @Override
    @Transactional
    public JobCommand saveOrUpdateCommand(@NonNull JobCommand command) throws IllegalArgumentException, NullPointerException {
        Job job = jobCommandToJob.convert(command);

        if (command.getItemCommand() == null) {
            throw new NullPointerException("Expected non-null item for job");
        } else if (command.getItemCommand().getId() != null) {
            itemRepository.findById(command.getItemCommand().getId())
                    .ifPresentOrElse(job::setProduct, () -> {
                        throw new IllegalArgumentException("Item with id=" + command.getItemCommand().getId() + " doesn't exist");
                    });
        } else if (command.getItemCommand().getName() != null) {
            itemRepository.findByNameIgnoreCase(command.getItemCommand().getName())
                    .ifPresentOrElse(job::setProduct, () -> {
                        throw new IllegalArgumentException("Item with name=" + command.getItemCommand().getName() + " doesn't exist");
                    });
        } else {
            throw new NullPointerException("Expected non-null name or id for item");
        }

        if (command.getUserCommand() == null) {
            throw new NullPointerException("Expected non-null user for job");
        } else if (command.getUserCommand().getId() != null) {
            userRepository.findById(command.getUserCommand().getId()).ifPresentOrElse(
                    job::setUser, () -> {
                        throw new IllegalArgumentException("User with id=" + command.getUserCommand().getId() + " doesn't exist");
                    }
            );
        } else if (command.getUserCommand().getName() != null) {
            userRepository.findByNameIgnoreCase(command.getUserCommand().getName()).ifPresentOrElse(
                    job::setUser, () -> {
                        throw new IllegalArgumentException("User with name=" + command.getUserCommand().getName() + " doesn't exist");
                    }
            );
        } else {
            throw new NullPointerException("Expected non-null name or id for user");
        }

        Objects.requireNonNull(job.getQuantity());
        if (job.getQuantity() <= 0)
            throw new IllegalArgumentException("Illegal quantity");

        if (job.getProduct().getBlueprints().isEmpty())
            throw new IllegalArgumentException("The specified Item is missing a blueprint to produce it");


        if (job.getFinishedTime() != null && job.getStartedTime() != null && job.getFinishedTime().isBefore(job.getStartedTime()))
            throw new IllegalArgumentException("The job is finished before it starts");

        return jobToJobCommand.convert(jobRepository.save(job));
    }

    @Override
    @Transactional
    public Job startJob(BlueprintCopy blueprintCopy, Activity activity, Job  job) {
        Blueprint blueprint = blueprintCopy.getBlueprint();
        BlueprintAction blueprintAction = null;
        for (BlueprintAction action : blueprint.getActions()) {
            if (action.getActivity().equals(activity))
                blueprintAction = action;
        }
        if (blueprintAction == null)
            throw new IllegalArgumentException("BlueprintCopy cannot be used with this activity");

        Map<Item, Long> materials = calculateMaterialRequirements(blueprintAction.getMaterials(), blueprintCopy, job.getQuantity());
        double jobCost = 0.0; //TODO Calculate additional service cost based on location and taxes
        for (Map.Entry<Item, Long> entry : materials.entrySet()) {
            if (!storageService.isAvailable(entry.getKey(), entry.getValue())) {
                throw new IllegalArgumentException("Not enough materials (missing " + entry.getKey().getName() + ")");
            }

            storageService.remove(entry.getKey(), entry.getValue());
            jobCost += entry.getKey().getAvgCost() * entry.getValue();
        }
        job.setJobCost(jobCost);

        Duration modifiedDuration = Duration.ofSeconds(Math.round(blueprintAction.getDuration().toSeconds() * blueprintCopy.getTimeModifier()));
        job.setStartedTime(LocalDateTime.now());
        job.setFinishedTime(job.getStartedTime().plus(modifiedDuration));

        return jobRepository.save(job);
    }

    private Map<Item, Long> calculateMaterialRequirements(
        Set<BlueprintMaterial> baseMaterials, BlueprintCopy blueprintCopy, Long runs) {
        Map<Item, Long> materialMap = new HashMap<>();
        for (BlueprintMaterial material : baseMaterials) {
            double quantity = material.getQuantity() * runs * blueprintCopy.getMaterialModifier();

            materialMap.put(material.getMaterial(), Math.round(quantity));
        }

        return materialMap;
    }
}
