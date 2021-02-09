package ksp.vilius.events;

import ksp.vilius.enums.Role;
import ksp.vilius.models.ApplicationUser;
import ksp.vilius.repositories.ApplicationUserRepository;
import ksp.vilius.security.CustomUserDetailsService;
import ksp.vilius.security.JwtTokenProvider;
import ksp.vilius.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Component
public class SuccessfulAuthenticationHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService customUserRepository;
    @Autowired
    private ApplicationUserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        //Authentication class is giving OAuth2User and we have to convert it to user that our DB understand and create JWT
        Object oAuth2UserPrincipals = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oAuth2UsersUsername = ((DefaultOAuth2User) oAuth2UserPrincipals).getAttributes().get("login").toString();

        SecurityUser userToFind = (SecurityUser) customUserRepository.loadUserByUsername(oAuth2UsersUsername);
        //If user isn't found we are going to create new acc
        if (userToFind == null) {
            ApplicationUser user = new ApplicationUser();
            //Email can be null, because of github privacy options by user
            user.setId(new Random().nextLong());
            user.setEmail(userToFind.getEmail());
            user.setUsername(oAuth2UsersUsername);
            user.setRole(Role.ROLE_USER);
            user.setAuthority(user.getRole().getAuthorities());
            user.setPassword("");
            user.setNotLocked(true);
            user.setEnabled(true);
            userRepository.save(user);

            //We previously had OAuth2 type authentication now after creating user to meet requirements
            Authentication auth = new UsernamePasswordAuthenticationToken(userToFind, null, userToFind.getAuthorities());
            String jwtToken = tokenProvider.generateToken(auth);
            //if everything goes fine we will add token as a cookie
            response.addCookie(new Cookie("JWT", jwtToken));
            System.out.println(jwtToken);
        } else {
            Authentication auth = new UsernamePasswordAuthenticationToken(userToFind, null, userToFind.getAuthorities());
            String jwtToken = tokenProvider.generateToken(auth);
            response.addCookie(new Cookie("JWT", jwtToken));
            System.out.println(jwtToken);
        }

        response.sendRedirect("http://localhost:3000");
    }
}
