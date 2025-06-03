package com.jason.springbootjwt.user;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue
    private @NonNull Integer id;
    private @NonNull String firstname;
    private @NonNull String lastname;
    private @NonNull String email;
    private @NonNull String password;

}
