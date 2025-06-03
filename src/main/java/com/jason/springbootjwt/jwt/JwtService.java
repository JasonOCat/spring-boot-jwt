package com.jason.springbootjwt.jwt;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    public @NonNull String extractUsername(@NonNull String token) {
        return "todo";
    }
}
