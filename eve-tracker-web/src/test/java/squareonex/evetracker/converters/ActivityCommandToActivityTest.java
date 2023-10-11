package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.ActivityCommand;
import squareonex.evetrackerdata.model.Activity;

import static org.junit.jupiter.api.Assertions.*;

class ActivityCommandToActivityTest extends ConverterTestTemplate {
    ActivityCommandToActivity converter;
    private final int ACTIVIITY_ID = 0;
    private final String ACTIVITY_NAME = "Activity";
    private final boolean ACTIVITY_PUBLISHED = true;

    @BeforeEach
    public void setUp() {
        this.converter = new ActivityCommandToActivity();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        Activity converted = converter.convert(new ActivityCommand());
        assertNotNull(converted);
        assertEquals(new Activity(), converted);
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        ActivityCommand source = setUpSource();

        Activity converted = converter.convert(source);

        assertEquals(ACTIVIITY_ID, converted.getId());
        assertEquals(ACTIVITY_NAME, converted.getName());
        assertEquals(ACTIVITY_PUBLISHED, converted.getPublished());
    }

    private ActivityCommand setUpSource() {
        ActivityCommand source = new ActivityCommand();
        source.setId(ACTIVIITY_ID);
        source.setName(ACTIVITY_NAME);
        source.setPublished(ACTIVITY_PUBLISHED);
        return source;
    }
}