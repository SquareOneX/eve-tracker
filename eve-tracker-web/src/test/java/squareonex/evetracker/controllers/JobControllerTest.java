package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import squareonex.evetracker.services.JobService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JobControllerTest {
    JobController jobController;
    @Mock
    JobService jobService;
    @Mock
    Model modelMock;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.jobController = new JobController(jobService);
    }

    @Test
    void list() {
        String templateString = jobController.list(modelMock);

        assertEquals("jobs/list", templateString);
        verify(modelMock, times(1)).addAttribute("jobs", jobService.findAll());
    }
}