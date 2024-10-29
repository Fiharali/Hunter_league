package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.dto.SpeciesDTO;
import com.ali.hunter.dto.UserDTO;
import com.ali.hunter.dto.mapper.UserMapper;
import com.ali.hunter.service.UserService;
import com.ali.hunter.web.vm.UserSearchVM;
import com.ali.hunter.web.vm.mapper.SpeciesVmMapper;
import com.ali.hunter.web.vm.mapper.UserVmMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAPI {

    private final UserService userService;
    private final UserVmMapper userVmMapper;
    private final UserMapper userMapper;

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@Valid UserSearchVM userSearchVM ) {

        User user = userVmMapper.toUser(userSearchVM);
        List<User> users = userService.searchUsers(user);

        List<UserDTO> usersDTO = userMapper.toUsersDTOList(users);

        return ResponseEntity.ok(usersDTO);
    }
}
