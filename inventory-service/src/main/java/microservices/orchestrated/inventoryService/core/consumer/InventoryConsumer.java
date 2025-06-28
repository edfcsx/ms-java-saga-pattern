package microservices.orchestrated.inventoryService.core.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.orchestrated.inventoryService.core.utils.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class InventoryConsumer {
    private final JsonUtil jsonUtil;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.inventory-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving event: {} from inventory-success topic", payload);
        var event = jsonUtil.ToEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.inventory-fail}"
    )
    public void ConsumeFailEvent(String payload) {
        log.info("Receiving rollback event: {} from inventory-fail topic", payload);
        var event = jsonUtil.ToEvent(payload);
        log.info(event.toString());
    }
}
