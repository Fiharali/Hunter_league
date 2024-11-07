package com.ali.hunter.web.vm.request;

import com.ali.hunter.domain.entity.Participation;
import com.ali.hunter.domain.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "you must enter the role of the user")
    private Role role;

    @NotNull(message = "you must enter the first name of the user")
    private String firstName;

    @NotNull(message = "you must enter the last name of the user")
    private String lastName;

    @NotNull(message = "you must enter the first name of the user")
    private String cin;

    @NotNull(message = "you must enter the email of the user")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "you must enter the password of the user")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must be at least 8 characters long, and contain at least one uppercase letter, one lowercase letter, and one number"
    )

    private String password;

    @NotNull(message = "you must enter the nationality me of the user")
    private String nationality;

    @NotNull(message = "you must enter the licenseExpirationDate  me of the user")
    @Future(message = "The license expiration date must be in the future")
    private LocalDateTime licenseExpirationDate;

}
