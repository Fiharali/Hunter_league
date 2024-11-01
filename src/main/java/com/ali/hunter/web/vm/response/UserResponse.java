package com.ali.hunter.web.vm.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class UserResponse {

    private UUID id;

    private String cin;

    private String email;

    private String nationality;


}
