package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JobToJobCommandTest extends ConverterTestTemplate {
    private static final Long JOB_ID = 0L;
    private static final Long JOB_QTY = 100L;
    private static final Long ITEM_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Boolean JOB_IS_INTERNAL = true;
    private static final LocalDateTime JOB_STARTED_TIME = LocalDateTime.now();
    private static final LocalDateTime JOB_FINISHED_TIME = LocalDateTime.now().plusHours(2);
    JobToJobCommand converter;
    @BeforeEach
    void setUp() {
        this.converter = new JobToJobCommand(
                new UserToUserCommand(),
                new ItemToItemCommand()
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
        assertEquals(new JobCommand(), converter.convert(new Job()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        Job source = createSource();

        JobCommand converted = converter.convert(source);

        assertEquals(JOB_ID, converted.getId());
        assertEquals(JOB_QTY, converted.getQuantity());
        assertEquals(ITEM_ID, converted.getItemCommand().getId());
        assertEquals(USER_ID, converted.getUserCommand().getId());
        assertEquals(JOB_IS_INTERNAL, converted.getIsInternal());
        assertEquals(JOB_STARTED_TIME, converted.getStartedTime());
        assertEquals(JOB_FINISHED_TIME, converted.getFinishedTime());
    }

    private Job createSource() {
        Job source = new Job();
        source.setId(JOB_ID);
        source.setIsInternal(JOB_IS_INTERNAL);
        source.setQuantity(JOB_QTY);
        source.setStartedTime(JOB_STARTED_TIME);
        source.setFinishedTime(JOB_FINISHED_TIME);

        source.setProduct(new Item(ITEM_ID, null));
        User user = new User();
        user.setId(USER_ID);
        source.setUser(user);
        return source;
    }
}