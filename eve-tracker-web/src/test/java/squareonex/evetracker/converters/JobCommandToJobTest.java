package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.Job;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JobCommandToJobTest extends ConverterTestTemplate {
    private static final Long JOB_ID = 0L;
    private static final Long JOB_QTY = 100L;
    private static final Long ITEM_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Boolean JOB_IS_INTERNAL = true;
    private static final LocalDateTime JOB_STARTED_TIME = LocalDateTime.now();
    private static final LocalDateTime JOB_FINISHED_TIME = LocalDateTime.now().plusHours(2);
    JobCommandToJob converter;

    @BeforeEach
    void setUp() {
        converter = new JobCommandToJob(
                new UserCommandToUser(),
                new ItemCommandToItem()
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

        Job converted = converter.convert(source);

        assertEquals(JOB_ID, converted.getId());
        assertEquals(JOB_QTY, converted.getQuantity());
        assertEquals(ITEM_ID, converted.getProduct().getId());
        assertEquals(USER_ID, converted.getUser().getId());
        assertEquals(JOB_IS_INTERNAL, converted.getIsInternal());
        assertEquals(JOB_STARTED_TIME, converted.getStartedTime());
        assertEquals(JOB_FINISHED_TIME, converted.getFinishedTime());
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
        return source;
    }
}