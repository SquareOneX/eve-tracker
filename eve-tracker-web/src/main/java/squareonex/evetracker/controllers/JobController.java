package squareonex.evetracker.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.controllers.exception.InvalidJobException;
import squareonex.evetracker.converters.JobCommandToJob;
import squareonex.evetracker.converters.JobToJobCommand;
import squareonex.evetracker.services.ActivityService;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetracker.services.JobService;
import squareonex.evetracker.services.PagingUtilities;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Job;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RequestMapping("jobs")
@Controller
@Slf4j
public class JobController {
    private final JobService jobService;
    private final BlueprintService blueprintService;
    private final ActivityService activityService;
    private final JobToJobCommand jobToJobCommand;
    private final JobCommandToJob jobCommandToJob;
    private final int maxItemsPerPage;

    public JobController(
            JobService jobService,
            BlueprintService blueprintService,
            ActivityService activityService,
            JobToJobCommand jobToJobCommand,
            JobCommandToJob jobCommandToJob,
            @Value("${eve-tracker.pagination.max-items-per-page}") int maxItemsPerPage
    ) {
        this.jobService = jobService;
        this.blueprintService = blueprintService;
        this.activityService = activityService;
        this.jobToJobCommand = jobToJobCommand;
        this.jobCommandToJob = jobCommandToJob;
        this.maxItemsPerPage = maxItemsPerPage;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(maxItemsPerPage);

        PagingUtilities.addPageableDataToModel(model, jobService, currentPage, pageSize);

        Map<Long, Long> jobDurations = new HashMap<>();
        Page<Job> jobPage = jobService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        jobPage.forEach(val -> {
            if (val.getStartedTime() != null && val.getFinishedTime().isAfter(LocalDateTime.now()))
                jobDurations.put(val.getId(), LocalDateTime.now().until(val.getFinishedTime(), ChronoUnit.MILLIS));
            else
                jobDurations.put(val.getId(), null);
        });
        model.addAttribute("jobDurations", jobDurations);
        return "jobs/list";
    }

    @RequestMapping("new")
    public String newJob(Model model) {
        model.addAttribute("jobCommand", new JobCommand());
        model.addAttribute("activities", activityService.findAll());
        model.addAttribute("blueprintCopies", new ArrayList<BlueprintCopy>());
        return "jobs/new";
    }

    @PostMapping(value = "save", params = "action=save")
    public String saveJobCommand(@ModelAttribute(name = "jobCommand") JobCommand command) {
        try {
            JobCommand saved = jobService.saveOrUpdateCommand(command);
            log.debug("Saving Job(id=" + saved.getId() + ")");
            return "redirect:/jobs";
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidJobException(e);
        }
    }

    @PostMapping(value = "save", params = "action=start")
    public String startJob(@ModelAttribute(name = "jobCommand") JobCommand command) {
        Job converted = jobCommandToJob.convert(command);
        Objects.requireNonNull(converted.getBlueprintCopy());

        Job startedJob = jobService.startJob(converted.getBlueprintCopy(), converted.getActivity(), converted);
        log.debug("Starting Job(id=" + startedJob.getId() + ")");
        return "redirect:/jobs";
    }

    @RequestMapping("{id}/view")
    public String view(@PathVariable Long id, Model model) {
        JobCommand jobCommand = jobService.findCommandById(id);
        if (jobCommand == null)
            throw new NoSuchElementException("Job with id=" + id + " doesn't exist. It may have been deleted.");

        model.addAttribute("blueprintCopies", blueprintService.findBlueprintCopyCommandsByItemCommand(jobCommand.getItemCommand()));
        model.addAttribute("jobCommand", jobCommand);
        model.addAttribute("activities", activityService.findAll());

        return "jobs/view";
    }
}
