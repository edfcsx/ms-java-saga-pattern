package microservices.orchestrated.paymentService.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.orchestrated.paymentService.core.enums.ESagaStatus;

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
