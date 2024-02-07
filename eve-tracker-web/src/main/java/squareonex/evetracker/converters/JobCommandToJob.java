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
public class JobCommandToJob implements Converter<JobCommand, Job> {
    private final Converter<UserCommand, User> userCommandToUser;
    private final Converter<ItemCommand, Item> itemCommandToItem;
    private final Converter<BlueprintCopyCommand, BlueprintCopy> blueprintCopyCommandBlueprintCopy;

    public JobCommandToJob(Converter<UserCommand, User> userCommandToUser, Converter<ItemCommand, Item> itemCommandToItem, Converter<BlueprintCopyCommand, BlueprintCopy> blueprintCopyCommandBlueprintCopy) {
        this.userCommandToUser = userCommandToUser;
        this.itemCommandToItem = itemCommandToItem;
        this.blueprintCopyCommandBlueprintCopy = blueprintCopyCommandBlueprintCopy;
    }

    @Nullable
    @Synchronized
    @Override
    public Job convert(JobCommand source) {
        if (source == null)
            return null;
        final Job target = new Job(
                itemCommandToItem.convert(source.getItemCommand()),
                source.getQuantity(),
                userCommandToUser.convert(source.getUserCommand()),
                source.getIsInternal()
        );
        target.setId(source.getId());
        target.setStartedTime(source.getStartedTime());
        target.setFinishedTime(source.getFinishedTime());
        target.setActivity(source.getActivity());
        target.setBlueprintCopy(source.getBlueprintCopy());
        return target;
    }
}
