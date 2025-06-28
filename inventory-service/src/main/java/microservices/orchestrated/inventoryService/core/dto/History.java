package microservices.orchestrated.inventoryService.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.orchestrated.inventoryService.core.enums.ESagaStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    private String source;
    private ESagaStatus status;
    private String message;
    private LocalDateTime createdAt;

}
