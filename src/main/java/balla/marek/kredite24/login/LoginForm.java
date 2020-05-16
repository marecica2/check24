package balla.marek.kredite24.login;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class LoginForm {

    @NonNull
    @Getter
    @Setter
    @NotNull
    @Email
    @Pattern(regexp = ".*(?<!@test.com)$", message = "Invalid Email")
    private String email;

    @NonNull
    @Getter
    @Setter
    @NotNull
    private String password;
}
