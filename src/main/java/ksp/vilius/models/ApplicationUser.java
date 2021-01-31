package ksp.vilius.models;

import ksp.vilius.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, updatable = false)
    private String username;
    @Column(unique = true, updatable = false)
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private String[] authority;
    private boolean isLocked;
    private boolean isEnabled;

    public ApplicationUser(ApplicationUser user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.authority = user.getAuthority();
        this.isLocked = user.isLocked();
        this.isEnabled = user.isEnabled();
    }
}
