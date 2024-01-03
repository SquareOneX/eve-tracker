package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.commands.JobCommand;
import squareonex.evetracker.commands.UserCommand;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.model.User;

@Component
public class JobToJobCommand implements Converter<Job, JobCommand> {
    private final Converter<User, UserCommand> userToUserCommand;
    private final Converter<Item, ItemCommand> itemToItemCommand;
    private final Converter<BlueprintCopy, BlueprintCopyCommand> blueprintCopyToBlueprintCopyCommand;

    public JobToJobCommand(Converter<User, UserCommand> userToUserCommand, Converter<Item, ItemCommand> itemToItemCommand, Converter<BlueprintCopy, BlueprintCopyCommand> blueprintCopyToBlueprintCopyCommand) {
        this.userToUserCommand = userToUserCommand;
        this.itemToItemCommand = itemToItemCommand;
        this.blueprintCopyToBlueprintCopyCommand = blueprintCopyToBlueprintCopyCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public JobCommand convert(Job source) {
        if (source == null)
            return null;
        final JobCommand target = new JobCommand();
        target.setId(source.getId());
        target.setIsInternal(source.getIsInternal());
        target.setStartedTime(source.getStartedTime());
        target.setFinishedTime(source.getFinishedTime());
        target.setItemCommand(itemToItemCommand.convert(source.getProduct()));
        target.setUserCommand(userToUserCommand.convert(source.getUser()));
        target.setQuantity(source.getQuantity());
        target.setActivity(source.getActivity());
        target.setBlueprintCopy(blueprintCopyToBlueprintCopyCommand.convert(source.getBlueprintCopy()));
        return target;
    }
}
