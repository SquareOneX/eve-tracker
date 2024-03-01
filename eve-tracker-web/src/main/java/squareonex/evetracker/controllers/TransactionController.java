package squareonex.evetracker.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import squareonex.evetracker.services.PagingUtilities;
import squareonex.evetracker.services.TransactionService;

import java.util.Optional;

@RequestMapping("transactions")
@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final int maxItemsPerPage;

    public TransactionController(TransactionService transactionService, @Value("${eve-tracker.pagination.max-items-per-page}") int maxItemsPerPage) {
        this.transactionService = transactionService;
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

        PagingUtilities.addPageableDataToModel(model, transactionService, currentPage, pageSize);
        return "transactions/list";
    }
}
