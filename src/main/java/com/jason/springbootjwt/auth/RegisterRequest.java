package com.jason.springbootjwt.auth;

import lombok.NonNull;

public record RegisterRequest(
        @NonNull String firstname,
        @NonNull String lastname,
        @NonNull String email,
        @NonNull String password
) {
}
