package squareonex.evetrackerdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Job {
    @EqualsAndHashCode.Include
    private Long id;
    @NonNull private Product product;
    @NonNull private Long quantity;
    @NonNull private User user;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    @NonNull private Boolean isInternal;
}
