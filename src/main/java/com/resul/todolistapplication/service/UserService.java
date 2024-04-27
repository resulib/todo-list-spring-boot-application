package com.resul.todolistapplication.service;

import com.resul.todolistapplication.dto.*;
import com.resul.todolistapplication.manager.UserManager;
import com.resul.todolistapplication.mapper.UserMapper;
import com.resul.todolistapplication.repository.UserRepository;
import com.resul.todolistapplication.shared.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserManager userManager;
    private final TodoService todoService;

    public PageResponse<UserDTO> findAll(FindUserDTO findUserDTO) {
        var userEntities = userManager.findAll(findUserDTO);
        var content = userMapper.toUserDTOList(userEntities.getContent());
        return new PageResponse<>(content, userEntities.getTotalPages(), userEntities.getTotalElements());
    }

    public UserDTO findById(Long id) {
        return userMapper.toUserDTO(userManager.getUserEntity(id));
    }

    public void create(CreateUserDTO createUserDTO) {
        var user = userMapper.toUserEntity(createUserDTO);
        userRepository.save(user);
    }

    public void update(Long id, UpdateUserDTO updateUserDTO) {
        var userEntity = userManager.getUserEntity(id);
        userRepository.save(userMapper.toUserEntity(updateUserDTO, userEntity));
    }

    public void delete(Long id) {
        var userEntity = userManager.getUserEntity(id);
        userEntity.setDeleted(true);
        userRepository.save(userEntity);
    }

    public PageResponse<TodoDTO> userTodos(FindTodoDTO findTodoDTO) {
        return todoService.findAll(findTodoDTO);
    }

    public void createTodo(CreateTodoDTO createTodoDTO) {
        todoService.create(createTodoDTO);
    }

    public void updateTodo(Long userId, Long todoId, UpdateTodoDTO updateTodoDTO) {
        todoService.update(userId, todoId, updateTodoDTO);
    }

    public void deleteTodo(Long todoId) {
        todoService.delete(todoId);
    }
}
