package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetracker.converters.*;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.model.User;
import squareonex.evetrackerdata.repositories.ItemRepository;
import squareonex.evetrackerdata.repositories.JobRepository;
import squareonex.evetrackerdata.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class JobServiceImplTest {
    private static final Long JOB_ID = 0L;
    private static final String ITEM_NAME = "Item";
    private static final String USER_NAME = "USER";
    JobServiceImpl jobService;
    @Mock
    JobRepository jobRepositoryMock;
    @Mock
    ItemRepository itemRepository;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.jobService = new JobServiceImpl(
                new JobToJobCommand(new UserToUserCommand(), new ItemToItemCommand()),
                new JobCommandToJob(new UserCommandToUser(), new ItemCommandToItem()),
                jobRepositoryMock,
                itemRepository,
                userRepository
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
        Item item = new Item();
        item.setBlueprints(Set.of(new BlueprintProduct(null, null, null)));
        doAnswer(invocation -> invocation.getArgument(0)).when(jobRepositoryMock).save(any(Job.class));
        doReturn(Optional.of(item)).when(itemRepository).findByNameIgnoreCase(ITEM_NAME);
        doReturn(Optional.of(new User())).when(userRepository).findByNameIgnoreCase(USER_NAME);


        JobCommand savedCommand = jobService.saveOrUpdateCommand(jobCommand);
        assertNotNull(savedCommand);
        verify(jobRepositoryMock, times(1)).save(any(Job.class));
        verify(itemRepository, times(1)).findByNameIgnoreCase(ITEM_NAME);
        verify(userRepository, times(1)).findByNameIgnoreCase(USER_NAME);
    }

    private JobCommand createJobCommand() {
        JobCommand jobCommand = new JobCommand();
        ItemCommand itemCommand = new ItemCommand();
        itemCommand.setName(ITEM_NAME);
        jobCommand.setItemCommand(itemCommand);
        UserCommand userCommand = new UserCommand();
        userCommand.setName(USER_NAME);
        jobCommand.setUserCommand(userCommand);

        jobCommand.setQuantity(1L);
        jobCommand.setIsInternal(false);

        return jobCommand;
    }
}