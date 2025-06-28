package microservices.orchestrated.productValidationService.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.orchestrator}")
    public String orchestratorTopic;

    public void sendEvent(String payload) {
        try {
            log.info("Sending data to topic {} with data {}", this.orchestratorTopic, payload);
            kafkaTemplate.send(this.orchestratorTopic, payload).get();
            log.info("Data sent successfully to topic {}", this.orchestratorTopic);
        } catch (Exception ex) {
            log.error("Error trying to send data to topic {} with data {}", this.orchestratorTopic, payload, ex);
        }
    }
}
