package com.ali.hunter.web.api;

import com.ali.hunter.service.UserService;
import com.ali.hunter.web.vm.UserSearchVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<com.ali.hunter.domain.entity.User>> searchUsers(@Valid UserSearchVM userSearchVM ) {

        List<com.ali.hunter.domain.entity.User> users = userService.searchUsers(userSearchVM);
        return ResponseEntity.ok(users);
    }
}
