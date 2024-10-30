package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.web.vm.request.UserSearchRequest;
import com.ali.hunter.service.UserService;
import com.ali.hunter.web.vm.mapper.UserVmMapper;
import com.ali.hunter.web.vm.response.UserResponse;
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
    private final UserVmMapper userVmMapper;


    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@Valid UserSearchRequest userSearchRequest) {

        User user = userVmMapper.toUser(userSearchRequest);
        List<User> users = userService.searchUsers(user);

        List<UserResponse> usersDTO = userVmMapper.toUsersResponceList(users);

        return ResponseEntity.ok(usersDTO);
    }
}
