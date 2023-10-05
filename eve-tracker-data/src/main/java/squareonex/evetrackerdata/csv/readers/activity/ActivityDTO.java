package squareonex.evetrackerdata.csv.readers.activity;

import com.opencsv.bean.CsvBindByName;
import lombok.*;
import org.springframework.boot.autoconfigure.jms.JmsProperties;

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
