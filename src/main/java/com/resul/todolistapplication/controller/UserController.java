package com.resul.todolistapplication.controller;

import com.resul.todolistapplication.dto.*;
import com.resul.todolistapplication.service.UserService;
import com.resul.todolistapplication.shared.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<PageResponse<UserDTO>> findAll(FindUserDTO findUserDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(findUserDTO));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> create(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userService.create(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        userService.update(id, updateUserDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/todos")
    public ResponseEntity<PageResponse<TodoDTO>> userTodos(FindTodoDTO findTodoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.userTodos(findTodoDTO));
    }

    @PostMapping("/{id}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable Long id, @RequestBody CreateTodoDTO createTodoDTO) {
        userService.createTodo(id, createTodoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<Void> updateTodo(@PathVariable Long userId, @PathVariable Long todoId, @RequestBody UpdateTodoDTO updateTodoDTO) {
        userService.updateTodo(userId, todoId, updateTodoDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long userId, @PathVariable Long todoId) {
        userService.deleteTodo(userId, todoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
