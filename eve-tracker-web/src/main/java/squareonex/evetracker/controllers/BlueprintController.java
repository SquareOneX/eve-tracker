package squareonex.evetracker.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetrackerdata.model.BlueprintAction;

import java.util.NoSuchElementException;

@RequestMapping("blueprints")
@Controller
public class BlueprintController {
    private final BlueprintService blueprintService;

    public BlueprintController(BlueprintService blueprintService) {
        this.blueprintService = blueprintService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("blueprintActions", blueprintService.getBlueprintActions());
        return "blueprints/list";
    }

    @GetMapping("/{blueprintId}/{activityId}/show")
    public String showBlueprintByIdAndActivity(@PathVariable Long blueprintId, @PathVariable Integer activityId, Model model){
        try {
            BlueprintAction blueprint = blueprintService.findByBlueprintIdAndActivityId(blueprintId, activityId);
            model.addAttribute("blueprint", blueprint);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Blueprint with blueprintId=" + blueprintId + " and activityId=" + activityId + " not found"
            );
        }
        return "blueprints/show";
    }
}
