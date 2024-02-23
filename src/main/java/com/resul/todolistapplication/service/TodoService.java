package com.resul.todolistapplication.service;

import com.resul.todolistapplication.dto.CreateTodoDTO;
import com.resul.todolistapplication.dto.TodoDTO;
import com.resul.todolistapplication.dto.UpdateTodoDTO;
import com.resul.todolistapplication.manager.TodoManager;
import com.resul.todolistapplication.mapper.TodoMapper;
import com.resul.todolistapplication.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final TodoManager todoManager;

    public List<TodoDTO> findAll() {
        var todoEntities = todoRepository.findAllByIsDeleted(false);
        return todoMapper.toTodoDTOList(todoEntities);
    }

    public TodoDTO findById(Long id) {
        var todoEntity = todoManager.getTodoEntity(id);
        return todoMapper.toTodoDTO(todoEntity);
    }

    public void create(CreateTodoDTO createTodoDTO) {
        var todoEntity = todoMapper.toTodoEntity(createTodoDTO);
        todoRepository.save(todoEntity);
    }

    public void update(Long id, UpdateTodoDTO updateTodoDTO) {
        var todoEntity = todoManager.getTodoEntity(id);
        todoMapper.toTodoEntity(updateTodoDTO, todoEntity);
        todoRepository.save(todoEntity);
    }

    public void delete(Long id) {
        var todoEntity = todoManager.getTodoEntity(id);
        todoEntity.setDeleted(true);
        todoRepository.save(todoEntity);
    }
}
