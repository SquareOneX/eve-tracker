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
    private Long quantity;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private Boolean isInternal = false;
    @ManyToOne
    @JoinColumn(name = "bpc_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_blueprintcopies"))
    private BlueprintCopy blueprintCopy;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_items"))
    private Item product;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_jobs_users"))
    private User user;

    public Job(Item product, Long quantity, User user, Boolean isInternal) {
        this.product = product;
        this.quantity = quantity;
        this.user = user;
        this.isInternal = isInternal;
    }
}
