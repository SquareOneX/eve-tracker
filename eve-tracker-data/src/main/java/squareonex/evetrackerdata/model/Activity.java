package squareonex.evetrackerdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "activities")
public class Activity {
    @EqualsAndHashCode.Include
    @Id
    private Integer id;
    private String name;
    private boolean published;
}
