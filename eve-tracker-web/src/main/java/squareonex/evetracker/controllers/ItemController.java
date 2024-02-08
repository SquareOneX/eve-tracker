package squareonex.evetracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import squareonex.evetracker.services.ItemService;
import squareonex.evetracker.services.PagingUtilities;
import squareonex.evetrackerdata.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RequestMapping("items")
@Controller
public class ItemController {
    private final ItemService itemService;
    private final int maxItemsPerPage;
    public ItemController(ItemService itemService, @Value("${eve-tracker.pagination.max-items-per-page}") int maxItemsPerPage) {
        this.itemService = itemService;
        this.maxItemsPerPage = maxItemsPerPage;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(maxItemsPerPage);

        PagingUtilities.addPageableDataToModel(model, itemService, currentPage, pageSize);

        return "items/list";
    }
}
