package com.peaksoft.springsecuritywithjwt.model;

import javax.persistence.*;

import com.peaksoft.springsecuritywithjwt.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users2")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String fullName;

    private  String email;

    private  String phoneNumber;

    private  String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
