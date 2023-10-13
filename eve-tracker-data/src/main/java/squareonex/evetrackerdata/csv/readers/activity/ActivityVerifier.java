package squareonex.evetrackerdata.csv.readers.activity;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;

public class ActivityVerifier implements BeanVerifier<ActivityDTO> {
    @Override
    public boolean verifyBean(ActivityDTO activity) throws CsvConstraintViolationException {
        return !activity.getName().equalsIgnoreCase("None") && activity.isPublished();
    }
}
