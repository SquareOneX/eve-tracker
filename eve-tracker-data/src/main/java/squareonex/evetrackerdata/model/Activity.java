package squareonex.evetrackerdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "activities")
public class Activity {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private String name;
    private Boolean published;

    public Activity(Integer id, String name, Boolean published) {
        this.id = id;
        this.name = name;
        this.published = published;
    }
}
