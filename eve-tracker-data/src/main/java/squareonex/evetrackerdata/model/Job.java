package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_items"))
    private Item product;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_users"))
    private User user;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private Boolean isInternal;

    public Job(Item product, Long quantity, User user, Boolean isInternal) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
        this.isInternal = isInternal;
    }
}
