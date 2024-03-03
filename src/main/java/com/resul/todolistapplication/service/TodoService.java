package com.resul.todolistapplication.service;

import com.resul.todolistapplication.dto.CreateTodoDTO;
import com.resul.todolistapplication.dto.FindTodoDTO;
import com.resul.todolistapplication.dto.TodoDTO;
import com.resul.todolistapplication.dto.UpdateTodoDTO;
import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.exception.TodoNotFoundException;
import com.resul.todolistapplication.manager.UserManager;
import com.resul.todolistapplication.mapper.TodoMapper;
import com.resul.todolistapplication.repository.TodoRepository;
import com.resul.todolistapplication.shared.PageResponse;
import com.resul.todolistapplication.specification.TodoSpecification;
import com.resul.todolistapplication.validator.TodoValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final UserManager userManager;
    private final TodoValidator todoValidator;

    public PageResponse<TodoDTO> findAll(FindTodoDTO findTodoDTO) {
        var pageable = PageRequest.of(findTodoDTO.getPage(), findTodoDTO.getSize());
        var findTodoVO = todoMapper.toFindTodoVO(findTodoDTO);
        var todoSpecification = new TodoSpecification(findTodoVO);
        var todoEntities = todoRepository.findAll(todoSpecification, pageable);
        var content = todoMapper.toTodoDTOList(todoEntities.getContent());
        return new PageResponse<>(content, todoEntities.getTotalPages(), todoEntities.getTotalElements());
    }

    public void create(Long userId, CreateTodoDTO createTodoDTO) {
        var todoEntity = todoMapper.toTodoEntity(createTodoDTO);
        var user = userManager.getUserEntity(userId);
        todoEntity.setUserEntity(user);
        todoRepository.save(todoEntity);
    }

    public void update(Long userId, Long todoId, UpdateTodoDTO updateTodoDTO) {
        var todoEntity = getUserTodo(userId, todoId);
        todoMapper.toTodoEntity(updateTodoDTO, todoEntity);
        todoRepository.save(todoEntity);
    }

    public void delete(Long userId, Long todoId) {
        var todo = getUserTodo(userId, todoId);
        todo.setDeleted(true);
        todoRepository.save(todo);
    }

    private TodoEntity getTodoEntity(Long id) {
        return todoRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with Id: " + id));
    }

    private TodoEntity getUserTodo(Long userId, Long todoId) {
        var user = userManager.getUserEntity(userId);
        var todo = getTodoEntity(todoId);
        todoValidator.validate(user, todo);
        return todo;
    }
}
