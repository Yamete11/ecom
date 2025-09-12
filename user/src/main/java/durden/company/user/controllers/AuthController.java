package durden.company.user.controllers;

import durden.company.user.components.JwtCore;
import durden.company.user.components.UserDetailsImpl;
import durden.company.user.dto.LoginRequest;
import durden.company.user.entities.RefreshToken;
import durden.company.user.entities.User;
import durden.company.user.services.AuthService;
import durden.company.user.services.RefreshTokenService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtCore jwtCore;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        User user = userDetails.getUser();

        String accessToken = authService.generateAccessToken((UserDetailsImpl) auth.getPrincipal());
        System.out.println("accessToken: " + accessToken);

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(60)
                .sameSite("Lax")
                .build();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken.getToken())
                .httpOnly(true)
                .secure(false)
                .path("/api/auth/refresh")
                .maxAge(604800)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", accessCookie.toString());
        response.addHeader("Set-Cookie", refreshCookie.toString());


        return ResponseEntity.ok(Map.of("tokenType", "Bearer"));
    }



    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshTokenCookie,
            HttpServletResponse response) {

        if (refreshTokenCookie == null || refreshTokenCookie.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Refresh token is missing"));
        }

        RefreshToken refreshToken = refreshTokenService.getRefreshToken(refreshTokenCookie);

        refreshTokenService.verifyExpiration(refreshToken);

        String accessToken = authService.generateAccessToken(new UserDetailsImpl(refreshToken.getUser()));

        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(60)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", accessCookie.toString());

        return ResponseEntity.ok(Map.of("tokenType", "Bearer"));
    }



    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response, @CookieValue(name = "refreshToken", required = false) String refreshTokenCookie) {
        if (refreshTokenCookie != null) {
            refreshTokenService.deleteByToken(refreshTokenCookie);
        }


        ResponseCookie clearAccessCookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        ResponseCookie clearRefreshCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/api/auth/refresh")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", clearAccessCookie.toString());
        response.addHeader("Set-Cookie", clearRefreshCookie.toString());

        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, Boolean>> me(@CookieValue(value = "accessToken", required = false) String token) {
        boolean valid = token != null && jwtCore.validateToken(token);
        Map<String, Boolean> response = Map.of("authenticated", valid);
        return ResponseEntity.ok(response);
    }


}
