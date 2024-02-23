package com.resul.todolistapplication.manager;

import com.resul.todolistapplication.entity.UserEntity;
import com.resul.todolistapplication.exception.UserNotFoundException;
import com.resul.todolistapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;

    public UserEntity getUserEntity(Long id) {
        return userRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new UserNotFoundException("User not found with Id: " + id));
    }
}
