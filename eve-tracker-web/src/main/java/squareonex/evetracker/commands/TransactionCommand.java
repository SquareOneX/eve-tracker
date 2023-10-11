package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TransactionCommand {
    private Long id;
    private ItemCommand itemCommand;
    private Integer quantity;
    private Float price;
    private LocalDateTime date;
    private Boolean isBuy;
}
