package com.resul.todolistapplication.mapper;

import com.resul.todolistapplication.dto.CreateUserDTO;
import com.resul.todolistapplication.dto.UpdateUserDTO;
import com.resul.todolistapplication.dto.UserDTO;
import com.resul.todolistapplication.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(CreateUserDTO createUserDTO);

    UserEntity toUserEntity(UpdateUserDTO updateUserDTO, @MappingTarget UserEntity userEntity);

    UserDTO toUserDTO(UserEntity userEntity);

    List<UserDTO> toUserDTOList(List<UserEntity> userEntities);
}
