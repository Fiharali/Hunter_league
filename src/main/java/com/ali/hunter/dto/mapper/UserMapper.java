package com.ali.hunter.dto.mapper;

import com.ali.hunter.domain.entity.Species;
import com.ali.hunter.domain.entity.User;
import com.ali.hunter.dto.SpeciesDTO;
import com.ali.hunter.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "cin", source = "cin")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "nationality", source = "nationality")

    UserDTO toUserDTO(User user);
    List<UserDTO> toUsersDTOList(List<User> users);
}
