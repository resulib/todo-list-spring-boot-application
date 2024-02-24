package com.resul.todolistapplication.validator;

import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.entity.UserEntity;
import com.resul.todolistapplication.exception.TodoValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TodoValidator {

    public void validate(UserEntity userEntity, TodoEntity todoEntity) {
        isTodoBelongsToUser(userEntity, todoEntity);
    }

    private void isTodoBelongsToUser(UserEntity userEntity, TodoEntity todoEntity) {
        var response = userEntity.getTodoEntities().stream()
                .anyMatch(todoEntity1 -> Objects.equals(todoEntity1.getId(), todoEntity.getId()));
        if (!response) {
            throw new TodoValidationException("Todo not belongs to a user. Todo Id: " + todoEntity.getId());
        }
    }
}
