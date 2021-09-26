package com.ocbc.auctionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name = "Fan Kaiyang";

    @Column(name = "username")
    private String username = "kaiyangfan";

    @Column(name = "address")
    private String address;

    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles = "";

    @Column(name = "permission")
    private String permissions = "";

    public List<String> getRoleList(){
        if(roles.length() == 0){
            new ArrayList<>();
        }
        return Arrays.asList(roles.split(","));
    }

    public List<String> getPermissionList(){
        if(permissions.length() == 0){
            new ArrayList<>();
        }
        return Arrays.asList(permissions.split(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(address, user.address) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(permissions, user.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, address, age, password, roles, permissions);
    }
}
