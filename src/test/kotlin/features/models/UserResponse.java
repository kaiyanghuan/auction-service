package features.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    public Integer id;
    public String name;
    public String username;
    public String address;
    public Integer age;
    public String roles;
    public String permission;
}
