package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
