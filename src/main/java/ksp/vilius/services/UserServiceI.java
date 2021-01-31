package ksp.vilius.services;

import ksp.vilius.dto.CreateUserDto;
import ksp.vilius.models.ApplicationUser;
import ksp.vilius.payload.JwtSuccessLoginResponse;

public interface UserServiceI {

    public ApplicationUser registerNewUser(CreateUserDto createUserDto);

    public JwtSuccessLoginResponse authenticateUser(LoginRequest request);
}
