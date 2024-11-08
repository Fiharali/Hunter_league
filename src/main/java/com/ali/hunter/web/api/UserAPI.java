package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.web.vm.request.SpeciesRequest;
import com.ali.hunter.web.vm.request.UserRequest;
import com.ali.hunter.web.vm.request.UserSearchRequest;
import com.ali.hunter.service.UserService;
import com.ali.hunter.web.vm.mapper.UserVmMapper;
import com.ali.hunter.web.vm.response.SpeciesResponse;
import com.ali.hunter.web.vm.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;
    private final UserVmMapper userVmMapper;


    @GetMapping
    public ResponseEntity<List<UserResponse>> searchUsers(@Valid UserSearchRequest userSearchRequest ,
     @RequestParam(defaultValue = "0") int page,
     @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("nationality").ascending());
        User user = userVmMapper.toUser(userSearchRequest);
        Page<User> users = userService.searchUsers(user,pageable);

        List<UserResponse> usersDTO = userVmMapper.toUsersResponceList(users);

        return ResponseEntity.ok(usersDTO);
    }


    @PostMapping
    public ResponseEntity<UserResponse> addUser(
            @Valid @RequestBody UserRequest userRequest) {
        User userEntity = userVmMapper.toUser(userRequest);
        User user = userService.addSUser(userEntity);
        return ResponseEntity.ok(userVmMapper.toUserResponse(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable UUID id,
            @Valid @RequestBody UserRequest userRequest) {

        User userEntity = userVmMapper.toUser(userRequest);

        User user = userService.updateUser(id, userEntity);
        return ResponseEntity.ok(userVmMapper.toUserResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteSpeciesById(@PathVariable UUID id) {
        User user = new User();
        user.setId(id);
        User deletedUser = userService.deleteUser(user);
        return ResponseEntity.ok(userVmMapper.toUserResponse(deletedUser));
    }
}
