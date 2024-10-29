package com.ali.hunter.web.vm;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchVM {

    private String firstName;

    private String lastName;

    private String email;
}
