package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.services.ActivityService;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetracker.services.JobService;
import squareonex.evetrackerdata.model.BlueprintCopy;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JobControllerTest {
    JobController jobController;
    @Mock
    JobService jobServiceMock;
    @Mock
    Model modelMock;
    @Mock
    BlueprintService blueprintService;
    @Mock ActivityService activityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.jobController = new JobController(jobServiceMock, blueprintService, activityService);
    }

    @Test
    void list() {
        String templateString = jobController.list(modelMock);

        assertEquals("jobs/list", templateString);
        verify(modelMock, times(1)).addAttribute("jobs", jobServiceMock.findAll());
    }

    @Test
    void newJob() {
        String templateStr = jobController.newJob(modelMock);

        assertEquals("jobs/new", templateStr);
        verify(modelMock, times(1)).addAttribute("blueprintCopies", new ArrayList<BlueprintCopy>());
        verify(modelMock, times(1)).addAttribute("jobCommand", new JobCommand());
    }

    @Test
    void createJob() {
        JobCommand jobCommand = new JobCommand();
        String templateStr = jobController.createJob(jobCommand);

        assertEquals("redirect:/jobs", templateStr);
        verify(jobServiceMock, times(1)).saveOrUpdateCommand(jobCommand);
    }
}