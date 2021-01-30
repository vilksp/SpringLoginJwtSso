package ksp.vilius.models;

import ksp.vilius.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class ApplicationUser {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String[] authorities;
}
