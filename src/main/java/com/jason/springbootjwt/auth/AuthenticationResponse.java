package com.jason.springbootjwt.auth;

import lombok.NonNull;

public record AuthenticationResponse(
        @NonNull String token
) {
}
