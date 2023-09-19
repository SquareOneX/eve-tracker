package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetrackerdata.services.BlueprintService;

@RequestMapping("blueprints")
@Controller
public class BlueprintController {
    private final BlueprintService blueprintService;

    public BlueprintController(BlueprintService blueprintService) {
        this.blueprintService = blueprintService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("blueprints", blueprintService.findAll());
        return "blueprints/list";
    }
}
