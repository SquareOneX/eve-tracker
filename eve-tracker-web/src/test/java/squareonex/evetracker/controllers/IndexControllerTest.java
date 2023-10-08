package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IndexControllerTest {
    IndexController indexController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.indexController = new IndexController();
    }

    @Test
    void showIndexLoadsWebpage() {
        String template = indexController.index();
        assertEquals("index", template);
    }
}