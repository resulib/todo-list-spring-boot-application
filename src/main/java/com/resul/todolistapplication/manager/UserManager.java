package com.resul.todolistapplication.manager;

import com.resul.todolistapplication.auth.JwtService;
import com.resul.todolistapplication.dto.FindUserDTO;
import com.resul.todolistapplication.entity.UserEntity;
import com.resul.todolistapplication.exception.UserNotFoundException;
import com.resul.todolistapplication.mapper.UserMapper;
import com.resul.todolistapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserEntity getUserEntity(Long id) {
        return userRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id: " + id));
    }

    public UserEntity findByUsername(String username) {
        return userRepository
                .findByUsernameAndIsDeleted(username, false)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
    }

    public UserEntity getUserFromToken() {
        return findByUsername(jwtService.getUserDetailsFromToken().getUsername());
    }

    public Page<UserEntity> findAll(FindUserDTO findUserDTO) {
        var pageable = PageRequest.of(findUserDTO.getPage(), findUserDTO.getSize());
        var findUserVO = userMapper.toFindUserVO(findUserDTO);
        return userRepository.findAll(findUserVO, pageable);
    }


}
