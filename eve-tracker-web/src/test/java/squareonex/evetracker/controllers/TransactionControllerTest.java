package squareonex.evetracker.controllers;

import org.h2.mvstore.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import squareonex.evetracker.services.TransactionService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.*;

class TransactionControllerTest {
    TransactionController transactionController;
    @Mock
    TransactionService transactionService;
    @Mock
    Model model;
    private int maxItemsPerPage = 10;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.transactionController = new TransactionController(transactionService, maxItemsPerPage);
    }

    @Test
    void list() {
        PageImpl<Object> expected = new PageImpl<>(List.of());
        when(transactionService.findPaginated(any(Pageable.class))).thenAnswer(invocation -> expected);
        String templateStr = transactionController.list(model, Optional.empty(), Optional.empty());

        assertEquals("transactions/list", templateStr);
        verify(model, times(1)).addAttribute(eq("data"), eq(expected));
    }
}