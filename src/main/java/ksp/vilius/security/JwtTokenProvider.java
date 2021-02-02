package ksp.vilius.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ksp.vilius.constants.SecurityConstant.*;

@Component
public class JwtTokenProvider {


    public String generateToken(Authentication auth) {

        SecurityUser user = (SecurityUser) auth.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date expiry = new Date(now.getTime() + EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("id", user.getId());
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer(JWT_TOKEN_ISSUER)
                .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(JWT_TOKEN_SECRET).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException |
                IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        }
    }


    public Long getUserIdFromJwt(String token) {
        Claims claim = Jwts.parser().setSigningKey(JWT_TOKEN_SECRET).parseClaimsJws(token).getBody();

        String id = null;
        try {
            id = (String) claim.get("id");
        } catch (Exception e) {
            return Long.valueOf(claim.get("id").toString());
        }
        return Long.parseLong(id);
    }
}
