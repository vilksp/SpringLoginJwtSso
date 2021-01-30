package ksp.vilius.models;

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
    private String role;
    private String[] authorities;
}
