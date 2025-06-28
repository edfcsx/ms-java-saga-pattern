package microservices.orchestrated.productValidationService.core.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.orchestrated.productValidationService.core.utils.JsonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ProductValidationConsumer {
    private final JsonUtil jsonUtil;

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.product-validation-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving event: {} from product-validation-success topic", payload);
        var event = jsonUtil.ToEvent(payload);
        log.info(event.toString());
    }

    @KafkaListener(
        groupId = "${spring.kafka.consumer.group-id}",
        topics = "${spring.kafka.topic.product-validation-fail}"
    )
    public void ConsumeFailEvent(String payload) {
        log.info("Receiving rollback event: {} from product-validation-fail topic", payload);
        var event = jsonUtil.ToEvent(payload);
        log.info(event.toString());
    }
}
