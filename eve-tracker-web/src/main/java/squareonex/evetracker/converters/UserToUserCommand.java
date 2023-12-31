package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.User;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {
    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User source) {
        if (source == null)
            return null;
        final UserCommand target = new UserCommand();
        target.setName(source.getName());
        target.setId(source.getId());
        target.setPlayerId(source.getPlayerId());
        return target;
    }
}
