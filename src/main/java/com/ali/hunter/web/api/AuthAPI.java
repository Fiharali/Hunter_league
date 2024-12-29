package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.service.UserService;
import com.ali.hunter.web.vm.mapper.UserVmMapper;
import com.ali.hunter.web.vm.request.AuthRequest;
import com.ali.hunter.web.vm.request.LoginRequest;
import com.ali.hunter.web.vm.request.UserRequest;
import com.ali.hunter.web.vm.request.UserSearchRequest;
import com.ali.hunter.web.vm.response.ErrorResponse;
import com.ali.hunter.web.vm.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {

    private final UserService userService;
    private final UserVmMapper userVmMapper;




    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {

        try {
            return ResponseEntity.ok(userService.loginAuth(authRequest));
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(e.getMessage()));
        }

    }



}
