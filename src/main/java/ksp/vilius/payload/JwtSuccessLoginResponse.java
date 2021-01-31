package ksp.vilius.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtSuccessLoginResponse {
    private String jwt;
}
