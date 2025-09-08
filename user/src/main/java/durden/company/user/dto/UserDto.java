package durden.company.user.dto;

import java.util.List;

public record UserDto(Long id, String username, List<String> roles) {}
