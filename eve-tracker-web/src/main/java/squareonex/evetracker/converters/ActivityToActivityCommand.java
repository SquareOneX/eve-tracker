package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.ActivityCommand;
import squareonex.evetrackerdata.model.Activity;

public class ActivityToActivityCommand implements Converter<Activity, ActivityCommand> {
    @Nullable
    @Synchronized
    @Override
    public ActivityCommand convert(Activity source) {
        if (source == null)
            return null;
        final ActivityCommand target = new ActivityCommand();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPublished(source.getPublished());
        return target;
    }
}
