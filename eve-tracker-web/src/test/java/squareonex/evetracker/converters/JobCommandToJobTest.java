package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.repositories.BlueprintCopyRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class JobCommandToJobTest extends ConverterTestTemplate {
    private static final Long JOB_ID = 0L;
    private static final Long JOB_QTY = 100L;
    private static final Long ITEM_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Boolean JOB_IS_INTERNAL = true;
    private static final LocalDateTime JOB_STARTED_TIME = LocalDateTime.now();
    private static final LocalDateTime JOB_FINISHED_TIME = LocalDateTime.now().plusHours(2);
    private static final Long BPC_ID = 0L;
    private static final Integer ACTIVITY_ID = 0;
    private static final Long BLUEPRINT_ID = 0L;
    private static final String BLUEPRINT_NAME = "BPO";
    JobCommandToJob converter;
    @Mock
    private BlueprintCopyRepository blueprintCopyRepositoryMock;
    @Mock
    private BlueprintRepository blueprintRepositoyMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        converter = new JobCommandToJob(
                new UserCommandToUser(),
                new ItemCommandToItem(),
                new BlueprintCopyCommandToBlueprintCopy(blueprintCopyRepositoryMock, blueprintRepositoyMock)
        );
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new Job(), converter.convert(new JobCommand()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        JobCommand source = getSource();
        when(blueprintRepositoyMock.findById(source.getBlueprintCopy().getBlueprint().getId())).thenReturn(Optional.of(new Blueprint(BLUEPRINT_ID, BLUEPRINT_NAME)));

        Job converted = converter.convert(source);

        assertEquals(JOB_ID, converted.getId());
        assertEquals(JOB_QTY, converted.getQuantity());
        assertEquals(ITEM_ID, converted.getProduct().getId());
        assertEquals(USER_ID, converted.getUser().getId());
        assertEquals(JOB_IS_INTERNAL, converted.getIsInternal());
        assertEquals(JOB_STARTED_TIME, converted.getStartedTime());
        assertEquals(JOB_FINISHED_TIME, converted.getFinishedTime());
        assertEquals(BPC_ID, converted.getBlueprintCopy().getId());
        assertEquals(ACTIVITY_ID, converted.getActivity().getId());
    }

    private JobCommand getSource() {
        JobCommand source = new JobCommand();
        source.setId(JOB_ID);
        source.setQuantity(JOB_QTY);
        ItemCommand item = new ItemCommand();
        item.setId(ITEM_ID);
        source.setItemCommand(item);
        UserCommand user = new UserCommand();
        user.setId(USER_ID);
        source.setUserCommand(user);
        source.setIsInternal(JOB_IS_INTERNAL);
        source.setStartedTime(JOB_STARTED_TIME);
        source.setFinishedTime(JOB_FINISHED_TIME);
        BlueprintCopy blueprintCopy = new BlueprintCopy(new Blueprint(BLUEPRINT_ID, BLUEPRINT_NAME), null, null);
        blueprintCopy.setId(BPC_ID);
        source.setBlueprintCopy(blueprintCopy);
        Activity activity = new Activity();
        activity.setId(ACTIVITY_ID);
        source.setActivity(activity);
        return source;
    }
}