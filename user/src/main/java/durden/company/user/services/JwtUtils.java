package durden.company.user.services;

import durden.company.user.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtils {

    private final String secret;

    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UserDto getUserFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        Long id = Long.valueOf(claims.get("id").toString());
        String username = claims.getSubject();
        List<String> roles = claims.get("roles", List.class);

        return new UserDto(id, username, roles);
    }
}
