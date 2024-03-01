package squareonex.evetrackerdata.csv.readers.activity;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.model.Activity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityReaderImpl implements ActivityReader {
    @Override
    public List<Activity> readAll() throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource("activities.csv");
        FileReader reader = new FileReader(resource.getPath());
        CsvToBean<ActivityDTO> csvToBean = new CsvToBeanBuilder<ActivityDTO>(reader)
                .withType(ActivityDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withVerifier(new ActivityVerifier())
                .build();

        List<Activity> activities = new ArrayList<>();

        for (ActivityDTO dto : csvToBean) {
            Activity activity = new Activity();
            activity.setId(dto.getId());
            activity.setName(dto.getName());
            activity.setPublished(dto.getPublished());
            activities.add(activity);
        }
        return activities;
    }
}
