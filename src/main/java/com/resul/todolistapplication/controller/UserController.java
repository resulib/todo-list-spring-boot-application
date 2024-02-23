package com.resul.todolistapplication.controller;

import com.resul.todolistapplication.dto.*;
import com.resul.todolistapplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
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

    @GetMapping("/{id}/todos")
    public ResponseEntity<List<TodoDTO>> userTodos(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.userTodos(id));
    }

    @PostMapping("/{id}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable Long id, @RequestBody CreateTodoDTO createTodoDTO) {
        userService.createTodo(id, createTodoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<Void> update(@PathVariable Long userId, @PathVariable Long todoId, @RequestBody UpdateTodoDTO updateTodoDTO) {
        userService.updateTodo(userId, todoId, updateTodoDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long todoId) {
        userService.deleteTodo(userId, todoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
