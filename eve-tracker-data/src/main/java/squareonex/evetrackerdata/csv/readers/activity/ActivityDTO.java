package squareonex.evetrackerdata.csv.readers.activity;

import com.opencsv.bean.CsvBindByName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ActivityDTO {
    @CsvBindByName(column = "activityId")
    private Integer id;
    @CsvBindByName(column = "activityName")
    private String name;
    @CsvBindByName(column = "published")
    private boolean published;
}
