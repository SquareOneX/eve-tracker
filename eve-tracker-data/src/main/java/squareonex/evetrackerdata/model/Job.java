package squareonex.evetrackerdata.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Job {
    @EqualsAndHashCode.Include
    private Long id;
    private Product product;
    private Long quantity;
    private User user;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private boolean isInternal;
}
