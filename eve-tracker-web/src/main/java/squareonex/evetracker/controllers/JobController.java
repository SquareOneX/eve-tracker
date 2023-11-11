package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.controllers.exception.InvalidJobException;
import squareonex.evetracker.services.JobService;
import squareonex.evetrackerdata.model.Job;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequestMapping("jobs")
@Controller
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        Set<Job> all = jobService.findAll();
        Map<Long, Long> jobDurations = new HashMap<>();
        all.forEach(val -> {
            if (val.getStartedTime() != null && val.getFinishedTime().isAfter(LocalDateTime.now()))
                jobDurations.put(val.getId(), LocalDateTime.now().until(val.getFinishedTime(), ChronoUnit.MILLIS));
            else
                jobDurations.put(val.getId(), null);
        });
        model.addAttribute("jobs", all);
        model.addAttribute("jobDurations", jobDurations);
        return "jobs/list";
    }

    @RequestMapping("new")
    public String newJob(Model model){
        model.addAttribute("job", new JobCommand());
        return "jobs/form";
    }

    @PostMapping("")
    public String createJob(@ModelAttribute JobCommand command) {
        try {
            JobCommand saved = jobService.saveOrUpdateCommand(command);
            return "redirect:/jobs";
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidJobException(e);
        }
    }
}
