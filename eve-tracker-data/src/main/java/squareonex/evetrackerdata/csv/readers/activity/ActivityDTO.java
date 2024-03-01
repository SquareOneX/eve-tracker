package squareonex.evetrackerdata.csv.readers.activity;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ActivityDTO {
    @NonNull
    @CsvBindByName(column = "activityId")
    private Integer id;
    @NonNull
    @CsvBindByName(column = "activityName")
    private String name;
    @NonNull
    @CsvBindByName(column = "published")
    private Boolean published;
}
