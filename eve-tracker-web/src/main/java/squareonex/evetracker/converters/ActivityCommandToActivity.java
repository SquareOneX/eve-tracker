package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.ActivityCommand;
import squareonex.evetrackerdata.model.Activity;

public class ActivityCommandToActivity implements Converter<ActivityCommand, Activity> {
    @Nullable
    @Synchronized
    @Override
    public Activity convert(ActivityCommand source) {
        if (source == null)
            return null;
        final Activity target = new Activity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPublished(source.getPublished());
        return target;
    }
}
