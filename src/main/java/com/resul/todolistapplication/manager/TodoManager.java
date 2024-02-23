package com.resul.todolistapplication.manager;

import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.exception.TodoNotFoundException;
import com.resul.todolistapplication.repository.TodoRepository;
import com.resul.todolistapplication.validator.TodoValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TodoManager {

    private final TodoRepository todoRepository;
    private final UserManager userManager;
    private final TodoManager todoManager;
    private final TodoValidator todoValidator;

    public TodoEntity getTodoEntity(Long id) {
        return todoRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with Id: " + id));
    }

    public TodoEntity getUserTodo(Long userId, Long todoId) {
        var user = userManager.getUserEntity(userId);
        var todo = todoManager.getTodoEntity(todoId);
        todoValidator.validate(user, todo);
        return todo;
    }
}
