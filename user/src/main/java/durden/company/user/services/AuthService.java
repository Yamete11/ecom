package durden.company.user.services;

import durden.company.user.components.JwtCore;
import durden.company.user.components.UserDetailsImpl;
import durden.company.user.dto.Tokens;
import durden.company.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtCore jwtCore;

    public String generateAccessToken(UserDetailsImpl userDetails) {
        return jwtCore.generateAccessToken(userDetails.getUsername());
    }
}

