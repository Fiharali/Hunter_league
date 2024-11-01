package com.ali.hunter.web.vm.request;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {

    private String cin;

    private String firstName;

    private String lastName;

    private String email;
}
