package com.resul.todolistapplication.mapper;

import com.resul.todolistapplication.dto.CreateUserDTO;
import com.resul.todolistapplication.dto.FindUserDTO;
import com.resul.todolistapplication.dto.UpdateUserDTO;
import com.resul.todolistapplication.dto.UserDTO;
import com.resul.todolistapplication.entity.UserEntity;
import com.resul.todolistapplication.vo.FindUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(CreateUserDTO createUserDTO);

    UserEntity toUserEntity(UpdateUserDTO updateUserDTO, @MappingTarget UserEntity userEntity);

    UserDTO toUserDTO(UserEntity userEntity);

    List<UserDTO> toUserDTOList(List<UserEntity> userEntities);

    @Mapping(target = "namePhrase", expression = "java(trimToNull(findUserDTO.getNamePhrase()))")
    @Mapping(target = "emailPhrase", expression = "java(trimToNull(findUserDTO.getEmailPhrase()))")
    FindUserVO toFindUserVO(FindUserDTO findUserDTO);

    default String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
