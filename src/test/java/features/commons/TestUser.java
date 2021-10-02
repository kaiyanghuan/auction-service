package features.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestUser {
    private boolean isAuthenticated;
    private String token;
    private String username;
}
