package com.example.management_shop.Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "password")
public class Users {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotBlank
@Column(nullable = false)
    private String fullname;
@Email
@Column(nullable = false, unique = true)
    private String email;
@NotBlank
@Column(nullable = false)
    private String password;
    private String address;
    @Column(unique = true)
    private String phone;
    private Boolean status = true;
@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();
}
