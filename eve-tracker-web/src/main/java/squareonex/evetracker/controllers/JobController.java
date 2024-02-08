package squareonex.evetracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.controllers.exception.InvalidJobException;
import squareonex.evetracker.services.ActivityService;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetracker.services.JobService;
import squareonex.evetracker.services.PagingUtilities;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Job;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.IntStream;

@RequestMapping("jobs")
@Controller
public class JobController {
    private final JobService jobService;
    private final BlueprintService blueprintService;
    private final ActivityService activityService;
    private final int maxItemsPerPage;

    public JobController(
            JobService jobService,
            BlueprintService blueprintService,
            ActivityService activityService,
            @Value("${eve-tracker.pagination.max-items-per-page}") int maxItemsPerPage
    ) {
        this.jobService = jobService;
        this.blueprintService = blueprintService;
        this.activityService = activityService;
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

    @PostMapping("save")
    public String saveJobCommand(@ModelAttribute(name = "jobCommand") JobCommand command) {
        try {
            JobCommand saved = jobService.saveOrUpdateCommand(command);
            return "redirect:/jobs";
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidJobException(e);
        }
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
