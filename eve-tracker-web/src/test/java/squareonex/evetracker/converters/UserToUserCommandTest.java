package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserToUserCommandTest extends ConverterTestTemplate {
    private static final String USER_NAME = "Player";
    private static final Long USER_ID = 0L;
    private static final Long PLAYER_ID = 1L;
    UserToUserCommand converter;
    @BeforeEach
    void setUp() {
        this.converter = new UserToUserCommand();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new UserCommand(USER_NAME), converter.convert(new User(USER_NAME)));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        User source = createSource();

        UserCommand converted = converter.convert(source);
        assertEquals(USER_NAME, converted.getName());
        assertEquals(USER_ID, converted.getId());
        assertEquals(PLAYER_ID, converted.getPlayerId());
    }

    private User createSource() {
        User source = new User(USER_NAME);
        source.setId(USER_ID);
        source.setPlayerId(PLAYER_ID);
        return source;
    }
}