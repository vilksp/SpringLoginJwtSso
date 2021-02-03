package ksp.vilius.security.oauth2;

import ksp.vilius.enums.Role;
import ksp.vilius.models.ApplicationUser;
import ksp.vilius.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CustomOAuth2Service extends DefaultOAuth2UserService {

    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String usernameFromOAuth2User = oAuth2User.getAttributes().get("login").toString();

        ApplicationUser user = userRepository.findByUsername(usernameFromOAuth2User);
        //If user object equals to null that means It's new user logging in and we have to create account
        if (user == null) {
            ApplicationUser newUser = new ApplicationUser();
            newUser.setId(new Random().nextLong());
            newUser.setUsername(usernameFromOAuth2User);
            newUser.setRole(Role.ROLE_USER);
            newUser.setAuthority(newUser.getRole().getAuthorities());
            //TODO add random password
            newUser.setPassword("");
            newUser.setNotLocked(true);
            newUser.setEnabled(true);
            userRepository.save(newUser);
        }


        return oAuth2User;
    }
}
