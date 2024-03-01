package squareonex.evetracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import squareonex.evetracker.services.TransactionService;
import squareonex.evetrackerdata.model.Transaction;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerIT {
    @Autowired
    TransactionController transactionController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TransactionService transactionService;

    @Test
    void webPageShouldLoad() throws Exception {
        Page<Transaction> expected = transactionService.findPaginated(PageRequest.of(0, 10));
        mockMvc.perform(get("/transactions?page=1&size=10"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("data", expected));
    }
}