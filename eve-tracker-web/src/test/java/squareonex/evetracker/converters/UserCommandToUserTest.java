package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserCommandToUserTest extends ConverterTestTemplate {
    private static final Long USER_ID = 0L;
    private static final String USER_NAME = "Player";
    private static final Long PLAYER_ID = 1L;
    UserCommandToUser converter;
    @BeforeEach
    void setUp() {
        converter = new UserCommandToUser();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        User converted = converter.convert(new UserCommand());
        assertEquals(new User(), converted);
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        UserCommand source = new UserCommand();
        source.setId(USER_ID);
        source.setName(USER_NAME);
        source.setPlayerId(PLAYER_ID);

        User converted = converter.convert(source);
        assertEquals(USER_ID, converted.getId());
        assertEquals(USER_NAME, converted.getName());
        assertEquals(PLAYER_ID, converted.getPlayerId());
    }
}