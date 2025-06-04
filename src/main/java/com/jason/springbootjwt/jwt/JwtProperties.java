package com.jason.springbootjwt.jwt;

import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
public record JwtProperties(@NonNull String secretKey) {

}
