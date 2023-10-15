package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetracker.services.TransactionService;

@RequestMapping("transactions")
@Controller
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("transactions", transactionService.findAll());
        return "transactions/list";
    }
}
