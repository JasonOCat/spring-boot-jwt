package com.jason.springbootjwt.auth;

import lombok.NonNull;

public record AuthenticationRequest(
        @NonNull String email,
        @NonNull String password
) {
}
