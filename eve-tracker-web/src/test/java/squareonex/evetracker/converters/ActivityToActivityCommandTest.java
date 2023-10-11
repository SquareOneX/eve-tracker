package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.ActivityCommand;
import squareonex.evetrackerdata.model.Activity;

import static org.junit.jupiter.api.Assertions.*;

class ActivityToActivityCommandTest extends ConverterTestTemplate {
    private final int ACTIVIITY_ID = 0;
    private final String ACTIVITY_NAME = "Activity";
    private final boolean ACTIVITY_PUBLISHED = true;
    ActivityToActivityCommand converter;
    @BeforeEach
    void setUp() {
        this.converter = new ActivityToActivityCommand();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        ActivityCommand converted = converter.convert(new Activity());
        assertNotNull(converted);
        assertEquals(new ActivityCommand(), converted);
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        Activity source = setUpSource();

        ActivityCommand converted = converter.convert(source);

        assertEquals(ACTIVIITY_ID, converted.getId());
        assertEquals(ACTIVITY_NAME, converted.getName());
        assertEquals(ACTIVITY_PUBLISHED, converted.getPublished());
    }

    private Activity setUpSource() {
        Activity source = new Activity();
        source.setId(ACTIVIITY_ID);
        source.setName(ACTIVITY_NAME);
        source.setPublished(ACTIVITY_PUBLISHED);
        return source;
    }
}