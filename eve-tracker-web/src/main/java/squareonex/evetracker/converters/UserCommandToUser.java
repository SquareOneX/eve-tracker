package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.User;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {
    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {
        if (source == null)
            return null;
        final User target = new User();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPlayerId(source.getPlayerId());

        return target;
    }
}
