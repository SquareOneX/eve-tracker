package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("jobs")
@Controller
public class JobController {
    @RequestMapping({"", "index", "index.html"})
    public String list(){
        return "notimplemented";
    }
}
