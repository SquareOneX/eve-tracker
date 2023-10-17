package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetrackerdata.model.Blueprint;

@RequestMapping("blueprints")
@Controller
public class BlueprintController {
    private final BlueprintService blueprintService;

    public BlueprintController(BlueprintService blueprintService) {
        this.blueprintService = blueprintService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("blueprints", blueprintService.getBlueprints());
        return "blueprints/list";
    }

    @GetMapping("/{blueprintId}/{activityId}/show")
    public String showBlueprintByIds(@PathVariable Long blueprintId, @PathVariable Integer activityId, Model model){
        try {
            Blueprint blueprint = blueprintService.findByBlueprintIdAndActivityId(blueprintId, activityId);
            model.addAttribute("blueprint", blueprint);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException(
                    "Couldn't find Blueprint with blueprintId= " + blueprintId + " and activityId= " + activityId);
        }
        return "blueprints/show";
    }
}
