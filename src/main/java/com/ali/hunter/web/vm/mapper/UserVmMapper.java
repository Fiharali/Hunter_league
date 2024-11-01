package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.web.vm.request.UserSearchRequest;
import com.ali.hunter.web.vm.response.UserResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserVmMapper {

    UserVmMapper INSTANCE = Mappers.getMapper(UserVmMapper.class);

    User toUser(UserSearchRequest userSearchRequest);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUsersResponceList(Page<User> users);

}

