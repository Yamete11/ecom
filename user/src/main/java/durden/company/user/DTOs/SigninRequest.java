package durden.company.user.DTOs;

import lombok.Data;

@Data
public class SigninRequest {
    private String username;
    private String password;
}
