package com.ocbc.auctionservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private String name;

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
}
