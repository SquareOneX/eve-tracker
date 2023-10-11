package squareonex.evetracker.commands;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class TransactionCommand {
    private Long id;
    private ItemCommand itemCommand;
    private Integer quantity;
    private Float price;
    private LocalDateTime date;
    private Boolean isBuy;
}
