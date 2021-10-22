package features.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String username;
    private String password;
    private String address;
    private Integer age;
    private String roles;
    private String permissions;
}
