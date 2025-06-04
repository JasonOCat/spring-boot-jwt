package com.jason.springbootjwt.auth;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record AuthenticationResponse(
        @NonNull String token
) {
}
