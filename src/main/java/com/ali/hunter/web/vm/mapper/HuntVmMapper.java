package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Hunt;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.web.vm.request.HuntRequest;
import com.ali.hunter.web.vm.request.LoginRequest;
import com.ali.hunter.web.vm.request.UserRequest;
import com.ali.hunter.web.vm.request.UserSearchRequest;
import com.ali.hunter.web.vm.response.UserResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HuntVmMapper {



    Hunt toHunt(HuntRequest huntRequest);


}

