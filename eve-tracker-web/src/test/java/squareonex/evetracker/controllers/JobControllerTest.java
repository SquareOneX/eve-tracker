package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.converters.JobCommandToJob;
import squareonex.evetracker.converters.JobToJobCommand;
import squareonex.evetracker.services.ActivityService;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetracker.services.JobService;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Job;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JobControllerTest {
    private final int maxItemsPerPage = 10;
    JobController jobController;
    @Mock
    JobService jobServiceMock;
    @Mock
    Model modelMock;
    @Mock
    BlueprintService blueprintService;
    @Mock
    ActivityService activityService;
    @Mock
    JobCommandToJob jobCommandToJobMock;
    @Mock
    JobToJobCommand jobToJobCommandMock;
    @Captor
    ArgumentCaptor<Map<Long, Long>> jobDurationsArgCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.jobController = new JobController(jobServiceMock, blueprintService, activityService, jobToJobCommandMock, jobCommandToJobMock, maxItemsPerPage);
    }

    @Test
    void list() {
        Page<Job> expectedPage = Page.empty();
        when(jobServiceMock.findPaginated(any(Pageable.class))).thenReturn(expectedPage);
        String templateString = jobController.list(modelMock, Optional.empty(), Optional.empty());

        assertEquals("jobs/list", templateString);
        verify(modelMock, times(1)).addAttribute("data", expectedPage);
    }

    @Test
    void listShouldPopulateJobDurationData() {
        final Long jobId = 0L;
        final long jobDuration = 2L;        // Hours

        Job job = new Job();
        job.setId(jobId);
        job.setStartedTime(LocalDateTime.now());
        job.setFinishedTime(job.getStartedTime().plusHours(jobDuration));
        Page<Job> expectedPage = new PageImpl<>(List.of(job));

        when(jobServiceMock.findPaginated(any(Pageable.class))).thenReturn(expectedPage);
        jobController.list(modelMock, Optional.empty(), Optional.empty());

        verify(modelMock).addAttribute(eq("jobDurations"), jobDurationsArgCaptor.capture());

        long expectedDuration = Duration.ofHours(jobDuration).toMillis();
        long tolerance = Duration.ofSeconds(1).toMillis();

        Map<Long, Long> value = jobDurationsArgCaptor.getValue();
        assertTrue(
                expectedDuration - tolerance < value.get(jobId) &&
                        expectedDuration + tolerance > value.get(jobId)
        );
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
        when(jobServiceMock.saveOrUpdateCommand(any())).thenAnswer(inv -> inv.getArgument(0));

        JobCommand jobCommand = new JobCommand();
        String templateStr = jobController.saveJobCommand(jobCommand);

        assertEquals("redirect:/jobs", templateStr);
        verify(jobServiceMock, times(1)).saveOrUpdateCommand(jobCommand);
    }

    @Test
    void viewShouldShowCorrectTemplate() {
        long jobId = 1L;
        Job job = new Job();
        job.setId(jobId);
        Item product = new Item(1L, null);
        job.setProduct(product);
        when(jobServiceMock.findById(jobId)).thenReturn(job);
        when(jobServiceMock.findCommandById(jobId)).thenReturn(new JobCommand());
        String template = jobController.view(jobId, modelMock);

        assertEquals("jobs/view", template);
        verify(modelMock, times(1)).addAttribute(eq("blueprintCopies"), any());
        verify(modelMock, times(1)).addAttribute(eq("jobCommand"), any(JobCommand.class));
        verify(modelMock, times(1)).addAttribute(eq("activities"), any());
    }
}