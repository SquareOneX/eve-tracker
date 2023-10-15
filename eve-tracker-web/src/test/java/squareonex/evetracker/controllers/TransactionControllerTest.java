package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import squareonex.evetracker.services.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TransactionControllerTest {
    TransactionController transactionController;
    @Mock
    TransactionService transactionService;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.transactionController = new TransactionController(transactionService);
    }

    @Test
    void list() {
        String templateStr = transactionController.list(model);

        assertEquals("transactions/list", templateStr);
        verify(model, times(1)).addAttribute("transactions", transactionService.findAll());
    }
}