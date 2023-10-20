package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.services.JobService;

@RequestMapping("jobs")
@Controller
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("jobs", jobService.findAll());
        return "jobs/list";
    }

    @RequestMapping("new")
    public String newJob(Model model){
        model.addAttribute("job", new JobCommand());
        return "jobs/form";
    }

    @PostMapping("")
    public String createJob(@ModelAttribute JobCommand command) {
        JobCommand saved = jobService.saveOrUpdateCommand(command);

        return "redirect:/jobs";
    }
}
