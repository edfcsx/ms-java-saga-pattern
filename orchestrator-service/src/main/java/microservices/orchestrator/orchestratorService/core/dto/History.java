package microservices.orchestrator.orchestratorService.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservices.orchestrator.orchestratorService.core.enums.EEventSource;
import microservices.orchestrator.orchestratorService.core.enums.ESagaStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    private EEventSource source;
    private ESagaStatus status;
    private String message;
    private LocalDateTime createdAt;

}
