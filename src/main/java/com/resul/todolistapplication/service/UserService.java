package com.resul.todolistapplication.service;

import com.resul.todolistapplication.dto.*;
import com.resul.todolistapplication.manager.UserManager;
import com.resul.todolistapplication.mapper.UserMapper;
import com.resul.todolistapplication.repository.UserRepository;
import com.resul.todolistapplication.shared.PageRequest;
import com.resul.todolistapplication.shared.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserManager userManager;
    private final TodoService todoService;

    public List<UserDTO> findAll() {
        var userEntities = userRepository.findAllByIsDeleted(false);
        return userMapper.toUserDTOList(userEntities);
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

    public PageResponse<TodoDTO> userTodos(Long userId, PageRequest pageRequest) {
        return todoService.findAll(userId, pageRequest.getPage(), pageRequest.getSize());
    }

    public void createTodo(Long userId, CreateTodoDTO createTodoDTO) {
        todoService.create(userId, createTodoDTO);
    }

    public void updateTodo(Long userId, Long todoId, UpdateTodoDTO updateTodoDTO) {
        todoService.update(userId, todoId, updateTodoDTO);
    }

    public void deleteTodo(Long userId, Long todoId) {
        todoService.delete(userId, todoId);
    }
}
