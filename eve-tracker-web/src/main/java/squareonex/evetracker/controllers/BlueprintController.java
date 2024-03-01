package squareonex.evetracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetracker.services.PagingUtilities;
import squareonex.evetrackerdata.model.BlueprintAction;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping("blueprints")
@Controller
public class BlueprintController {
    private final BlueprintService blueprintService;
    private final int maxItemsPerPage;

    public BlueprintController(BlueprintService blueprintService, @Value("${eve-tracker.pagination.max-items-per-page}") int maxItemsPerPage) {
        this.blueprintService = blueprintService;
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

        PagingUtilities.addPageableDataToModel(model, blueprintService, currentPage, pageSize);

        return "blueprints/list";
    }

    @GetMapping("/{blueprintId}/{activityId}/show")
    public String showBlueprintByIdAndActivity(@PathVariable Long blueprintId, @PathVariable Integer activityId, Model model) {
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
