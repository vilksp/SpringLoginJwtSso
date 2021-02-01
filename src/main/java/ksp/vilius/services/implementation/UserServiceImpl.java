package ksp.vilius.services.implementation;

import ksp.vilius.dto.CreateUserDto;
import ksp.vilius.exceptions.ApplicationUserEmailsExistsException;
import ksp.vilius.exceptions.ApplicationUserUsernameExistsException;
import ksp.vilius.models.ApplicationUser;
import ksp.vilius.payload.JwtSuccessLoginResponse;
import ksp.vilius.payload.LoginRequest;
import ksp.vilius.repositories.ApplicationUserRepository;
import ksp.vilius.security.JwtTokenProvider;
import ksp.vilius.services.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static ksp.vilius.enums.Role.ROLE_USER;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private ApplicationUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    private static String EMAIL_EXISTS_EXCEPTION = "This email is already taken";
    private static String USERNAME_EXISTS_EXCEPTION = "This username is already taken";

    @Override
    public ApplicationUser registerNewUser(CreateUserDto userDto) {

        //Create new Application User
        ApplicationUser userToCreate = new ApplicationUser();

        //Validate email and username if they are unique
        validateEmailAndUsername(userDto);

        //Set all properties from DTO to this User Object
        userToCreate.setUsername(userDto.getUsername());
        userToCreate.setEmail(userDto.getEmail());
        userToCreate.setFirstName(userDto.getFirstName());
        userToCreate.setLastName(userDto.getLastName());
        userToCreate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // everytime user is created it will get by Default USER role and authorities
        userToCreate.setRole(ROLE_USER);
        //Role class haves all authorities from AuthorityConstant and now we are getting string array of all authorities from our ROLE_USER and setting it
        userToCreate.setAuthority(userToCreate.getRole().getAuthorities());
        log.info("new user is being created with username: " + userDto.getUsername());
        return userRepository.save(userToCreate);

    }

    @Override
    public JwtSuccessLoginResponse authenticateUser(LoginRequest request) {
        // Attempt authentication with username and password from loginrequest if not successful login will throw error
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        //Generate token
        String jwt = tokenProvider.generateToken(auth);
        return new JwtSuccessLoginResponse(jwt);
    }

    private void validateEmailAndUsername(CreateUserDto userDto) {
        ApplicationUser findByEmail = userRepository.findByEmail(userDto.getEmail());
        //If findByEmail object is not null that means it already exists and we have to throw exception
        if (findByEmail != null) {
            throw new ApplicationUserEmailsExistsException(EMAIL_EXISTS_EXCEPTION);
        }
        ApplicationUser findByUsername = userRepository.findByUsername(userDto.getUsername());
        if (findByUsername != null) {
            throw new ApplicationUserUsernameExistsException(USERNAME_EXISTS_EXCEPTION);
        }
    }
}
