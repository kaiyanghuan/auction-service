package features.commons;

import lombok.Data;

@Data
public class AuthenticationResponse {
    String tokenType;
    String accessToken;
}
