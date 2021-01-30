package ksp.vilius.services;

import ksp.vilius.dto.CreateUserDto;
import ksp.vilius.models.ApplicationUser;

public interface UserServiceI {

    public ApplicationUser registerNewUser(CreateUserDto createUserDto);
}
