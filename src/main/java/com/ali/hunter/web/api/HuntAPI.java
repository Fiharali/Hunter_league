package com.ali.hunter.web.api;

import com.ali.hunter.domain.entity.Hunt;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.service.HuntService;
import com.ali.hunter.web.vm.mapper.HuntVmMapper;
import com.ali.hunter.web.vm.request.HuntRequest;
import com.ali.hunter.web.vm.request.UserRequest;
import com.ali.hunter.web.vm.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hunts")
@RequiredArgsConstructor
public class HuntAPI {

    private final HuntService huntService;
    private final HuntVmMapper huntVmMapper;



    @PostMapping
    public ResponseEntity<Map<String,String>> registerResult(
            @Valid @RequestBody HuntRequest huntRequest) {

           double score = huntService.registerHunt(huntRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hunt registered successfully , and the score is "+ score );
        return ResponseEntity.ok(response);
    }





}
