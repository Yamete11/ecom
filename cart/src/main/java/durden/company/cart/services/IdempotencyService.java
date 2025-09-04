package durden.company.cart.services;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class IdempotencyService {

    private final RedisTemplate<String, String> redisTemplate;

    public IdempotencyService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isProcessed(String eventId) {
        Boolean exists = redisTemplate.hasKey(eventId);
        return exists != null && exists;
    }

    public void markProcessed(String eventId) {
        redisTemplate.opsForValue().set(eventId, "processed", Duration.ofHours(24));
    }
}
