package microservices.orchestrated.productValidationService.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.orchestrated.productValidationService.core.dto.Event;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String ToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error converting object to JSON: {}", e.getMessage());
            return "";
        }
    }

    public Event ToEvent(String json) {
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (Exception e) {
            log.error("Error converting JSON to Event: {}", e.getMessage());
            return null;
        }
    }
}
