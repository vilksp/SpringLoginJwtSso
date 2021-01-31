package ksp.vilius.security;

import ksp.vilius.models.ApplicationUser;
import ksp.vilius.repositories.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private ApplicationUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        //Search if this user is in database if not throw exception,
        // else create user that fulfills UserDetails contract
        ApplicationUser userToFindByUsername = userRepository.findByUsername(username);
        if (userToFindByUsername == null) {
            throw new UsernameNotFoundException("Could not find user with username: `" + username + "`");
        }
        return new SecurityUser(userToFindByUsername);
    }

    public UserDetails loadUserByEmail(String email) {
        ApplicationUser userToFindByEmail = userRepository.findByEmail(email);
        if (userToFindByEmail == null) {

        }
        return new SecurityUser(userToFindByEmail);
    }

    public UserDetails loadUserById(Long id) {
        ApplicationUser userToFindById = userRepository.getById(id);
        if (userToFindById == null) {

        }
        return new SecurityUser(userToFindById);
    }
}
