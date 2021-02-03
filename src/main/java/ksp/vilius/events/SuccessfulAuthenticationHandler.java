package ksp.vilius.events;

import ksp.vilius.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessfulAuthenticationHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        //Authentication class is giving OAuth2User and we have to convert it to user that our DB understand and create JWT
        Object oAuth2UserPrincipals = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String oAuth2UsersUsername = ((DefaultOAuth2User) oAuth2UserPrincipals).getAttributes().get("login").toString();





    }
}
