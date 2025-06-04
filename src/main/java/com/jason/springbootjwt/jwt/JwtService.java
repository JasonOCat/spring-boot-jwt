package com.jason.springbootjwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    public @NonNull Optional<String> extractUsername(@NonNull String token) {
        return Optional.ofNullable(extractClaim(token, Claims::getSubject));
    }

    public String generateToken(@NonNull UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken(
            @NonNull Map<String, Object> claims,
            @NonNull UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .issuedAt(new Date(System.currentTimeMillis() + jwtProperties.expirationTime()))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(
            @NonNull String token,
            @NonNull UserDetails userDetails
    ) {
        return extractUsername(token)
                .map(username -> username.equals(userDetails.getUsername()) && !isTokenExpired(token))
                .orElse(false);
    }

    private boolean isTokenExpired(@NonNull String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(@NonNull String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(@NonNull String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(
            @NonNull String token,
            @NonNull Function<Claims, T> claimsResolver
    ) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
