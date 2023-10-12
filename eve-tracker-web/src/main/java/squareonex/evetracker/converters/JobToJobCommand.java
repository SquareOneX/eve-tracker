package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.model.User;

public class JobToJobCommand implements Converter<Job, JobCommand> {
    private final Converter<User, UserCommand> userToUserCommand;
    private final Converter<Item, ItemCommand> itemToItemCommand;

    public JobToJobCommand(Converter<User, UserCommand> userToUserCommand, Converter<Item, ItemCommand> itemToItemCommand) {
        this.userToUserCommand = userToUserCommand;
        this.itemToItemCommand = itemToItemCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public JobCommand convert(Job source) {
        if (source == null)
            return null;
        JobCommand target = new JobCommand();
        target.setId(source.getId());
        target.setIsInternal(source.getIsInternal());
        target.setStartedTime(source.getStartedTime());
        target.setFinishedTime(source.getFinishedTime());
        target.setItemCommand(itemToItemCommand.convert(source.getProduct()));
        target.setUserCommand(userToUserCommand.convert(source.getUser()));
        target.setQuantity(source.getQuantity());
        return target;
    }
}
