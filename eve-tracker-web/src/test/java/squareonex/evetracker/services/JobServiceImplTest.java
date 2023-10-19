package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetracker.converters.*;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.repositories.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class JobServiceImplTest {
    private static final Long JOB_ID = 0L;
    JobServiceImpl jobService;
    @Mock
    JobRepository jobRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.jobService = new JobServiceImpl(
                new JobToJobCommand(new UserToUserCommand(), new ItemToItemCommand()),
                new JobCommandToJob(new UserCommandToUser(), new ItemCommandToItem()),
                jobRepositoryMock
        );
    }

    @Test
    void findAll() {
        assertNotNull(jobService.findAll());

        verify(jobRepositoryMock, times(1)).findAll();
    }


    @Test
    void findById() {
        Job job = mock();
        doReturn(Optional.of(job)).when(jobRepositoryMock).findById(JOB_ID);

        assertNotNull(jobService.findById(JOB_ID));
        verify(jobRepositoryMock, times(1)).findById(JOB_ID);
    }

    @Test
    void saveOrUpdateCommand() {
        JobCommand jobCommand = createJobCommand();
        doAnswer(invocation -> invocation.getArgument(0)).when(jobRepositoryMock).save(any(Job.class));


        JobCommand savedCommand = jobService.saveOrUpdateCommand(jobCommand);
        assertNotNull(savedCommand);
        verify(jobRepositoryMock, times(1)).save(any(Job.class));
    }

    private JobCommand createJobCommand() {
        JobCommand jobCommand = new JobCommand();
        jobCommand.setItemCommand(new ItemCommand());
        jobCommand.setUserCommand(new UserCommand());

        jobCommand.setQuantity(1L);
        jobCommand.setIsInternal(false);

        return jobCommand;
    }
}