package com.jason.springbootjwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private static final String SECRET_KEY = "";

    public @NonNull String extractUsername(@NonNull String token) {
        return "todo";
    }

    private Claims extractAllClaims(@NonNull String token) {
        return Jwts
                .parser()
                .setSigningKey("todo")
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
}
