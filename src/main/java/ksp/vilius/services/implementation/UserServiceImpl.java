package ksp.vilius.services.implementation;

import ksp.vilius.dto.CreateUserDto;
import ksp.vilius.exceptions.ApplicationUserCreatingException;
import ksp.vilius.models.ApplicationUser;
import ksp.vilius.repositories.ApplicationUserRepository;
import ksp.vilius.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static ksp.vilius.enums.Role.ROLE_USER;

@Service
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private ApplicationUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ApplicationUser registerNewUser(CreateUserDto userDto) {
        try {
            //Create new Application User
            ApplicationUser userToCreate = new ApplicationUser();
            //Set all properties from DTO to this User Object
            userToCreate.setUsername(userDto.getUsername());
            userToCreate.setEmail(userDto.getEmail());
            userToCreate.setFirstName(userDto.getFirstName());
            userToCreate.setLastName(userDto.getLastName());
            userToCreate.setPassword(passwordEncoder.encode(userDto.getPassword()));
            // everytime user is created it will get by Default USER role and authorities
            userToCreate.setRole(ROLE_USER);
            //Role class haves all authorities from AuthorityConstant and now we are getting string array of all authorities from our ROLE_USER and setting it
            userToCreate.setAuthorities(userToCreate.getRole().getAuthorities());

            return userRepository.save(userToCreate);
        } catch (Exception e) {
            // exception doesn't specify if username or email is taken to ensure better security
            throw new ApplicationUserCreatingException("Username or email is already taken!");
        }

    }
}
