package com.ali.hunter.web.vm.mapper;

import com.ali.hunter.domain.entity.Participation;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.web.vm.ParticipationVM;
import com.ali.hunter.web.vm.UserSearchVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserVmMapper {

    UserVmMapper INSTANCE = Mappers.getMapper(UserVmMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    User toUser(UserSearchVM userSearchVM);
}

