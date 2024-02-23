package com.resul.todolistapplication.service;

import com.resul.todolistapplication.dto.*;
import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.manager.TodoManager;
import com.resul.todolistapplication.manager.UserManager;
import com.resul.todolistapplication.mapper.TodoMapper;
import com.resul.todolistapplication.mapper.UserMapper;
import com.resul.todolistapplication.repository.TodoRepository;
import com.resul.todolistapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserManager userManager;
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final TodoManager todoManager;

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

    public List<TodoDTO> userTodos(Long id) {
        var user = userManager.getUserEntity(id);
        var todoEntities = todoRepository.findAllByIdAndIsDeleted(user.getId(), false);
        return todoMapper.toTodoDTOList(todoEntities);
    }

    public void createTodo(Long id, CreateTodoDTO createTodoDTO) {
        var user = userManager.getUserEntity(id);
        TodoEntity todo = new TodoEntity();
        todo.setContent(createTodoDTO.getContent());
        todo.setTitle(createTodoDTO.getTitle());
        todo.setUserEntity(user);
        todoRepository.save(todo);
    }

    public void updateTodo(Long userId, Long todoId, UpdateTodoDTO updateTodoDTO) {
        var todoEntity = todoManager.getUserTodo(userId, todoId);
        todoMapper.toTodoEntity(updateTodoDTO, todoEntity);
        todoRepository.save(todoEntity);
    }

    public void deleteTodo(Long userId, Long todoId) {
        var todoEntity = todoManager.getUserTodo(userId, todoId);
        todoEntity.setDeleted(true);
        todoRepository.save(todoEntity);
    }
}
