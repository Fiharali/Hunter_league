package com.ali.hunter.web.vm.response;

import com.ali.hunter.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String cin;

    private Role role;

    private String firstName;

    private String lastName;

    private String email;

    private String nationality;


}
