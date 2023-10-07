package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_items"))
    @NonNull private Item product;
    @NonNull private Long quantity;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_users"))
    @NonNull private User user;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    @NonNull private Boolean isInternal;
}
